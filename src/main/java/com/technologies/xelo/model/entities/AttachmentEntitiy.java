package com.technologies.xelo.model.entities;

import com.technologies.xelo.dto.AttachmentDTO;
import com.technologies.xelo.enums.DocType;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/29
 * @TIME 04:50
 */
@ToString
@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(schema = "data_capture", name = "attachement", catalog = "sacssp_data_capture")
@Builder
@AllArgsConstructor
public class AttachmentEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "attachmentname", columnDefinition="TEXT")
    private String attachmentname;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Basic
    @Column(name = "createdate", nullable = false)
    private Timestamp createdate;

    @Basic
    @Column(name = "size")
    private Long size;

    @Basic
    @Column(name = "contenttype", columnDefinition="TEXT" )
    private String contenttype;

    @Basic
    @Enumerated(EnumType.STRING)
    DocType status;

    @Basic
    @Column(name = "documenttype")
    private String documenttype;

//    @ToString.Exclude
//    @JoinColumn(name = "party_id")
//    @ManyToOne
    @Basic
    @Column(name = "party", nullable = false)
    private Long party;

    @Transient
    public AttachmentDTO convertToDto(){
        return AttachmentDTO.builder()
                .name(this.attachmentname)
                .reference(String.valueOf(this.party))
                .content(this.content)
                .contentType(this.contenttype)
                .size(this.size)
                .documentType(this.documenttype)
                .id(this.id)
                .build();
    }

    @Override
    public String toString() {
        return "AttachmentEntitiy{" +
                "id=" + id +
                ", attachmentname='" + attachmentname + '\'' +
                ", content=" + Arrays.toString(content) +
                ", createdate=" + createdate +
                ", size=" + size +
                ", contenttype='" + contenttype + '\'' +
                ", status=" + status +
                ", documenttype='" + documenttype + '\'' +
                ", party=" + party +
                '}';
    }
}
