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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillResponse addSkill(User user, SkillRequest request) {
        
        Skill skill = Skill.builder()
            .name(request.name())
            .user(user)
            .build();
        
        Skill saved = skillRepository.save(skill);

        return SkillMapper.toResponse(saved);
    }

    public List<SkillResponse> getSkills(User user) {
        
        List<Skill> skills = skillRepository.findByUser(user);
        
        return skills.stream()
            .map(SkillMapper::toResponse)
            .toList();
    }

    public SkillResponse updateSkill(Long id, User user, SkillRequest request) {

        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        if (!skill.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("Unauthorized");
        }

        skill.setName(request.name());
        Skill updated = skillRepository.save(skill);

        return SkillMapper.toResponse(updated);
    }

    public void deleteSkill(Long id, User user) {
        Skill skill = skillRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found"));

        if (!skill.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedActionException("Unauthorized");
        }

        skillRepository.delete(skill);
    }

}
