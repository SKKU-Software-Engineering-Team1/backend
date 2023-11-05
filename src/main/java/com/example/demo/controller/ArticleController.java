package com.example.demo.controller;


import com.example.demo.service.ArticleService;
import com.example.demo.service.UnionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
//    @Autowired
    final ArticleService articleService;


//    @GetMapping("/getList")
//    public String getParameter(@RequestParam(value = "name")String uniname)
//    {
////        System.out.println(uniname);
////        articleService.getArticleList(uniname);
//
//        return "index";
//    }
}
