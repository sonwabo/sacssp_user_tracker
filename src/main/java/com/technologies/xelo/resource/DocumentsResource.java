package com.technologies.xelo.resource;

import com.technologies.xelo.dto.AttachmentDTO;
import com.technologies.xelo.intf.DocumentService;
import com.technologies.xelo.model.entities.AttachmentEntitiy;
import com.technologies.xelo.model.entities.PartyEntity;
import com.technologies.xelo.model.repo.AttachmentRepository;
import com.technologies.xelo.model.repo.PartyEntityRepository;
import com.technologies.xelo.service.PartyManagerImpl;
import com.technologies.xelo.util.SheetsGenerator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/05/03
 * @TIME 04:59
 */
@RestController
public class DocumentsResource {

    private static Log logger = LogFactory.getLog(DocumentsResource.class);


    private final DocumentService documentService;
    private final SheetsGenerator sheetsGenerator;
    private final String fileNameAndLocation;
    private final int BUFFER_SIZE = 1024;

    public DocumentsResource(DocumentService documentService, SheetsGenerator sheetsGenerator, @Value("${sheets.location}") String fileNameAndLocation) {
        this.documentService = documentService;
        this.sheetsGenerator = sheetsGenerator;
        this.fileNameAndLocation = fileNameAndLocation;

    }

    @PostMapping("/v1/uploadAttachment")
    public AttachmentDTO uploadFile(@RequestBody AttachmentDTO attachment) {
        try {
            return this.documentService.saveAttachment(attachment);
        } catch (Exception e) {
        }
        return null;
    }

    @PostMapping("/v1/deleteAttachment")
    public Map<String, String> deleteFile(@RequestBody AttachmentDTO attachment) {
        try {
            final String response = this.documentService.deleteAttachment(attachment);
            return new HashMap<String, String >() {{
                put("response", response);
            }};
        } catch (Exception e) {
        }
        return null;
    }

    @GetMapping("/v1/download/file")
    public ResponseEntity<byte[]> getFile(@RequestParam(value = "id") Long id) {
        AttachmentEntitiy fileDB = this.documentService.getAttachment(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getAttachmentname() + "\"")
                .body(fileDB.getContent());
    }

    @GetMapping("/v1/getPartyAttachments")
    public Map<String, List<AttachmentDTO>> getAllAttachmentsForParty(@RequestParam(value = "reference") String reference) {
        List<AttachmentDTO> attachments = this.documentService.getAllAttachmentsForParty(reference);
        return new HashMap<String, List<AttachmentDTO>>() {{
            put("attachments", attachments);
        }};
    }



    @GetMapping("/v1/file/{page}/{size}/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadPDFResource(HttpServletRequest request, HttpServletResponse response,
                                                        @PathVariable("page") Integer page,
                                                        @PathVariable("size") Integer size,
                                                        @PathVariable("fileName") String fileName) throws IOException {

        logger.info("********************** File Name Generated ******************** ");
        logger.info( fileName );
        fileName = "UserData.xls";
        if (this.sheetsGenerator.generateSheets(page, size)) {
            Path file = Paths.get(this.fileNameAndLocation);
            Resource resource = new UrlResource(file.toUri());
            return (ResponseEntity<Resource>) ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(file))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        }
        return null;
    }
}
