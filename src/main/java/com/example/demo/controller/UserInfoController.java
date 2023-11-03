package com.example.demo.controller;

import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;
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
    public ResponseEntity<?> UserInfo(@RequestHeader("Authorization") String accessToken) {
        System.out.println("here " + accessToken);
        return userService.getUserInfomation(accessToken);
    }

//    @GetMapping("/UserInfo/userShotInfo/{userEmail}")
//    public Response<?> UserShortInfo(@PathVariable String userEmail){
//        LoginDto loginDto = LoginDto.builder()
//                .userEmail(userEmail)
//                .userPassword("")
//                .build();
//
//        Response<?> result = userService.getUserTagInfomation(loginDto);
//        return result;
//    }
}
