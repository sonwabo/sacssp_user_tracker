package com.technologies.xelo.model.repo;

import com.technologies.xelo.model.entities.AddressEntity;
import com.technologies.xelo.model.entities.AttachmentEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AttachmentRepository extends JpaRepository<AttachmentEntitiy, Long> {
    List<AttachmentEntitiy> findByParty(Long party);
}
