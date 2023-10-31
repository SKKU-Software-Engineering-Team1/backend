package com.example.demo.controller;

import com.example.demo.dto.RecruitingUserDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.RecruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Recruit", description = "Recruit 관련 API")
@RestController
@RequestMapping("/api/recruit/")
public class RecruitController {

    @Autowired
    RecruitService recruitService;
    @Operation(summary = "로그인한 유저(임원진)의 동아리를 위한 추천 후보 리스트 불러오기", description = "")
    @GetMapping("userList")
    @Tag(name = "Recruit")
    public ResponseDto<List<RecruitingUserDto>> getUserList(@RequestParam Long union_id) {
        return recruitService.getUserList(union_id);
    }

    @Operation(summary = "해당 유저 정보 가져오기", description = "")
    @GetMapping("userInfo")
    @Tag(name = "Recruit")
    public ResponseDto<RecruitingUserDto> getUserInfo(@RequestParam Long user_id) {
        return recruitService.getUser(user_id);
    }

}
