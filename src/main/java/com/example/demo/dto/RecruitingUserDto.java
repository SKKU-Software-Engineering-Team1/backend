package com.example.demo.dto;

import com.example.demo.entity.enums.CampusType;
import com.example.demo.entity.enums.GenderType;
import com.example.demo.entity.enums.TagType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecruitingUserDto {
    private Long userId;
    private String userName;
    private GenderType userGender;
    private Integer userAge;
    private String userPhone;
    private String userEmail;
    private CampusType userCampus;
    private String userIntroduction;
    private List<TagType> userTags;
    private String userRole;
}
