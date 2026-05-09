package com.netWork.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netWork.backend.entity.User;
import com.netWork.backend.entity.WorkExperience;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long>{
    List<WorkExperience> findByUser(User user);
}
