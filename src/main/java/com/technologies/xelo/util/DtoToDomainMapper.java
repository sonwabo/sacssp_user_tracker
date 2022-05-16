package com.technologies.xelo.util;

import com.technologies.xelo.dto.UserDetailsDTO;
import com.technologies.xelo.enums.*;
import com.technologies.xelo.model.entities.*;
import java.util.Collections;
import org.springframework.util.StringUtils;

import javax.persistence.Basic;
import javax.persistence.Column;


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

                .nameofhighestcommdevqualification(userDetails.nameofhighestcommdevqualification)
                .internationalcountry(userDetails.internationalcountry)
                .internationalinstitutionname(userDetails.internationalinstitutionname)
                .internationalcompletionyear(userDetails.internationalcompletionyear)
                .internationalinstitutionemail(userDetails.internationalinstitutionemail)
                .internationalqualificationname(userDetails.internationalqualificationname)
                .internationalsaqavalidation(userDetails.internationalsaqavalidation)
                .internationalsaqaverification(userDetails.internationalsaqaverification)
                .partyType(PartyType.PERSON)
                .build();
    }

    public static UserDetailsDTO mapPartyToDto(PartyEntity party) {

        String ethnicalStatus = (party.getEthnicalstatus() != null) ? party.getEthnicalstatus().split(",")[0] : "";
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
                .ethnicalstatus(ethnicalStatus)
                .disabilitystatus(party.getDisabilitystatus())
                .maritalstatus(party.getMaritalstatus())
                .language(party.getLanguage())

                .nameofhighestcommdevqualification(party.getNameofhighestcommdevqualification())
                .internationalcountry(party.getInternationalcountry())
                .internationalinstitutionname(party.getInternationalinstitutionname())
                .internationalcompletionyear(party.getInternationalcompletionyear())
                .internationalinstitutionemail(party.getInternationalinstitutionemail())
                .internationalqualificationname(party.getInternationalqualificationname())
                .internationalsaqavalidation(party.getInternationalsaqavalidation())
                .internationalsaqaverification(party.getInternationalsaqaverification())

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
                if (StringUtils.hasText(userDetails.institutiontel)) {
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
        EmploymentEntity employer = EmploymentEntity.builder()
                .employername(userDetails.employername)
                .jobtitle(userDetails.jobtitle)
                .sectorofemployment(userDetails.sectorofemployment)
                .iscommunitypractitioner(userDetails.iscommunitypractitioner)
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
        userDetails.iscommunitypractitioner = employment.getIscommunitypractitioner();
        mapAddressToDto(employment.getAddresses().get(0), userDetails, Type.EMPLOYMENT);
        mapContactPointToDto(employment.getContactPoint().get(0), userDetails, Type.EMPLOYMENT);
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

    public static PartyEntity mapPartyDtoToDomain(PartyEntity party, UserDetailsDTO user) {

        party.setTitle(user.title);
        party.setFirstname(user.name1);
        party.setSecondname(user.name2);
        party.setThirdname(user.name3);
        party.setSurname(user.surname);
        party.setMaidensurname(user.maidensurname);
        party.setGender(Gender.fromString(user.gender));
        party.setEthnicalstatus(user.ethnicalstatus);
        party.setDisabilitystatus(user.disabilitystatus);
        party.setMaritalstatus(user.maritalstatus);
        party.setLanguage(user.language);

        party.setNameofhighestcommdevqualification( user.nameofhighestcommdevqualification);
        party.setInternationalcountry(user.internationalcountry);
        party.setInternationalinstitutionname(user.internationalinstitutionname);
        party.setInternationalcompletionyear(user.internationalcompletionyear);
        party.setInternationalinstitutionemail(user.internationalinstitutionemail);
        party.setInternationalqualificationname(user.internationalqualificationname);
        party.setInternationalsaqavalidation(user.internationalsaqavalidation);
        party.setInternationalsaqaverification(user.internationalsaqaverification);

        ContactPointEntity cp = party.getContactPoint().iterator().next();
        cp.getEmail().iterator().next().setEmail1(user.email);
        cp.getTelephone().iterator().next().setTel1(user.worknumber);
        cp.getTelephone().iterator().next().setCell1(user.cellphone);


        return party;
    }

    public static PartyEntity mapAddressDtoToDomain(PartyEntity party, UserDetailsDTO user) {

        AddressEntity entity = party.getAddresses().iterator().next();
        entity.setPostalcode(user.postalcode);
        entity.setProvince(user.provincename);
        entity.setStreetnumberandname(user.streetnumberandname);
        entity.setHouseorunitnumberorbusinesspasrkname(user.houseorcomplexnameandnumber);
        entity.setSuburbname(user.suburbname);
        entity.setTown(user.townname);
        return party;
    }

    public static PartyEntity mapEmploymentHistoryDtoToDomain(PartyEntity party, UserDetailsDTO user) {

        EmploymentEntity employmentHistory = party.getEmploymentHistory().iterator().next();
        employmentHistory.setEmployername(user.employername);
        employmentHistory.setJobtitle(user.jobtitle);
        employmentHistory.setSectorofemployment(user.sectorofemployment);
        employmentHistory.setIscommunitypractitioner(user.iscommunitypractitioner);

        AddressEntity empAddress = employmentHistory.getAddresses().iterator().next();

        empAddress.setPostalcode(user.emppostalcode);
        empAddress.setProvince(user.empprovincename);
        empAddress.setStreetnumberandname(user.empstreetnameandnumber);
        empAddress.setHouseorunitnumberorbusinesspasrkname(user.empbusinessparknameorunitname);
        empAddress.setSuburbname(user.empsuburbname);
        empAddress.setTown(user.emptownname);

        ContactPointEntity emp = employmentHistory.getContactPoint().iterator().next();
        emp.getEmail().iterator().next().setEmail1(user.empemail);
        emp.getTelephone().iterator().next().setTel1(user.empworknumber);
        emp.getTelephone().iterator().next().setCell1(user.empcellphone);

        return party;
    }

    public static PartyEntity mapEducationistoryDtoToDomain(PartyEntity party, UserDetailsDTO user) {

        EducationEntity education = party.getEducationEntities().iterator().next();
        education.setDurationofcourse(user.durationofcourse);
        education.setInstitutionName(user.institutionname);
        education.setQualificationName(user.qualificationname);
        education.setYearcompleted(user.yearcompleted);
        education.setQualificationlevel(user.qualificationlevel);
        education.setUniversityobtained(user.universityobtained);

        ContactPointEntity cp = education.getContactPoint().iterator().next();
        cp.getTelephone().iterator().next().setTel1(user.institutiontel);
        return party;
    }
}