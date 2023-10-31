package com.example.demo.entity.enums;

public enum GenderType {
    MALE, FEMALE;

    public static GenderType getGender(String gender){
        GenderType genderType;

        if(gender.equals("MALE"))
            genderType = GenderType.MALE;
        else
            genderType = GenderType.FEMALE;

        return genderType;
    }
}
