package com.technologies.xelo.enums;

public enum Gender {
    MALE,FEMALE;

    public static Gender fromString(String gender){
        for (Gender d : values()) {
            if((gender.toLowerCase().trim().equalsIgnoreCase(d.name().toLowerCase()))){
                return d;
            }
        }
        return null;
    }
}
