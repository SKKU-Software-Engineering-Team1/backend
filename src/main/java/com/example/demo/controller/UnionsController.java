package com.example.demo.controller;

import com.example.demo.service.UnionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UnionsController {

    @Autowired
    UnionService unionService;


    @GetMapping("/getId")
    public ResponseEntity<?> getParameter(@RequestParam(value = "Id")Long uniId)
    {
        System.out.println(uniId.getClass().getName());
        System.out.println(uniId);
        return unionService.getUnionInfo(uniId);
    }


}
