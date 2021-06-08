package com.technologies.xelo.util;

import com.technologies.xelo.dto.UserDetailsDTO;
import com.technologies.xelo.enums.*;
import com.technologies.xelo.model.entities.*;
import java.util.Collections;
import org.springframework.util.StringUtils;


/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/29
 * @TIME 08:01
 */
public class DtoToDomainMapper {


    public static PartyEntity mapPartyToDomain(UserDetailsDTO userDetails) {
        return PartyEntity.builder()
                .id(userDetails.userId)
                .title(userDetails.title)
                .firstname(userDetails.name1)
                .secondname(userDetails.name2)
                .thirdname(userDetails.name3)
                .surname(userDetails.surname)
                .maidensurname(userDetails.maidensurname)
                .gender(Gender.fromString(userDetails.gender))
                .ethnicalstatus(userDetails.ethnicalstatus)
                .disabilitystatus(userDetails.disabilitystatus)
                .maritalstatus(userDetails.maritalstatus)
                .language(userDetails.language)
                .partyType(PartyType.PERSON)
                .build();
    }

    public static UserDetailsDTO mapPartyToDto(PartyEntity party) {
        return UserDetailsDTO.builder()
                .userId(party.getId())
                .reference(party.getReference())
                .title(party.getTitle())
                .name1(party.getFirstname())
                .name2(party.getSecondname())
                .name3(party.getThirdname())
                .surname(party.getSurname())
                .maidensurname(party.getMaidensurname())
                .gender(party.getGender().name())
                .ethnicalstatus(party.getEthnicalstatus())
                .disabilitystatus(party.getDisabilitystatus())
                .maritalstatus(party.getMaritalstatus())
                .language(party.getLanguage())
                .build();
    }

    public static IdentificationEntity mapIdentificationToDomain(UserDetailsDTO userDetails) {
        IdentificationEntity identity = IdentificationEntity.builder()
                .citizenship(userDetails.citizenship)
                .birthdate(userDetails.birthdate)
                .identification(userDetails.idnumber)
                .passport(userDetails.passport)
                .build();

        if (StringUtils.hasText(userDetails.idnumber) && StringUtils.hasText(userDetails.passport)) {
            identity.setIdentificationType(IdentificationType.SA_ID_BOOK);
        } else if (StringUtils.hasText(userDetails.idnumber)) {
            identity.setIdentificationType(IdentificationType.SA_ID_BOOK);
        } else {
            identity.setIdentificationType(IdentificationType.PASSPORT);
        }
        return identity;
    }

    public static UserDetailsDTO mapIdentificationToDto(IdentificationEntity identity, UserDetailsDTO user) {
        user.citizenship = identity.getCitizenship();
        user.birthdate = identity.getBirthdate();
        user.idnumber = identity.getIdentification();
        user.passport = identity.getPassport();
        return user;
    }

    public static AddressEntity mapAddressToDomain(UserDetailsDTO userDetails, Type type) {

        AddressEntity addressEntity = AddressEntity.builder().build();
        switch (type) {
            case HOME:
            case PERSONAL:
                addressEntity.setPostalcode(userDetails.postalcode);
                addressEntity.setProvince(userDetails.provincename);
                addressEntity.setStreetnumberandname(userDetails.streetnumberandname);
                addressEntity.setHouseorunitnumberorbusinesspasrkname(userDetails.houseorcomplexnameandnumber);
                addressEntity.setSuburbname(userDetails.suburbname);
                addressEntity.setTown(userDetails.townname);
                addressEntity.setKind(Kind.PERSONAL);
                addressEntity.setCountry("South Africa");
                break;
            case EDUCATION:
                break;
            case EMPLOYMENT:
                addressEntity.setPostalcode(userDetails.emppostalcode);
                addressEntity.setProvince(userDetails.empprovincename);
                addressEntity.setStreetnumberandname(userDetails.empstreetnameandnumber);
                addressEntity.setHouseorunitnumberorbusinesspasrkname(userDetails.empbusinessparknameorunitname);
                addressEntity.setSuburbname(userDetails.empsuburbname);
                addressEntity.setTown(userDetails.emptownname);
                addressEntity.setKind(Kind.EMPLOYMENT);
                addressEntity.setCountry("South Africa");
                break;
        }
        return addressEntity;
    }

