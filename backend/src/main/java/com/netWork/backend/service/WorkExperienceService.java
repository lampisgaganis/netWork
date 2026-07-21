package com.netWork.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.WorkExperienceRequest;
import com.netWork.backend.dto.WorkExperienceResponse;
import com.netWork.backend.entity.User;
import com.netWork.backend.entity.WorkExperience;
import com.netWork.backend.exception.ResourceNotFoundException;
import com.netWork.backend.exception.UnauthorizedActionException;
import com.netWork.backend.mapper.WorkExperienceMapper;
import com.netWork.backend.repository.WorkExperienceRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WorkExperienceService {
    
        private final WorkExperienceRepository workExperienceRepository;

        public WorkExperienceResponse addWorkExperience(User user, WorkExperienceRequest request) {
                
                WorkExperience workExperience = WorkExperience.builder()
                        .company(request.company())
                        .position(request.position())
                        .description(request.description())
                        .startDate(request.startDate())
                        .endDate(request.endDate())
                        .currentlyWorking(request.currentlyWorking())
                        .isPublic(request.isPublic())
                        .user(user)
                        .build();
                
                WorkExperience saved = workExperienceRepository.save(workExperience);

                return WorkExperienceMapper.toResponse(saved);

        }

        public List<WorkExperienceResponse> getMyWorkExperiences(User user) {

                return workExperienceRepository.findByUser(user)
                        .stream()
                        .map(WorkExperienceMapper::toResponse)
                        .toList();
                }

        public WorkExperienceResponse updateWorkExperience( Long id, User user, WorkExperienceRequest request) {

                WorkExperience workExperience = workExperienceRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Work experience not found"));
                        
                if (!workExperience.getUser().getId().equals(user.getId())) {
                        throw new UnauthorizedActionException("Unauthorized");
                }
                
                workExperience.setCompany(request.company());
                workExperience.setPosition(request.position());
                workExperience.setDescription(request.description());
                workExperience.setStartDate(request.startDate());
                workExperience.setEndDate(request.endDate());
                workExperience.setCurrentlyWorking(request.currentlyWorking());
                workExperience.setPublic(request.isPublic());

                WorkExperience saved = workExperienceRepository.save(workExperience);

                return WorkExperienceMapper.toResponse(saved);
        
        }

        public void deleteWorkExperience(Long id, User user) {

                WorkExperience workExperience = workExperienceRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Work experience not found"));
                
                if (!workExperience.getUser().getId().equals(user.getId())) {
                        throw new UnauthorizedActionException("Unauthorized");
                }

                workExperienceRepository.delete(workExperience);

        }


}
