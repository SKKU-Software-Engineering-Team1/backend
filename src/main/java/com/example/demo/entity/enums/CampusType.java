package com.example.demo.entity.enums;

public enum CampusType {
    NATURAL, SOSIETY;

    public static CampusType getCampus(String campus) {
        CampusType campusType;

        if (campus.equals("NATURAL")) {
            campusType = CampusType.NATURAL;
        } else {
            campusType = CampusType.SOSIETY;
        }
        return campusType;
    }
}
