package com.example.demo.dto.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String userEmail;
    private String userPassword;

    private int userAge;
    private String userName;
    private String userGender;
    private String userPhoneNumber;
    private String userCampus;

    private List<String> userTags;

}
