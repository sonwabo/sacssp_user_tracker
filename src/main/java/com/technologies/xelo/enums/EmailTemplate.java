package com.technologies.xelo.enums;

public enum EmailTemplate {

    USER_CREATED("user-created.html");

    private String value;
    EmailTemplate(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
