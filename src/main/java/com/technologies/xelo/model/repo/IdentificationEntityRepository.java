package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.IdentificationEntity;
import com.technologies.xelo.model.entities.PartyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface IdentificationEntityRepository extends JpaRepository<IdentificationEntity, Long> {
     IdentificationEntity findByIdentification(String identification);
     IdentificationEntity findByPassport(String identification);

     IdentificationEntity findByParty_Id(Long partyId);
     @Query(value = "select count(*) from data_capture.identification i where i.citizenship= :value",nativeQuery = true)
     Long getIsSouthAfricanCitinzenCount(@Param(value = "value") String value);
}