package com.technologies.xelo.util;

import com.technologies.xelo.dto.UserDetailsDTO;
import com.technologies.xelo.enums.Type;
import com.technologies.xelo.model.repo.PartyEntityRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.technologies.xelo.service.PartyManagerImpl.getUserDetailsDTO;

/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/30
 * @TIME 11:27
 */
@Component
public class SheetsGenerator {

    private final PartyEntityRepository partyEntityRepository;
    private final String fileNameAndLocation;

    public SheetsGenerator(final PartyEntityRepository partyEntityRepository, @Value("${sheets.location}") String fileNameAndLocation) {
        this.partyEntityRepository = partyEntityRepository;
        this.fileNameAndLocation = fileNameAndLocation;
    }

    public boolean generateSheets(int page, int size) throws IOException {

        List<UserDetailsDTO> users = this.partyEntityRepository.findAll().stream() //partyEntityRepository.findAll( PageRequest.of(0 , 10))
                .map(partyEntity -> {
                    return getUserDetailsDTO(partyEntity);
                }).collect(Collectors.toList());

        if(!users.isEmpty()){
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Users");
            generateHearders(sheet);
            generateBody(users,sheet);
            FileOutputStream fileOut = new FileOutputStream(this.fileNameAndLocation);
            workbook.write(fileOut);
            fileOut.close();
            return true;
        }
        return false;
    }

    private void generateBody( List<UserDetailsDTO> users , HSSFSheet sheet ) {

        int counter = 1;
        for (int i = 0; i < users.size(); i++ ) {

            UserDetailsDTO user = users.get(i);

            HSSFRow rowhead = sheet.createRow((short)counter);
            rowhead.createCell(0).setCellValue(user.getTitle());
            rowhead.createCell(1).setCellValue(user.getName1());
            rowhead.createCell(2).setCellValue(user.getName2());
            rowhead.createCell(3).setCellValue(user.getName3());
            rowhead.createCell(4).setCellValue(user.getSurname());
            rowhead.createCell(5).setCellValue(user.getMaidensurname());

            List<String> newList = Arrays.asList( user.getEthnicalstatus().split(",") ).stream()
                    .distinct()
                    .collect(Collectors.toList());
            newList.removeAll(Arrays.asList("", null));

            if(newList.size() > 1){
                rowhead.createCell(6).setCellValue(newList.get(1));
                rowhead.createCell(7).setCellValue(newList.get(0));
            }else{
                rowhead.createCell(6).setCellValue("No");
                rowhead.createCell(7).setCellValue(newList.get(0));
            }

            rowhead.createCell(8).setCellValue(user.getGender());
            rowhead.createCell(9).setCellValue(user.getLanguage());
            rowhead.createCell(10).setCellValue(user.getMaritalstatus());
            rowhead.createCell(11).setCellValue(user.getEmail());
            rowhead.createCell(12).setCellValue(user.getWorknumber());
            rowhead.createCell(13).setCellValue(user.getCellphone());
            rowhead.createCell(14).setCellValue(user.getCitizenship());
            rowhead.createCell(15).setCellValue(user.getBirthdate());
            rowhead.createCell(16).setCellValue(user.getIdnumber());


            rowhead.createCell(17).setCellValue(user.getHouseorcomplexnameandnumber());
            rowhead.createCell(18).setCellValue(user.getStreetnumberandname());
            rowhead.createCell(19).setCellValue(user.getSuburbname());
            rowhead.createCell(20).setCellValue(user.getPostalcode());
            rowhead.createCell(21).setCellValue(user.getTownname());
            rowhead.createCell(22).setCellValue(user.getProvincename());
            rowhead.createCell(23).setCellValue(user.getDisabilitystatus());


            rowhead.createCell(24).setCellValue(user.getInstitutionname());
            rowhead.createCell(25).setCellValue(user.getInstitutiontel());
            rowhead.createCell(26).setCellValue(user.getUniversityobtained());
            rowhead.createCell(27).setCellValue(user.getYearcompleted());
            rowhead.createCell(28).setCellValue(user.getDurationofcourse());

            rowhead.createCell(29).setCellValue(user.getQualificationlevel());
            rowhead.createCell(30).setCellValue(user.getQualificationname());
            rowhead.createCell(31).setCellValue(user.getEmployername());
            rowhead.createCell(32).setCellValue(user.getJobtitle());
            rowhead.createCell(33).setCellValue(user.getSectorofemployment());
            rowhead.createCell(34).setCellValue(user.getEmpcellphone());
            rowhead.createCell(35).setCellValue(user.getEmpcellphone());
            rowhead.createCell(36).setCellValue(user.getEmail());

            counter = counter + 1;
        }
    }

    private void generateHearders(HSSFSheet sheet) {
        HSSFRow rowhead = sheet.createRow((short)0);

        rowhead.createCell(0).setCellValue("Title");
        rowhead.createCell(1).setCellValue("First Name");
        rowhead.createCell(2).setCellValue("Second Name");
        rowhead.createCell(3).setCellValue("Third Name");
        rowhead.createCell(4).setCellValue("Surname");
        rowhead.createCell(5).setCellValue("Maiden Name");
        rowhead.createCell(6).setCellValue("Disability Status");
        rowhead.createCell(7).setCellValue("Ethnical Status");
        rowhead.createCell(8).setCellValue("Gender");
        rowhead.createCell(9).setCellValue("Language");
        rowhead.createCell(10).setCellValue("Marital Status");
        rowhead.createCell(11).setCellValue("Email");
        rowhead.createCell(12).setCellValue("Personal Telephone Number");
        rowhead.createCell(13).setCellValue("Cellphone");
        rowhead.createCell(14).setCellValue("Citezenship");
        rowhead.createCell(15).setCellValue("Birthdate");
        rowhead.createCell(16).setCellValue("Identification Number");

        rowhead.createCell(17).setCellValue("House/Complex Name and Number");
        rowhead.createCell(18).setCellValue("Street Name and Number");
        rowhead.createCell(19).setCellValue("Suburb Name");
        rowhead.createCell(20).setCellValue("Postal Code");
        rowhead.createCell(21).setCellValue("Town Name");
        rowhead.createCell(22).setCellValue("Province");
        rowhead.createCell(23).setCellValue("District");


        rowhead.createCell(24).setCellValue("Institution Name");
        rowhead.createCell(25).setCellValue("Institution Tel");
        rowhead.createCell(26).setCellValue("University Obtained");
        rowhead.createCell(27).setCellValue("Year Completed");
        rowhead.createCell(28).setCellValue("Duration of Course");

        rowhead.createCell(29).setCellValue("Qualification Level");
        rowhead.createCell(30).setCellValue("Qualification Name");
        rowhead.createCell(31).setCellValue("Employer Name");
        rowhead.createCell(32).setCellValue("Job Title");
        rowhead.createCell(33).setCellValue("Sector of Employment");
        rowhead.createCell(34).setCellValue("Employer Cellphone");
        rowhead.createCell(35).setCellValue("Employer Work Number");
        rowhead.createCell(36).setCellValue("Employer Email");
    }
}
