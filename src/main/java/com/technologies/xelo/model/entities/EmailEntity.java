package com.technologies.xelo.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

/**

 *
 * @author agile generator
 */
@ToString
@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
@Table(schema = "data_capture", name = "email", catalog = "sacssp_data_capture")
public class EmailEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "email1", columnDefinition="TEXT")
    private String email1;
    @Basic
    @Column(name = "email2", columnDefinition="TEXT")
    private String email2;

    @Basic
    @Column(name = "externalreference", columnDefinition="TEXT")
    private String externalRefere;

    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "contact_point_id", unique = true)
    @ManyToOne
    private ContactPointEntity contact_point_id;


    @Override
    public EmailEntity clone() {
        try {
            return (EmailEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