    public static UserDetailsDTO mapAddressToDto(AddressEntity address, UserDetailsDTO user, Type type) {

        switch (type) {
            case HOME:
            case PERSONAL:
                user.postalcode = address.getPostalcode();
                user.provincename = address.getProvince();
                user.streetnumberandname = address.getStreetnumberandname();
                user.houseorcomplexnameandnumber = address.getHouseorunitnumberorbusinesspasrkname();
                user.suburbname = address.getSuburbname();
                user.townname = address.getTown();
            case EMPLOYMENT:
                user.emppostalcode = address.getPostalcode();
                user.empprovincename = address.getProvince();
                user.empstreetnameandnumber = address.getStreetnumberandname();
                user.empbusinessparknameorunitname = address.getHouseorunitnumberorbusinesspasrkname();
                user.empsuburbname = address.getSuburbname();
                user.emptownname = address.getTown();
        }
        return user;
    }

    public static ContactPointEntity mapContactPointToDomain(UserDetailsDTO userDetails, Type type) {

        ContactPointEntity contactPointEntity = ContactPointEntity.builder().build();
        TelephoneEntity telephoneEntity = null;
        EmailEntity emailEntity = null;

        switch (type) {
            case HOME:
            case PERSONAL:
                contactPointEntity.setContactpointtype(Type.PERSONAL);

                if (StringUtils.hasText(userDetails.cellphone) || StringUtils.hasText(userDetails.worknumber)) {
                    telephoneEntity = TelephoneEntity.builder().
                            contact_point_id(contactPointEntity)
                            .cell1(userDetails.cellphone)
                            .tel1(userDetails.worknumber)
                            .build();
                    contactPointEntity.setTelephone(Collections.singletonList(telephoneEntity));
                }
                if (StringUtils.hasText(userDetails.email)) {
                    emailEntity = EmailEntity.builder()
                            .contact_point_id(contactPointEntity)
                            .email1(userDetails.email).build();
                    contactPointEntity.setEmail(Collections.singletonList(emailEntity));
                }
                break;
            case EDUCATION:

                contactPointEntity.setContactpointtype(Type.EDUCATION);
                if(StringUtils.hasText(userDetails.institutiontel)){
                    telephoneEntity = TelephoneEntity.builder()
                            .contact_point_id(contactPointEntity)
                            .tel1(userDetails.institutiontel).build();
                    contactPointEntity.setTelephone(Collections.singletonList(telephoneEntity));
                }
                break;
            case EMPLOYMENT:
                contactPointEntity.setContactpointtype(Type.EMPLOYMENT);

                if (StringUtils.hasText(userDetails.empcellphone) || StringUtils.hasText(userDetails.empworknumber)) {
                    telephoneEntity = TelephoneEntity.builder().
                            contact_point_id(contactPointEntity)
                            .cell1(userDetails.empcellphone)
                            .tel1(userDetails.empworknumber)
                            .build();
                    contactPointEntity.setTelephone(Collections.singletonList(telephoneEntity));
                }
                if (StringUtils.hasText(userDetails.empemail)) {
                    emailEntity = EmailEntity.builder()
                            .contact_point_id(contactPointEntity)
                            .email1(userDetails.empemail).build();
                    contactPointEntity.setEmail(Collections.singletonList(emailEntity));
                }
                break;
        }
        return contactPointEntity;
    }

