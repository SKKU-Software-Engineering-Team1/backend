package com.example.demo.controller;

import com.example.demo.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruit")
@RequiredArgsConstructor
public class RecruitController {

    final RecruitService recruitService;

    @GetMapping("/userList")
    public ResponseEntity<?> getUserList(@RequestParam Long union_id) {
        return recruitService.getUserList(union_id);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo(@RequestParam Long user_id) {
        return recruitService.getUser(user_id);
    }

}
