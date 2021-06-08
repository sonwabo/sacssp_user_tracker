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
@Table(schema = "data_capture", name = "telephone", catalog = "sacssp_data_capture")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class TelephoneEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tel1", length = 50)
    @Basic
    private String tel1;

    @Column(name = "tel2", length = 50)
    @Basic
    private String tel2;

    @Column(name = "cell1", length = 50)
    @Basic
    private String cell1;

    @Column(name = "cell2", length = 50)
    @Basic
    private String cell2;


    @ToString.Exclude
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "contact_point_id", unique = true)
    @ManyToOne
    private ContactPointEntity contact_point_id;


    @Override
    public TelephoneEntity clone() {
        try {
            return (TelephoneEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
