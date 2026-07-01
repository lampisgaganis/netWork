package com.netWork.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.EducationRequest;
import com.netWork.backend.dto.EducationResponse;
import com.netWork.backend.entity.Education;
import com.netWork.backend.entity.User;
import com.netWork.backend.exception.ResourceNotFoundException;
import com.netWork.backend.exception.UnauthorizedActionException;
import com.netWork.backend.mapper.EducationMapper;
import com.netWork.backend.repository.EducationRepository;
import com.netWork.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public EducationResponse addEducation(String email, EducationRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Education education = Education.builder()
                .institution(request.institution())
                .degree(request.degree())
                .fieldOfStudy(request.fieldOfStudy())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .currentlyStudying(request.currentlyStudying())
                .isPublic(request.isPublic())
                .user(user)
                .build();
        
        Education saved = educationRepository.save(education);

        return EducationMapper.toResponse(saved);

    }

    public List<EducationResponse> getMyEducations(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return educationRepository.findByUser(user)
                .stream()
                .map(EducationMapper::toResponse)
                .toList();
    }
    
    public EducationResponse updateEducation( Long id, String email, EducationRequest request) {

        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found"));

        if (!education.getUser().getEmail().equals(email)) {
            throw new UnauthorizedActionException("Unauthorized");
        }

        education.setInstitution(request.institution());
        education.setDegree(request.degree());
        education.setFieldOfStudy(request.fieldOfStudy());
        education.setStartDate(request.startDate());
        education.setEndDate(request.endDate());
        education.setCurrentlyStudying(request.currentlyStudying());
        education.setPublic(request.isPublic());

        Education updated = educationRepository.save(education);

        return EducationMapper.toResponse(updated);
    }

    public void deleteEducation(Long id, String email) {
        Education education = educationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Education not found"));

        if (!education.getUser().getEmail().equals(email)) {
            throw new UnauthorizedActionException("Unauthorized");
        }

        educationRepository.delete(education);
    }

}
