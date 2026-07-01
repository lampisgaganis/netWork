package com.netWork.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netWork.backend.entity.Skill;
import com.netWork.backend.entity.User;

public interface SkillRepository extends JpaRepository<Skill, Long>{
    List<Skill> findByUser(User user);
}
