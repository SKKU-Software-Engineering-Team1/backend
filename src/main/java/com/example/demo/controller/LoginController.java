package com.example.demo.controller;

import com.example.demo.dto.Login.LoginDto;
import com.example.demo.dto.Login.Logout;
import com.example.demo.dto.Login.TokenDto;
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
@CrossOrigin(origins="*")
public class LoginController {

    final JwtTokenProvider jwtTokenProvider;
    final LoginService loginService;
    final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto requestBody) {
        return loginService.signUp(requestBody);
    }

    @PostMapping("/signUpAdmin")
    public ResponseEntity<?> signUpAdmin(@RequestBody SignUpDto requestBody) {
        return loginService.signUpAdmin(requestBody);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginDto loginDto) {
        return loginService.login(loginDto);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenDto tokenDto) {
        return loginService.reissue(tokenDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Logout logout) {
        return loginService.logout(logout);
    }

}
