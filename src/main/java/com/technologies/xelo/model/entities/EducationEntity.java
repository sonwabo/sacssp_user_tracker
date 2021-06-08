package com.technologies.xelo.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import lombok.*;

/**

 *
 * @author agile generator
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(schema = "data_capture", name = "education", catalog = "sacssp_data_capture")
@AllArgsConstructor
public class EducationEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(nullable = false, name = "institutionName", columnDefinition="TEXT")
    private String institutionName;
    @Basic
    @Column(nullable = false, name = "qualificationName", columnDefinition="TEXT")
    private String qualificationName;
    @Basic
    @Column(nullable = false, name = "universityobtained", columnDefinition="TEXT")
    private String universityobtained;
    @Basic
    @Column(nullable = false, name = "qualificationlevel", columnDefinition="TEXT")
    private String qualificationlevel;
    @Basic
    @Column(nullable = false, name = "durationofcourse", columnDefinition="TEXT")
    private String durationofcourse;
    @Basic
    @Column(nullable = false, name = "yearcompleted", columnDefinition="TEXT")
    private String yearcompleted;

    @OneToMany(mappedBy = "education_id", cascade = {CascadeType.ALL})
    List<ContactPointEntity> contactPoint;

    @OneToMany(mappedBy = "education_id", cascade = {CascadeType.ALL})
    List<AddressEntity> address;


    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "party_id", unique = true)
    @ManyToOne
    private PartyEntity party_id;


    @Override
    public EducationEntity clone() {
        try {
            return (EducationEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
