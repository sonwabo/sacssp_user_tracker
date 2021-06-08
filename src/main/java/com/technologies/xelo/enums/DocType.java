package com.technologies.xelo.enums;

public enum DocType {

    ID_COPY,CERTIFICATES;

    public static DocType getTypeByStr(String doc){
        for (DocType d : values()) {
            if((doc.trim().equalsIgnoreCase(d.name()))){
                return d;
            }
        }
        return null;
    }
}
