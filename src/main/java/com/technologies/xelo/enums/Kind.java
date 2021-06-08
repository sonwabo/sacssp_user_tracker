package com.technologies.xelo.enums;

public enum Kind {
    EMPLOYMENT,HOME,PERSONAL,EDUCATION;

    public static Kind getkindByStr(String kind){
        for (Kind d : values()) {
            if((kind.trim().equalsIgnoreCase(d.name()))){
                return d;
            }
        }
        return null;
    }
}
