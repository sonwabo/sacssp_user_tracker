package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.EmploymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmploymentEntityRepository extends JpaRepository<EmploymentEntity, Long> {
    @Query(value = "select count(*) from data_capture.employment i where i.iscommunitypractitioner= :value",nativeQuery = true)
    Long getCommunityPractitionerCount(@Param(value = "value") String value);
}