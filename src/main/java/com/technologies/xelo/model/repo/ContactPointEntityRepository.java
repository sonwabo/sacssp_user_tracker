package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.ContactPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContactPointEntityRepository extends JpaRepository<ContactPointEntity, Long> {
}