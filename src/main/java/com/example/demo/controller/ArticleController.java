package com.example.demo.controller;


import com.example.demo.entity.Board.Board;
import com.example.demo.entity.Union.Unions;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.Login.LoginService;
import com.example.demo.service.Login.UserService;
import com.example.demo.service.UnionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ArticleController {
//    @Autowired

    final ArticleRepository articleRepository;
    final ArticleService articleService;
//    final JwtTokenProvider jwtTokenProvider;
//    final UserService userService;
//    final TokenService tokenService;
    @GetMapping("/getArticle")
    public ResponseEntity<?> getAllArticle(){
        return articleService.getArticleList();
    }
//    @PostMapping("/getArticle")
//    public ResponseEntity<?> UserInfo(@RequestHeader("AccessToken") String accessToken
//            , @RequestHeader(value = "refreshToken", required = false) String refreshToken){
//
//        // 모든 코드에 이거 복붙해서 아래 부분 return만 한 줄 본인이 넣으실거 추가하시면 됩니다.
//        // RefreshToken이랑 다 날라가는 거 검증 완료했습니다.
//        if(refreshToken == null){
//            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
//            if(!jwtTokenProvider.validateToken(accessToken))
//                return tokenService.requestRefreshToken();
//            else
//                return articleRepository.findAll(); // 여기만 바꿔주세요
//
//        }
//        else
//            return tokenService.reissueAccessToken(accessToken, refreshToken);
//
//    }



}
