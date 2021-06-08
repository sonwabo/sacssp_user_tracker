package com.technologies.xelo.service;

import com.technologies.xelo.dto.PartyDTO;
import com.technologies.xelo.dto.UserDetailsDTO;
import com.technologies.xelo.enums.EmailTemplate;
import com.technologies.xelo.enums.Type;
import com.technologies.xelo.intf.PartyManager;
import com.technologies.xelo.model.entities.*;
import com.technologies.xelo.model.repo.*;
import com.technologies.xelo.util.DtoToDomainMapper;

import java.util.*;
import java.util.stream.Collectors;
import javax.mail.MessagingException;
import javax.transaction.Transactional;

import com.technologies.xelo.util.EmailSender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/25
 * @TIME 11:51
 */

@Service
@Transactional
public class PartyManagerImpl implements PartyManager {

    private static Log logger = LogFactory.getLog(PartyManagerImpl.class);

    private final PartyEntityRepository partyEntityRepository;
    private final IdentificationEntityRepository identificationEntityRepository;
    private final ContactPointEntityRepository contactPointEntityRepository;
    private final AddressEntityRepository addressEntityRepository;
    private final DisclaimerRepository disclaimerRepository;
    private final EducationEntityRepository educationEntityRepository;
    private final EmploymentEntityRepository employmentEntityRepository;

    private final EmailSender emailSender;

    public PartyManagerImpl(PartyEntityRepository partyEntityRepository,
                            IdentificationEntityRepository identificationEntityRepository,
                            ContactPointEntityRepository contactPointEntityRepository,
                            AddressEntityRepository addressEntityRepository,
                            DisclaimerRepository disclaimerRepository,
                            EducationEntityRepository educationEntityRepository,
                            EmploymentEntityRepository employmentEntityRepository,
                            EmailSender emailSender
    ) {
        this.partyEntityRepository = partyEntityRepository;
        this.identificationEntityRepository = identificationEntityRepository;
        this.contactPointEntityRepository = contactPointEntityRepository;
        this.addressEntityRepository = addressEntityRepository;
        this.disclaimerRepository = disclaimerRepository;
        this.educationEntityRepository = educationEntityRepository;
        this.employmentEntityRepository = employmentEntityRepository;
        this.emailSender = emailSender;
    }




    @Override
    public PartyDTO createParty(PartyDTO party) {
        return partyEntityRepository.save(party.convertToModel()).convertToDto();
    }

    @Override
    public PartyDTO getPartyById(Long party) {
        return Optional.ofNullable(partyEntityRepository.getOne(party).convertToDto())
                .orElse(null);
    }

    @Override
    public UserDetailsDTO getUserDetailsByReference(String reference) {

        PartyEntity partyEntity = this.partyEntityRepository.findByReference(reference);
        if (partyEntity != null) {

            UserDetailsDTO user = DtoToDomainMapper.mapPartyToDto(partyEntity);
            DtoToDomainMapper.mapDisclaimerToDto(partyEntity.getDisclaimer(), user);
            DtoToDomainMapper.mapIdentificationToDto(partyEntity.getIdentification(), user);
            DtoToDomainMapper.mapAddressToDto(partyEntity.getAddresses().get(0), user, Type.PERSONAL);
            DtoToDomainMapper.mapContactPointToDto(partyEntity.getContactPoint().get(0), user, Type.PERSONAL);
            DtoToDomainMapper.mapEducationToDto(partyEntity.getEducationEntities().get(0), user);
            DtoToDomainMapper.mapEmployerToDto(partyEntity.getEmploymentHistory().get(0), user);
            return user;
        }
        return null;
    }


