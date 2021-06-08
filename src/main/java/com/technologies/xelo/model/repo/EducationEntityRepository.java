package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EducationEntityRepository extends JpaRepository<EducationEntity, Long> {
}