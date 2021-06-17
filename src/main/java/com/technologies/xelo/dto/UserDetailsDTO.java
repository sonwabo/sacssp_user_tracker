package com.technologies.xelo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
/**
 * @author <a href="mailto:s.singatha@gmail.com">Sonwabo Singatha</a>
 * @version 1.0
 * @Date 2021/04/27
 * @TIME 22:00
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class UserDetailsDTO {

    /**Personal Information **/
    public Long userId;
    public String reference; 


    public String title;
    public String name1;
    public String name2;
    public String name3;
    public String surname;
    public String disabilitystatus;
    public String ethnicalstatus;
    public String gender;
    public String language;
    public String maidensurname;
    public String maritalstatus;

    public String disclaimer;

    /**  Personal Contact Points **/
    public String email;
    public String worknumber;
    public String cellphone;

    /**  Identification **/
    public String citizenship;
    public String birthdate;
    public String idnumber;
    public String passport;

    /** Personal Address **/
    public String postalcode;
    public String provincename;
    public String houseorcomplexnameandnumber;
    public String streetnumberandname;
    public String suburbname;
    public String townname;


    /** Education History **/
    public String institutionname;
    public String institutiontel;
    public String universityobtained;
    public String yearcompleted;
    public String durationofcourse;
    public String qualificationlevel;
    public String qualificationname;


    /** Employment History **/
    public String jobtitle;
    public String employername;
    public String sectorofemployment;
    public String iscommunitypractitioner;

    /** Employer's Contact**/
    public String empcellphone;
    public String empworknumber;
    public String empemail;


    /** Employer's Address **/
    public String emppostalcode;
    public String empprovincename;
    public String empbusinessparknameorunitname;
    public String empstreetnameandnumber;
    public String empsuburbname;
    public String emptownname;

}
