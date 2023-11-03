package com.example.demo.controller;

import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.Login.LoginService;
import com.example.demo.service.Login.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserInfoController {

    final JwtTokenProvider jwtTokenProvider;
    final LoginService loginService;
    final UserService userService;

    @PostMapping("/UserInfo/userInfo")
    public ResponseEntity<?> UserInfo(@RequestHeader("Authorization") String accessToken){
        return userService.getUserInfomation(accessToken);
    }

    @PostMapping("/UserInfo/userShortInfo")
    public ResponseEntity<?> UserShortInfo(@RequestHeader("Authorization") String accessToken){
        return userService.getUserTagInfomation(accessToken);
    }
}