    public static UserDetailsDTO mapContactPointToDto(ContactPointEntity contactPoint, UserDetailsDTO user, Type type) {

        TelephoneEntity telephoneEntity = contactPoint.getTelephone().get(0);
        EmailEntity emailEntity = contactPoint.getEmail().get(0);
        switch (type) {
            case HOME:
            case PERSONAL:
                user.cellphone = telephoneEntity.getCell1();
                user.worknumber = telephoneEntity.getTel1();
                user.email = emailEntity.getEmail1();
                break;
            case EDUCATION:
                break;
            case EMPLOYMENT:
                user.empcellphone = telephoneEntity.getCell1();
                user.empworknumber = telephoneEntity.getTel1();
                user.empemail = emailEntity.getEmail1();
                break;
        }
        return user;
    }

    public static DisclaimerEntity mapDisclaimerToDomain(UserDetailsDTO userDetails) {
        return DisclaimerEntity.builder()
                .disclaimerstatus(userDetails.disclaimer)
                .build();
    }

    public static UserDetailsDTO mapDisclaimerToDto(DisclaimerEntity disclaimer, UserDetailsDTO userDetailsDTO) {
        userDetailsDTO.disclaimer = disclaimer.getDisclaimerstatus();
        return userDetailsDTO;
    }

    public static EmploymentEntity mapEmployerToDomain(UserDetailsDTO userDetails) {
        EmploymentEntity employer =  EmploymentEntity.builder()
                .employername(userDetails.employername)
                .jobtitle(userDetails.jobtitle)
                .sectorofemployment(userDetails.sectorofemployment)
                .build();

        AddressEntity addressEntity = mapAddressToDomain(userDetails, Type.EMPLOYMENT);
        addressEntity.setEmp_id(employer);
        employer.setAddresses(Collections.singletonList(addressEntity));
        ContactPointEntity contactPointEntity = mapContactPointToDomain(userDetails, Type.EMPLOYMENT);
        contactPointEntity.setEmp_id(employer);
        employer.setContactPoint(Collections.singletonList(contactPointEntity));
        return employer;
    }

    public static UserDetailsDTO mapEmployerToDto(EmploymentEntity employment, UserDetailsDTO userDetails) {
        userDetails.employername = employment.getEmployername();
        userDetails.jobtitle = employment.getJobtitle();
        userDetails.sectorofemployment = employment.getSectorofemployment();
        mapAddressToDto(employment.getAddresses().get(0), userDetails, Type.EMPLOYMENT);
        mapContactPointToDto(employment.getContactPoint().get(0),userDetails, Type.EMPLOYMENT );
        return userDetails;
    }

    public static EducationEntity mapEducationToDomain(UserDetailsDTO userDetails) {

        EducationEntity education = EducationEntity.builder()
                .institutionName(userDetails.institutionname)
                .qualificationName(userDetails.qualificationname)
                .qualificationlevel(userDetails.qualificationlevel)
                .durationofcourse(userDetails.durationofcourse)
                .yearcompleted(userDetails.yearcompleted)
                .universityobtained(userDetails.universityobtained)
                .build();
         ContactPointEntity contactPoint = mapContactPointToDomain(userDetails, Type.EDUCATION);
         contactPoint.setEducation_id(education);
         education.setContactPoint(Collections.singletonList(contactPoint));
        return education;
    }

    public static UserDetailsDTO mapEducationToDto(EducationEntity entity, UserDetailsDTO userDetails) {
        ContactPointEntity contactPointEntity = entity.getContactPoint().get(0);
        TelephoneEntity telephoneEntity = (!contactPointEntity.getTelephone().isEmpty())
                ? contactPointEntity.getTelephone().get(0) : null;

        userDetails.institutionname = entity.getInstitutionName();
        if (telephoneEntity != null) {
            userDetails.institutiontel = telephoneEntity.getTel1();
        }
        userDetails.universityobtained = entity.getUniversityobtained();
        userDetails.yearcompleted = entity.getYearcompleted();
        userDetails.durationofcourse = entity.getDurationofcourse();
        userDetails.qualificationlevel = entity.getQualificationlevel();
        userDetails.qualificationname = entity.getQualificationName();
        return userDetails;
    }

}