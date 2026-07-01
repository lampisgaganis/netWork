package com.netWork.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.netWork.backend.dto.SkillRequest;
import com.netWork.backend.dto.SkillResponse;
import com.netWork.backend.entity.Skill;
import com.netWork.backend.entity.User;
import com.netWork.backend.exception.ResourceNotFoundException;
import com.netWork.backend.exception.UnauthorizedActionException;
import com.netWork.backend.mapper.SkillMapper;
import com.netWork.backend.repository.SkillRepository;
import com.netWork.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillResponse addSkill(String email, SkillRequest request) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Skill skill = Skill.builder()
            .name(request.name())
            .user(user)
            .build();
        
        Skill saved = skillRepository.save(skill);

        return SkillMapper.toResponse(saved);
    }

    public List<SkillResponse> getSkills(String email) {
        
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        List<Skill> skills = skillRepository.findByUser(user);
        
        return skills.stream()
            .map(SkillMapper::toResponse)
            .toList();
    }

    public SkillResponse updateSkill(Long id, String email, SkillRequest request) {

        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        if (!skill.getUser().getEmail().equals(email)) {
            throw new UnauthorizedActionException("Unauthorized");
        }

        skill.setName(request.name());
        Skill updated = skillRepository.save(skill);

        return SkillMapper.toResponse(updated);
    }

    public void deleteSkill(Long id, String email) {
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        if (!skill.getUser().getEmail().equals(email)) {
            throw new UnauthorizedActionException("Unauthorized");
        }

        skillRepository.delete(skill);
    }

}
