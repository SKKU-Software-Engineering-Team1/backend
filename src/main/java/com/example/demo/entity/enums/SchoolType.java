package com.example.demo.entity.enums;

public enum SchoolType {
    NATURAL, SOCIETY;

    public static SchoolType getSchool(String school){
        SchoolType schoolType;

        if(school.equals("NATURAL"))
            schoolType = SchoolType.NATURAL;
        else
            schoolType = SchoolType.SOCIETY;

        return schoolType;
    }
}
