package com.technologies.xelo.model.entities;

import com.technologies.xelo.enums.Kind;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author agile generator
 */
@ToString
@Table(schema = "data_capture", name = "address", catalog = "sacssp_data_capture")
@Entity
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class AddressEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(nullable = false, name = "province", columnDefinition="TEXT")
    private String province;

    @Basic
    @Column(name = "country",columnDefinition="TEXT")
    private String country;

    @Basic
    @Column(nullable = false, name = "postalcode", length = 50)
    private String postalcode;

    @Basic
    @Column(name = "houseorunitnumberorbusinesspasrkname", columnDefinition="TEXT")
    private String  houseorunitnumberorbusinesspasrkname;

    @Basic
    @Column(name = "streetnumberandname",columnDefinition="TEXT")
    private String streetnumberandname;


    @Basic
    @Column(name = "suburbname", columnDefinition="TEXT")
    private String suburbname;

    @Basic
    @Column(name = "town", columnDefinition="TEXT")
    private String town;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Kind kind;

    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "party_id")
    @ManyToOne
    private PartyEntity party_id;

    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "emp_id")
    @ManyToOne
    private EmploymentEntity emp_id;

    @ToString.Exclude
    @Setter(AccessLevel.PUBLIC)
    @JoinColumn(name = "education_id", unique = true)
    @ManyToOne
    private EducationEntity education_id;


    @Override
    public AddressEntity clone() {
        try {
            return (AddressEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
