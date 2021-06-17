package com.technologies.xelo.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.*;


/**

 *
 * @author agile generator
 */
@ToString
@Entity
@Table(schema = "data_capture", name = "employment", catalog = "sacssp_data_capture")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class EmploymentEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(nullable = false, name = "jobtitle", columnDefinition="TEXT")
    private String jobtitle;

    @Basic
    @Column(nullable = false, name = "employername", columnDefinition="TEXT")
    private String employername;

    @Basic
    @Column(nullable = false, name = "sectorofemployment", columnDefinition="TEXT")
    private String sectorofemployment;

    @Basic
    @Column(nullable = false, name = "iscommunitypractitioner")
    private String iscommunitypractitioner;


    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "party_id")
    @ManyToOne
    private PartyEntity party_id;

    @OneToMany(mappedBy = "emp_id", cascade = {CascadeType.ALL})
    List<AddressEntity> addresses;

    @OneToMany(mappedBy = "emp_id", cascade = {CascadeType.ALL})
    List<ContactPointEntity> contactPoint;


    @Override
    public EmploymentEntity clone() {
        try {
            return (EmploymentEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
