package com.netWork.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netWork.backend.dto.EducationRequest;
import com.netWork.backend.dto.EducationResponse;
import com.netWork.backend.security.CurrentUserService;
import com.netWork.backend.service.EducationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;
    private final CurrentUserService currentUserService;

    @PostMapping
    public EducationResponse addEducation(Authentication authentication, @Valid @RequestBody EducationRequest request) {
        return educationService.addEducation(currentUserService.getCurrentUser(authentication), request);
    }

    @GetMapping
    public List<EducationResponse> getEducation(Authentication authentication) {
        return educationService.getMyEducations(currentUserService.getCurrentUser(authentication));
    }
    
    @PutMapping("/{id}")
    public EducationResponse updateEducation(@PathVariable Long id, Authentication authentication, @Valid @RequestBody EducationRequest request) {
        return educationService.updateEducation(id, currentUserService.getCurrentUser(authentication), request);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id, Authentication authentication) {
        educationService.deleteEducation(id, currentUserService.getCurrentUser(authentication));
    }
}
