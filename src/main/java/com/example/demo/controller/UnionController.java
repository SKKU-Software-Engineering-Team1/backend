package com.example.demo.controller;

import com.example.demo.entity.Union.Unions;
import com.example.demo.service.UnionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/uni")
public class UnionController {

    @Autowired
    UnionService unionService;


    @GetMapping("/getName")
    public String getParameter(@RequestParam(value = "name")String uniname)
    {
        System.out.println(uniname);
        unionService.getUnionInfo(uniname);

        return "index";
    }


}
