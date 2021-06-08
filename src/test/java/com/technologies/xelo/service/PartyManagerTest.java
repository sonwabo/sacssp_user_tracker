package com.technologies.xelo.service;

import com.technologies.xelo.dto.PartyDTO;
import com.technologies.xelo.enums.*;
import com.technologies.xelo.intf.PartyManager;
import com.technologies.xelo.model.entities.*;
import com.technologies.xelo.model.repo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/25
 * @TIME 12:09
 */
@SpringBootTest
public class PartyManagerTest {

    @Autowired
    PartyManager partyManager;
    @Autowired
    PartyEntityRepository partyEntityRepository;

    @Autowired
    IdentificationEntityRepository identificationEntityRepository;
    @Autowired
    ContactPointEntityRepository contactPointEntityRepository;
    @Autowired
    AddressEntityRepository addressEntityRepository;
    @Autowired
    DisclaimerRepository disclaimerRepository;
    @Autowired
    EducationEntityRepository educationEntityRepository;
    @Autowired
    EmploymentEntityRepository employmentEntityRepository;


    @Test
    public void testCreateParty(){

        PartyDTO partyDTO = PartyDTO.builder()
                .title("Mr")
                .firstname("Sonwabo")
                .secondname("Olex")
                .thirdname("Lex")
                .surname("Singatha")
                .maidensurname("Msibi")
                .gender(Gender.MALE)
                .language("Xhosa")
                .ethnicalstatus("Black")
                .maritalstatus("Married")
                .disabilitystatus("None")
                .partyType(PartyType.PERSON)
                .build();

        partyDTO = partyManager.createParty(partyDTO);
        System.out.println("======== Party Created ========= ");
        System.out.println(partyDTO.toString());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCreatePartyWithId()throws Exception{

        PartyDTO partyType = partyManager.getPartyById(1L);

        IdentificationEntity identity = IdentificationEntity.builder()
                .identification("8904305263084")
                .identificationType(IdentificationType.SA_ID_BOOK)
                .birthdate("1989/04/30")
                .citizenship("South African")
                .party(partyType.convertToModel())
                .build();

        identity = identificationEntityRepository.save(identity);
        System.out.println(" ============== Identity Captured ============ ");
        System.out.println(identity.toString());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCreateDisclaimer() {
        PartyDTO partyDTO = partyManager.getPartyById(1L);
        DisclaimerEntity disclaimerEntity = DisclaimerEntity.builder().disclaimerstatus("YES").party(partyDTO.convertToModel()).build();
        disclaimerRepository.save( disclaimerEntity );
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testfindDisclaimerByPart() {
        PartyEntity partyEntity = partyEntityRepository.getOne(1L);
        DisclaimerEntity disclaimerEntity = partyEntity.getDisclaimer();
        System.out.println( disclaimerEntity.toString() );
    }


    @Test
    @Transactional
    public void testFindPartyByIdentification(){
        Optional<IdentificationEntity> partyType  = Optional.ofNullable(identificationEntityRepository.findByIdentification("8904305263084"));//findById(1L);
        System.out.println(" ============== Identity Captured ============ ");
        System.out.println(partyType.get().toString());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCreateContactPoints() {
        PartyDTO party = partyManager.getPartyById(1L);

        ContactPointEntity contactPointEntity = ContactPointEntity.builder()
                .party_id( party.convertToModel() )
                .contactpointtype(Type.PERSONAL)
                .build();

        contactPointEntity.setTelephone(Collections.singletonList(TelephoneEntity.builder().contact_point_id(contactPointEntity).cell1("0824866209").build()));
        contactPointEntity.setEmail(Collections.singletonList(EmailEntity.builder().contact_point_id(contactPointEntity).email1("s.singatha@gmail.com").build()));

        contactPointEntityRepository.save(contactPointEntity);
        System.out.println(" ============== Identity Captured ============ ");
        //System.out.println(contactPointEntity.get().toString());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testFindContactPoints() {

        ContactPointEntity contactPointEntity = contactPointEntityRepository.getOne(2L);
        TelephoneEntity telephoneEntity = contactPointEntity.getTelephone().get(0);
        EmailEntity emailEntity = contactPointEntity.getEmail().get(0);
        System.out.println("<<<<<<<<<<<<<< Email and Tel >>>>>>>>>>>>>>> ");
        System.out.println(telephoneEntity.toString());
        System.out.println(emailEntity.toString());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testCreateAddress() {
        PartyDTO party = partyManager.getPartyById(1L);

            AddressEntity addressEntity = AddressEntity.builder()
                    .country("SA")
                    .kind(Kind.PERSONAL)
                    .party_id(party.convertToModel())
                    .postalcode("1709")
                    .province("WC")
                    .houseorunitnumberorbusinesspasrkname("12 Shiraz Villas")
                    .streetnumberandname("Bukettraube Cl")
                    .suburbname("Oude Westhof")
                    .town("CapeTown")
                    .build();

            addressEntityRepository.save(addressEntity);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testfindAddress() {
        AddressEntity addressEntity = addressEntityRepository.getOne(1L);
        System.out.println(addressEntity.toString());
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testcreateEducatoinHistory() {
        PartyDTO party = partyManager.getPartyById(1L);

        EducationEntity educationEntity = EducationEntity.builder().
                id(2L)
                .durationofcourse("Three Years")
                .institutionName("Jeppe College")
                .qualificationlevel("level 6")
                .qualificationName("Software Development")
                .yearcompleted("2011")
                .party_id(party.convertToModel())
        .build();

        ContactPointEntity contactPointEntity = ContactPointEntity.builder()
                .education_id(educationEntity)
                .contactpointtype(Type.EDUCATION)
                .build();

        contactPointEntity.setTelephone(Collections.singletonList(TelephoneEntity.builder().contact_point_id(contactPointEntity).cell1("0731747417").build()));
        contactPointEntity.setEmail(Collections.singletonList(EmailEntity.builder().contact_point_id(contactPointEntity).email1("sngson010@myuct.ac.za").build()));
        educationEntity.setContactPoint( Collections.singletonList(contactPointEntity) );

        AddressEntity addressEntity = AddressEntity.builder()
                .town("Pretoria")
                .suburbname("Central")
                .streetnumberandname("2 Andries Street")
                .province("Gauteng")
                .postalcode("4104")
                .kind(Kind.EDUCATION)
                .country("South Africa")
                .houseorunitnumberorbusinesspasrkname("Lesley Building")
                .education_id(educationEntity)
                .build();
        educationEntity.setAddress(Collections.singletonList(addressEntity));
        educationEntityRepository.save( educationEntity );
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testcreateEmploymentHistory() {

        PartyEntity partyEntity = partyEntityRepository.getOne(1L);
        EmploymentEntity employmentEntity = EmploymentEntity.builder().
                employername("")
        .build();

        ContactPointEntity contactPointEntity = ContactPointEntity.builder()
                .emp_id(employmentEntity)
                .contactpointtype(Type.EMPLOYMENT)
                .build();

        contactPointEntity.setTelephone(Collections.singletonList(TelephoneEntity.builder().contact_point_id(contactPointEntity).cell1("0219409988").build()));
        contactPointEntity.setEmail(Collections.singletonList(EmailEntity.builder().contact_point_id(contactPointEntity).email1("s.singatha@tsctech.com").build()));
        employmentEntity.setContactPoint( Collections.singletonList(contactPointEntity) );

        AddressEntity addressEntity = AddressEntity.builder()
                .town("Johannesburg")
                .suburbname("Fairland")
                .streetnumberandname("14 Avenue")
                .province("Gauteng")
                .postalcode("0121")
                .kind(Kind.EMPLOYMENT)
                .country("South Africa")
                .houseorunitnumberorbusinesspasrkname("Lesley Building")
                .emp_id(employmentEntity)
                .build();
        employmentEntity.setAddresses(Collections.singletonList(addressEntity));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findByReference() {
        PartyEntity partyEntity = partyEntityRepository.findByReference("a627dff6-7952-47d1-99c7-928a43cb7db4");
        System.out.println("<<<<<<<<<<<<< ONE >>>>>>>>>>>>>");
        System.out.println(partyEntity.toString());
    }

}
