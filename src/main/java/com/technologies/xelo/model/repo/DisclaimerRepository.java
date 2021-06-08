package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.ContactPointEntity;
import com.technologies.xelo.model.entities.DisclaimerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisclaimerRepository extends JpaRepository<DisclaimerEntity, Long> {
}
