package com.example.demo.dto.Login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Logout {
    private String accessToken;
    private String refreshToken;
}