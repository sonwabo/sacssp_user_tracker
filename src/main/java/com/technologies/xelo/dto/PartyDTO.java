package com.technologies.xelo.dto;

import com.technologies.xelo.enums.Gender;
import com.technologies.xelo.enums.PartyType;
import com.technologies.xelo.model.entities.PartyEntity;
import lombok.*;

import javax.persistence.*;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/25
 * @TIME 11:59
 */
@Builder
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PartyDTO {

    private Long id;
    private String title;
    private String firstname;
    private String secondname;
    private String thirdname;
    private String surname;
    private String maidensurname;
    private Gender gender;
    private String disabilitystatus;
    private String language;
    private String maritalstatus;
    private String ethnicalstatus;
    private String organisationname;

    private PartyType partyType;


    public PartyEntity convertToModel(){
        return  PartyEntity.builder()
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
}
