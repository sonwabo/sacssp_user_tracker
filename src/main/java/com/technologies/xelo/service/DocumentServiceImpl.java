package com.technologies.xelo.service;

import com.technologies.xelo.dto.AttachmentDTO;
import com.technologies.xelo.intf.DocumentService;
import com.technologies.xelo.model.entities.AttachmentEntitiy;
import com.technologies.xelo.model.entities.PartyEntity;
import com.technologies.xelo.model.repo.AttachmentRepository;
import com.technologies.xelo.model.repo.PartyEntityRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/05/03
 * @TIME 05:08
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    private static Log logger = LogFactory.getLog(DocumentServiceImpl.class);

    private final AttachmentRepository attachmentRepository;
    private final PartyEntityRepository partyEntityRepository;

    public DocumentServiceImpl(AttachmentRepository attachmentRepository, PartyEntityRepository partyEntityRepository) {
        this.attachmentRepository = attachmentRepository;
        this.partyEntityRepository = partyEntityRepository;
    }

    @Transactional
    public AttachmentDTO saveAttachment(AttachmentDTO dto) throws IOException {
        logger.info("============= Saving Documents ============= ");
        logger.info("Reference" + dto.getReference());
        PartyEntity party = this.partyEntityRepository.findById(Long.parseLong(dto.getReference())).get();
        logger.info("Found Party " + party.getId());
        AttachmentEntitiy attachment = transform(dto);
        attachment.setParty(party.getId());
        logger.info(attachment.toString());
        dto = this.attachmentRepository.save(attachment).convertToDto();
        return dto;
    }

    @Transactional
    public String deleteAttachment(AttachmentDTO dto){
        AttachmentEntitiy attachment = transform(dto);
        this.attachmentRepository.delete(attachment);
        return "success";
    }

    public AttachmentEntitiy getAttachment(Long id) {
        return this.attachmentRepository.findById(id).get();
    }

    @Transactional
    public List<AttachmentDTO> getAllAttachmentsForParty(String party) {
        return this.attachmentRepository
                .findByParty( Long.parseLong(party))
                .stream().map(AttachmentEntitiy::convertToDto).collect(Collectors.toList());
    }

    private AttachmentEntitiy transform(AttachmentDTO dto) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(dto.getName()));
        return AttachmentEntitiy.builder()
                .id(dto.getId())
                .attachmentname(fileName)
                .createdate(Timestamp.from(new Date().toInstant()))
                //.party(Long.parseLong(dto.getReference()))
                .content(dto.getContent())
                .size(dto.getSize())
                .documenttype(dto.getDocumentType())
                .contenttype(dto.getContentType())
                .build();
    }
}
