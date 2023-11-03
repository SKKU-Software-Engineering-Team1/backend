package com.example.demo.controller;

import com.example.demo.dto.Login.LoginDto;
import com.example.demo.dto.Login.Logout;
import com.example.demo.dto.Login.Reissue;
import com.example.demo.dto.Login.SignUpDto;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.Login.LoginService;
import com.example.demo.service.Login.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    final JwtTokenProvider jwtTokenProvider;
    final LoginService loginService;
    final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto requestBody) {
        return loginService.signUp(requestBody);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody Reissue reissue) {
        return loginService.reissue(reissue);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Logout logout) {
        return loginService.logout(logout);
    }

}
