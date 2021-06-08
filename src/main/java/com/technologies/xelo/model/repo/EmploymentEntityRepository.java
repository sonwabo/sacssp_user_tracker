package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.EmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmploymentEntityRepository extends JpaRepository<EmploymentEntity, Long> {
}