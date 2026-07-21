package com.netWork.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netWork.backend.dto.WorkExperienceRequest;
import com.netWork.backend.dto.WorkExperienceResponse;
import com.netWork.backend.security.CurrentUserService;
import com.netWork.backend.service.WorkExperienceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/work-experiences")
@RequiredArgsConstructor
public class WorkExperienceController {

    private final WorkExperienceService workExperienceService;
    private final CurrentUserService currentUserService;

    @PostMapping
    public WorkExperienceResponse addWorkExperience (Authentication authentication,@Valid @RequestBody WorkExperienceRequest request) {
        
        return workExperienceService.addWorkExperience(
                currentUserService.getCurrentUser(authentication),
                request
        );
    }

    @GetMapping
    public List<WorkExperienceResponse> getMyWorkExperiences (Authentication authentication) {
        return workExperienceService.getMyWorkExperiences(currentUserService.getCurrentUser(authentication));
    }

    @PutMapping("/{id}")
    public WorkExperienceResponse updateWorkExperience(@PathVariable Long id, Authentication authentication, @Valid @RequestBody WorkExperienceRequest request) {
        return workExperienceService.updateWorkExperience(id, currentUserService.getCurrentUser(authentication), request);
    }

    @DeleteMapping("/{id}")
    public void deleteWorkExperience(@PathVariable Long id, Authentication authentication) {
        workExperienceService.deleteWorkExperience(id, currentUserService.getCurrentUser(authentication));
    }
}
