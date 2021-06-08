package com.technologies.xelo.intf;

import com.technologies.xelo.dto.AttachmentDTO;
import com.technologies.xelo.model.entities.AttachmentEntitiy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {

    /**
     * Creates new Attachment to the data stor
     * @param attachment
     * @throws IOException
     */
    AttachmentDTO saveAttachment(AttachmentDTO attachment) throws IOException;

    /**
     * Delete attachment permanently
     * @param attachment
     * @return
     */
    String deleteAttachment(AttachmentDTO attachment);

    /**
     * Gets the Attachment by ID
     * @param id
     * @return
     */
    AttachmentEntitiy getAttachment(Long id);

    /**
     * Returns a List of Attachements attached to the given party reference
     * @param party
     * @return
     */
    List<AttachmentDTO>  getAllAttachmentsForParty(String party);

}
