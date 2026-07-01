package com.netWork.backend.mapper;

import com.netWork.backend.dto.SkillResponse;
import com.netWork.backend.entity.Skill;

public class SkillMapper {
    public static SkillResponse toResponse(Skill skill) {
        return new SkillResponse(
            skill.getId(),
            skill.getName()
        );
    }
}
