package com.netWork.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netWork.backend.entity.Education;
import com.netWork.backend.entity.User;

public interface EducationRepository extends JpaRepository<Education, Long>{
    List<Education> findByUser(User user);

}
