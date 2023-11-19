package com.example.demo.repository;

import com.example.demo.dto.Login.LoginDto;
import com.example.demo.dto.Login.SignUpDto;
import com.example.demo.entity.enums.CampusType;
import com.example.demo.service.Login.LoginService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class LoginTest {

    @Autowired
    LoginService loginService;

    @Autowired
    EntityManager em;

    @DisplayName("회원 가입을 하는 경우 성공을 하면 200 코드를 반환한다.")
    @Test
    void SignUpTest(){

        SignUpDto signUpDto = SignUpDto.builder()
                .userEmail("test@g.skku.edu")
                .userPassword("1234")
                .userName("테스터1")
                .userPhoneNumber("012345")
                .userCampus("NATURAL_SCIENCE")
                .userAge(20)
                .userGender("MALE")
                .userTags(List.of("친목","맛집"))
                .build();

        // 위의 요소 중 하나라도 빠져있는 경우 에러가 발생!
        // 예를 들면 친목, 맛집 등의 태그가 null인 경우 에러 발생!
        Assertions.assertThat(loginService.signUp(signUpDto).getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @DisplayName("로그인을 하면 AccessToken과 RefreshToken을 부여받는다.")
    @Test
    void LoginTokenTest(){
        LoginDto loginDto = LoginDto.builder()
                .userEmail("test@g.skku.edu")
                .userPassword("1234")
                .build();

        ResponseEntity<?> responseEntity = loginService.login(loginDto);
        System.out.println(responseEntity.getBody());
        // AccessToken: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGcuc2trdS5lZHUiLCJhdXRoIjoiIiwiZXhwIjoxNzAwMDU1MDk5fQ.4LJEAJFNq3RvPoPG73y2Yg949dnwWOvUPtdIMR5NSEE
        // RefreshToken: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGcuc2trdS5lZHUiLCJhdXRoIjoiIiwiZXhwIjoxNzAwNjU2Mjk5fQ.r4Ao56SI5PQ3ld9kA96vFXghJzgWUKFBwRPslDhwE6E
    }

    @DisplayName("유효하지 않은 토큰을 요청과 함께 받은 경우 ")
    @Test
    void LoginTokenTest2(){
        LoginDto loginDto = LoginDto.builder()
                .userEmail("test@g.skku.edu")
                .userPassword("1234")
                .build();

        ResponseEntity<?> responseEntity = loginService.login(loginDto);
        System.out.println(responseEntity.getBody());
        // AccessToken: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGcuc2trdS5lZHUiLCJhdXRoIjoiIiwiZXhwIjoxNzAwMDU1MDk5fQ.4LJEAJFNq3RvPoPG73y2Yg949dnwWOvUPtdIMR5NSEE
        // RefreshToken: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGcuc2trdS5lZHUiLCJhdXRoIjoiIiwiZXhwIjoxNzAwNjU2Mjk5fQ.r4Ao56SI5PQ3ld9kA96vFXghJzgWUKFBwRPslDhwE6E
    }
}
