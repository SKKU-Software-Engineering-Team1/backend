package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UnionsRepository;
import com.example.demo.service.RecruitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Tag(name = "Recruit", description = "Recruit 관련 API")
@RestController
@RequestMapping("/api/recruit/")
public class RecruitController {

    @Autowired
    RecruitService recruitService;
    @Operation(summary = "로그인한 유저(임원진)의 동아리를 위한 추천 후보 리스트 불러오기", description = "로그인 기능")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Union not found"),
    })
    @GetMapping("list")
    @Tag(name = "Recruit")
    public ResponseEntity<?> getList(@RequestParam Long union_id) {
        System.out.println(union_id);
        return recruitService.getList(union_id);
    }

}
