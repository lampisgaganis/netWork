package com.netWork.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netWork.backend.dto.SkillRequest;
import com.netWork.backend.dto.SkillResponse;
import com.netWork.backend.service.SkillService;

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
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {
    
    private final SkillService skillService;

    @PostMapping
    public SkillResponse addSkill(
        Authentication authentication,
        @Valid @RequestBody SkillRequest request
    )
    {
        return skillService.addSkill(authentication.getName(), request);
    }
    
    @GetMapping
    public List<SkillResponse> getSkills(Authentication authentication) {
        return skillService.getSkills(authentication.getName());
    }
    
    @PutMapping("/{id}")
    public SkillResponse updateSkill(@PathVariable Long id, Authentication authentication, @Valid @RequestBody SkillRequest request) {
        return skillService.updateSkill(id, authentication.getName(), request);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Long id, Authentication authentication) {
        skillService.deleteSkill(id, authentication.getName());
    }

}
