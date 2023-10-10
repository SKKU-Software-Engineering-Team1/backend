package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

//    @Autowired
//    LoginService loginService;
//
//    @PostMapping("/signUp")
//    public ResponseDto<?> signUp(@RequestBody SignUpDto requestBody){
//        ResponseDto<?> result = loginService.signUp(requestBody);
//        return result;
//    }

}
