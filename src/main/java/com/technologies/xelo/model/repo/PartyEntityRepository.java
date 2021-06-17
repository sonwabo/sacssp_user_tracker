package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface PartyEntityRepository extends JpaRepository<PartyEntity, Long> {
    Optional<PartyEntity> findByReference(String ref);

    //@Query(value = "select nextval('data_capture.party_id_seq')", nativeQuery = true)
    @Query(value = "select count(*) from data_capture.party",nativeQuery = true)
    Long getNextValMySequence();
}