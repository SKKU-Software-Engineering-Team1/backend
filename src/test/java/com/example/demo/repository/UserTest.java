package com.example.demo.repository;

import com.example.demo.controller.UserInfoController;
import com.example.demo.dto.Login.UserResponseDto;
import com.example.demo.dto.Response;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.Login.TokenService;
import com.example.demo.service.Login.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.security.SignatureException;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserTest {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UserInfoController userInfoController;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    EntityManager em;

    // security를 추가하면서 생긴 것들
    @Autowired
    Response response;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManagerBuilder authenticationManagerBuilder;

    @DisplayName("Authentication 생성 테스트")
    @Test
    void Authentication생성테스트(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("test@g.skku.edu", "1234");

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        System.out.println(authentication);
    }

    @DisplayName("Authentication을 검증할 때에 로그인 User에 없는 정보를 입력하면 에러를 발생시킨다.")
    @Test
    void Authentication오류테스트(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("t@skku.edu", "1234");

        Assertions.assertThatThrownBy(()->
                authenticationManagerBuilder.getObject().authenticate(authenticationToken))
                .isInstanceOf(BadCredentialsException.class);
    }

    @DisplayName("AccessToken을 임의로 변조하여 보내는 경우 에러를 발생시킨다.")
    @Test
    void AccessToken변조오류체크(){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("test@g.skku.edu", "1234");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGcuc2trdS5lZHUiLCJhdXRoIjoiIiwiZXhwIjoxNzAwMzg1OTMzfQ.6BQ_Q29Rf4R0Y_E0fIOLJ3TnhcGzNMgQixIhlawQi3
        Assertions.assertThatThrownBy(()-> userService.getUserInformation("eiOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGcuc2trdS5lZHUiLCJhdXRoIjoiIiwiZXhwIjoxNzAwMzg1OTMzfQ.6BQ_Q29Rf4R0Y_E0fIOLJ3TnhcGzNMgQixIhlawQi3E"))
                .isInstanceOf(Exception.class);
    }



}
