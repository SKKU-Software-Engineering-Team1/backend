package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String userName;
    String userGender;
    int userAge;
    String userPhoneNumber;
    String userEmail;
    String userCampus;
    List<String> userTags;
}
