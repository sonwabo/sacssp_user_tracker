package com.technologies.xelo.model.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.technologies.xelo.dto.PartyDTO;
import com.technologies.xelo.enums.Gender;
import com.technologies.xelo.enums.Kind;
import com.technologies.xelo.enums.PartyType;
import lombok.*;

/**

 *
 * @author agile generator
 */
@ToString
@Entity
@Table(schema = "data_capture", name = "party", catalog = "sacssp_data_capture")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor
public class PartyEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Column(nullable = false, name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(nullable = false, name = "title", columnDefinition="TEXT")
    private String title;

    @Basic
    @Column(nullable = false, name = "firstname", columnDefinition="TEXT")
    private String firstname;

    @Basic
    @Column(nullable = true, name = "secondname",columnDefinition="TEXT")
    private String secondname;

    @Basic
    @Column(nullable = true, name = "thirdname", columnDefinition="TEXT")
    private String thirdname;

    @Column(nullable = false, name = "surname", columnDefinition="TEXT")
    @Basic
    private String surname;

    @Column(nullable = true, name = "maidensurname", columnDefinition="TEXT")
    @Basic
    private String maidensurname;


    @Column(nullable = false, name = "gender", length = 50)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, name = "disabilitystatus", columnDefinition="TEXT")
    @Basic
    private String disabilitystatus;

    @Column(nullable = false, name = "language", columnDefinition="TEXT")
    @Basic
    private String language;

    @Column(nullable = false, name = "maritalstatus",columnDefinition="TEXT")
    @Basic
    private String maritalstatus;

    @Column(nullable = false, name = "ethnicalstatus",columnDefinition="TEXT")
    @Basic
    private String ethnicalstatus;

    @Column(nullable = true, name = "organisationname",columnDefinition="TEXT")
    @Basic
    private String organisationname;

    @Column(nullable = false, name = "reference", length = 100, unique = true, columnDefinition="TEXT")
    @Basic
    private String reference;

    @Column(nullable = false, name = "nameofhighestcommdevqualification", length = 100, columnDefinition="TEXT")
    @Basic
    public String nameofhighestcommdevqualification;

    @Column(nullable = false, name = "internationalcountry", length = 100, columnDefinition="TEXT")
    @Basic
    public String internationalcountry;

    @Column(nullable = false, name = "internationalinstitutionname", length = 100,columnDefinition="TEXT")
    @Basic
    public String  internationalinstitutionname;

    @Column(nullable = false, name = "internationalcompletionyear", length = 100, columnDefinition="TEXT")
    @Basic
    public String internationalcompletionyear;

    @Column(nullable = false, name = "internationalinstitutionemail", length = 100, columnDefinition="TEXT")
    @Basic
    public String  internationalinstitutionemail;

    @Column(nullable = false, name = "internationalqualificationname", length = 100, columnDefinition="TEXT")
    @Basic
    public String  internationalqualificationname;

    @Column(nullable = false, name = "internationalsaqavalidation", length = 100, columnDefinition="TEXT")
    @Basic
    public String  internationalsaqavalidation;
    @Column(nullable = false, name = "internationalsaqaverification", length = 100, columnDefinition="TEXT")
    @Basic
    public String  internationalsaqaverification;

    @Column(nullable = true, name = "partytype", length = 50)
    @Enumerated(EnumType.STRING)
    private PartyType partyType;

    @OneToMany(mappedBy = "party_id", cascade = {CascadeType.ALL})
    List<AddressEntity> addresses;

    @OneToMany(mappedBy = "party_id" ,cascade = {CascadeType.ALL})
    List<EmploymentEntity> employmentHistory;

    @OneToMany(mappedBy = "party_id" , cascade = {CascadeType.ALL})
    List<ContactPointEntity> contactPoint;

//    @OneToMany(mappedBy = "party_id" , cascade = {CascadeType.ALL})
//    List<AttachmentEntitiy> attachments;

    @OneToMany(mappedBy = "party_id" , cascade = {CascadeType.ALL})
    List<EducationEntity> educationEntities;


    @OneToOne(mappedBy = "party", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private IdentificationEntity identification;

    @OneToOne(mappedBy = "party", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private DisclaimerEntity disclaimer;



    @Transient
    public PartyDTO convertToDto(){
        return PartyDTO.builder()
                .id(this.id)
                .title(this.title)
                .firstname(this.firstname)
                .secondname(this.secondname)
                .thirdname(this.thirdname)
                .surname(this.surname)
                .maidensurname(this.maidensurname)
                .gender(this.gender)
                .disabilitystatus(this.disabilitystatus)
                .language(this.language)
                .maritalstatus(this.maritalstatus)
                .ethnicalstatus(this.ethnicalstatus)
                .organisationname(this.organisationname)
                .partyType(this.partyType)
                .build();
    }

    @Override
    public PartyEntity clone() {
        try {
            return (PartyEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            return this;
        }
    }
}
