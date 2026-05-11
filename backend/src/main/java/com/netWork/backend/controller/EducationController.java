package com.netWork.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netWork.backend.dto.EducationRequest;
import com.netWork.backend.dto.EducationResponse;
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
@RequestMapping("/api/v1/educations")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PostMapping
    public EducationResponse addEducation(Authentication authentication, @Valid @RequestBody EducationRequest request) {
        return educationService.addEducation(authentication.getName(), request);
    }

    @GetMapping
    public List<EducationResponse> getEducation(Authentication authentication) {
        return educationService.getMyEducations(authentication.getName());
    }
    
    @PutMapping("/{id}")
    public EducationResponse updateEducation(@PathVariable Long id, Authentication authentication, @Valid @RequestBody EducationRequest request) {
        return educationService.updateEducation(id, authentication.getName(), request);
    }

    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id, Authentication authentication) {
        educationService.deleteEducation(id, authentication.getName());
    }
}
