package com.example.demo.controller;

import com.example.demo.service.UnionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class UnionsController {

    @Autowired
    UnionService unionService;


    @GetMapping("/getId")
    public ResponseEntity<?> getParameter(@RequestParam(value = "Id")Long uniId)
    {

        return unionService.getUnionInfo(uniId);
    }


}
