package com.technologies.xelo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/05/03
 * @TIME 05:52
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDTO {

    public Long id;
    public String reference;
    public String name;
    public byte[] content;
    public String contentType;
    public String documentType;
    public Long size;

}
