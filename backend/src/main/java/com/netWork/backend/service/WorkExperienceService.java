package com.netWork.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.WorkExperienceRequest;
import com.netWork.backend.dto.WorkExperienceResponse;
import com.netWork.backend.entity.User;
import com.netWork.backend.entity.WorkExperience;
import com.netWork.backend.mapper.WorkExperienceMapper;
import com.netWork.backend.repository.UserRepository;
import com.netWork.backend.repository.WorkExperienceRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class WorkExperienceService {
    
        private final WorkExperienceRepository workExperienceRepository;
        private final UserRepository userRepository;

        public WorkExperienceResponse addWorkExperience(String email, WorkExperienceRequest request) {

                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found"));
                
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

        public List<WorkExperienceResponse> getMyWorkExperiences(String email) {
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("User not found"));

                return workExperienceRepository.findByUser(user)
                        .stream()
                        .map(WorkExperienceMapper::toResponse)
                        .toList();
                }

        public WorkExperienceResponse updateWorkExperience( Long id, String email, WorkExperienceRequest request) {

                WorkExperience workExperience = workExperienceRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Work experience not found"));
                        
                if (!workExperience.getUser().getEmail().equals(email)) {
                        throw new RuntimeException("Unauthorized");
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

        public void deleteWorkExperience(Long id, String email) {

                WorkExperience workExperience = workExperienceRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Work experience not found"));
                
                if (!workExperience.getUser().getEmail().equals(email)) {
                        throw new RuntimeException("Unauthorized");
                }

                workExperienceRepository.delete(workExperience);

        }


}
