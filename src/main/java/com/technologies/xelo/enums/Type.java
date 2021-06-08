package com.technologies.xelo.enums;

public enum Type {
    EMPLOYMENT,HOME,PERSONAL,EDUCATION;


    public static Type getTypeByStr(String kind){
        for (Type d : values()) {
            if((kind.trim().equalsIgnoreCase(d.name()))){
                return d;
            }
        }
        return null;
    }
}
