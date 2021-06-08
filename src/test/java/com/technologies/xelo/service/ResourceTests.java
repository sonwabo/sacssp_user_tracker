package com.technologies.xelo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technologies.xelo.dto.UserDetailsDTO;
import com.technologies.xelo.enums.Type;
import com.technologies.xelo.intf.PartyManager;
import com.technologies.xelo.model.entities.*;
import com.technologies.xelo.model.repo.*;
import com.technologies.xelo.util.DtoToDomainMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/29
 * @TIME 12:49
 */
@SpringBootTest
public class ResourceTests {

    @Autowired
    PartyManager partyManager;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @Transactional
    @Rollback(false)
    public void testCreatePartyDetailsFromJson() throws JsonProcessingException {

        String strUser = "{\n" +
                "  \"title\": \"Mr\",\n" +
                "  \"name1\": \"Soonwabo\",\n" +
                "  \"name2\": \"Orlecks\",\n" +
                "  \"name3\": \"Lex\",\n" +
                "  \"surname\": \"Singatha\",\n" +
                "  \"maidensurname\": \"Msibi\",\n" +
                "  \"birthdate\": \"1989/04/30\",\n" +
                "  \"idnumber\": \"8904305263084\",\n" +
                "  \"passport\": \"\",\n" +
                "  \"citizenship\": \"South African\",\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"language\": \"Xhosa\",\n" +
                "  \"maritalstatus\": \"Married\",\n" +
                "  \"ethnicalstatus\": \"Black\",\n" +
                "  \"disabilitystatus\": \"None\",\n" +
                "  \"cellphone\": \"0824866209\",\n" +
                "  \"worknumber\": \"0219404107\",\n" +
                "  \"email\": \"s.singatha@gmail.com\",\n" +
                "  \"houseorcomplexnameandnumber\": \"12 Shiraz Villas\",\n" +
                "  \"streetnumberandname\": \"Bukettraube Cl\",\n" +
                "  \"suburbname\": \"Oude Westhof\",\n" +
                "  \"townname\": \"Capetown\",\n" +
                "  \"provincename\": \"WC\",\n" +
                "  \"postalcode\": \"1703\",\n" +
                "  \"institutionname\": \"Jeppe College\",\n" +
                "  \"institutiontel\": \"0129405252\",\n" +
                "  \"qualificationname\": \"Further Education and Training Certificate: Community Development\",\n" +
                "  \"yearcompleted\": \"2010/01/20\",\n" +
                "  \"qualificationlevel\": \"Level 6\",\n" +
                "  \"durationofcourse\": \"Two years\",\n" +
                "  \"universityobtained\": \"University of the Witwatersrand (WITS)\",\n" +
                "  \"employername\": \"Jumpco Holdings\",\n" +
                "  \"sectorofemployment\": \"Private Sector\",\n" +
                "  \"jobtitle\": \"Senior Developer\",\n" +
                "  \"empbusinessparknameorunitname\": \"Mispel Road Business Park\",\n" +
                "  \"empstreetnameandnumber\": \"14 Avenue\",\n" +
                "  \"empsuburbname\": \"Fairland\",\n" +
                "  \"emptownname\": \"Johannesburg\",\n" +
                "  \"empprovincename\": \"WC\",\n" +
                "  \"emppostalcode\": \"1755\",\n" +
                "  \"empcellphone\": \"0731747417\",\n" +
                "  \"empworknumber\": \"0119405566\",\n" +
                "  \"empemail\": \"sonwabo.singatha@jumpco.io\",\n" +
                "  \"disclaimer\": \"Yes\"\n" +
                "}";

        UserDetailsDTO user = this.objectMapper.readValue(strUser, UserDetailsDTO.class);
        Assert.notNull(user);

        System.out.println(">>>>>>>>>>>>>>>>>>>");
        System.out.println(partyManager.createOrUpdateUser(user));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testGetPartyDetailsFromDatabase() throws JsonProcessingException {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println( this.objectMapper.writeValueAsString(partyManager.getUserDetails(1L)));
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
