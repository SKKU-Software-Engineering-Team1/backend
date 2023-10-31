package com.example.demo.controller;

import com.example.demo.dto.Login.LoginDto;
import com.example.demo.dto.Login.Logout;
import com.example.demo.dto.Login.Reissue;
import com.example.demo.dto.Response;
import com.example.demo.dto.Login.SignUpDto;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    final JwtTokenProvider jwtTokenProvider;
    final LoginService loginService;
    final UserService userService;

    @PostMapping("/login/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto requestBody){
        return loginService.signUp(requestBody);
    }


    @PostMapping("/login/login")
    public ResponseEntity<?> logIn(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }

    @PostMapping("/login/reissue")
    public ResponseEntity<?> reissue(@RequestBody Reissue reissue){
        return loginService.reissue(reissue);
    }

    @PostMapping("/login/logout")
    public ResponseEntity<?> logout(@RequestBody Logout logout){
        return loginService.logout(logout);
    }



}
