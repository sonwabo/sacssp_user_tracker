package com.technologies.xelo.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.technologies.xelo.enums.PartyType;
import com.technologies.xelo.enums.Type;
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
@Table(schema = "data_capture", name = "contact_point", catalog = "sacssp_data_capture")
@Builder
@AllArgsConstructor
public class ContactPointEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "type", columnDefinition="TEXT")
    @Enumerated(EnumType.STRING)
    private Type contactpointtype;

    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "party_id", unique = true)
    @ManyToOne
    private PartyEntity party_id;

    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "education_id", unique = true)
    @ManyToOne
    private EducationEntity education_id;

    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "emp_id")
    @ManyToOne
    private EmploymentEntity emp_id;

    @OneToMany(mappedBy = "contact_point_id" , cascade = {CascadeType.ALL})
    List<TelephoneEntity> telephone;

    //@OneToMany(mappedBy = "contact_point_id" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OneToMany(mappedBy = "contact_point_id" , cascade = {CascadeType.ALL})
    List<EmailEntity> email;


    @Override
    public ContactPointEntity clone() {
        try {
            return (ContactPointEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
