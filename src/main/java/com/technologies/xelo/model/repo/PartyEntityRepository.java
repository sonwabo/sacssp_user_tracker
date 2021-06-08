package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PartyEntityRepository extends JpaRepository<PartyEntity, Long> {
    PartyEntity findByReference(String ref);

    @Query(value = "select nextval('data_capture.party_id_seq')", nativeQuery = true)
    Long getNextValMySequence();
}