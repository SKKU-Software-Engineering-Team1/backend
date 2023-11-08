package com.example.demo.controller;

import com.example.demo.dto.Login.SignUpDto;
import com.example.demo.dto.UnionsDto;
import com.example.demo.entity.Union.Unions;
import com.example.demo.service.UnionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UnionsController {

    @Autowired
    UnionService unionService;


    @GetMapping("/getUniInfo")
    public ResponseEntity<?> getParameter(@RequestParam(value = "Id")Long uniId)
    {

        return unionService.getUnionInfo(uniId);
    }


    @PostMapping("/regUni")
    public ResponseEntity<?> signUp(@RequestBody UnionsDto requestBody) {
        return unionService.regUnion(requestBody);
    }


}