    @Override
    public List<PartyDTO> getAllUsers() {
        return partyEntityRepository
                .findAll()
                .stream()
                .map(PartyEntity::convertToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Page<UserDetailsDTO> getAllUsers(int page, int size) {

        return partyEntityRepository.findAll(PageRequest.of(page, size))
                .map(partyEntity -> {
                    UserDetailsDTO user = DtoToDomainMapper.mapPartyToDto(partyEntity);
                    DtoToDomainMapper.mapDisclaimerToDto(partyEntity.getDisclaimer(), user);
                    DtoToDomainMapper.mapIdentificationToDto(partyEntity.getIdentification(), user);
                    DtoToDomainMapper.mapAddressToDto(partyEntity.getAddresses().get(0), user, Type.PERSONAL);
                    DtoToDomainMapper.mapContactPointToDto(partyEntity.getContactPoint().get(0), user, Type.PERSONAL);
                    DtoToDomainMapper.mapEducationToDto(partyEntity.getEducationEntities().get(0), user);
                    DtoToDomainMapper.mapEmployerToDto(partyEntity.getEmploymentHistory().get(0), user);
                    return user;
                });
    }

    @Override
    @Transactional
    public String createOrUpdateUser(UserDetailsDTO user) {

        PartyEntity partyEntity = DtoToDomainMapper.mapPartyToDomain(user);
        final String reference = UUID.randomUUID().toString();
        partyEntity.setReference(reference);
        partyEntity = partyEntityRepository.save(partyEntity);

        DisclaimerEntity disclaimerEntity = DtoToDomainMapper.mapDisclaimerToDomain(user);
        disclaimerEntity.setParty(partyEntity);
        disclaimerRepository.save(disclaimerEntity);

        IdentificationEntity identificationEntity = DtoToDomainMapper.mapIdentificationToDomain(user);
        identificationEntity.setParty(partyEntity);
        identificationEntityRepository.save(identificationEntity);

        AddressEntity homeAddress = DtoToDomainMapper.mapAddressToDomain(user, Type.PERSONAL);
        homeAddress.setParty_id(partyEntity);
        partyEntity.setAddresses(Collections.singletonList(homeAddress));

        addressEntityRepository.save(homeAddress);

        ContactPointEntity contactPointEntity = DtoToDomainMapper.mapContactPointToDomain(user, Type.PERSONAL);
        contactPointEntity.setContactpointtype(Type.PERSONAL);
        contactPointEntity.setParty_id(partyEntity);
        contactPointEntityRepository.save(contactPointEntity);

        EducationEntity educationEntity = DtoToDomainMapper.mapEducationToDomain(user);
        educationEntity.setParty_id(partyEntity);
        educationEntityRepository.save(educationEntity);

        EmploymentEntity employmentEntity = DtoToDomainMapper.mapEmployerToDomain(user);
        employmentEntity.setParty_id(partyEntity);
        employmentEntityRepository.save(employmentEntity);


        try {
            emailSender.sendMessageUsingThymeleafTemplate(user.getEmail().trim(),
                    null,
                    "New User Captured",
                     new HashMap<String,Object>(){{
                         put("firstname", user.getName1() +" "+ user.getSurname() );
                         put("reference", reference);
                     }},
                     EmailTemplate.USER_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reference;
    }

    @Override
    @Transactional
    public UserDetailsDTO getUserDetails(Long userid) {

        PartyEntity partyEntity = partyEntityRepository.getOne(userid);

        UserDetailsDTO user = DtoToDomainMapper.mapPartyToDto(partyEntity);
        DtoToDomainMapper.mapDisclaimerToDto(partyEntity.getDisclaimer(), user);
        DtoToDomainMapper.mapIdentificationToDto(partyEntity.getIdentification(), user);
        DtoToDomainMapper.mapAddressToDto(partyEntity.getAddresses().get(0), user, Type.PERSONAL);
        DtoToDomainMapper.mapContactPointToDto(partyEntity.getContactPoint().get(0), user, Type.PERSONAL);
        DtoToDomainMapper.mapEducationToDto(partyEntity.getEducationEntities().get(0), user);
        DtoToDomainMapper.mapEmployerToDto(partyEntity.getEmploymentHistory().get(0), user);
        return user;
    }

    /**
     * Note: This method is used as a hack to save the docs first
     */
    @Override
    public Long getNextPartySequence(){
        return this.partyEntityRepository.getNextValMySequence();
    }

}