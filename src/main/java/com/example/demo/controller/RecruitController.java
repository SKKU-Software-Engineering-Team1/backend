package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.User;
import com.example.demo.service.RecruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "Recruit", description = "Recruit 관련 API")
@RestController
@RequestMapping("/api/recruit/")
public class RecruitController {

    @Autowired
    RecruitService recruitService;
    @Operation(summary = "로그인한 유저(임원진)의 동아리를 위한 추천 후보 리스트 불러오기", description = "")
    @GetMapping("userList")
    @Tag(name = "Recruit")
    public ResponseDto<List<User>> getUserList(@RequestParam Long union_id) {
        return recruitService.getUserList(union_id);
    }

    @Operation(summary = "해당 유저 정보 가져오기", description = "")
    @GetMapping("userInfo")
    @Tag(name = "Recruit")
    public ResponseDto<Optional<User>> getUserInfo(@RequestParam Long user_id) {
        return recruitService.getUser(user_id);
    }

}
