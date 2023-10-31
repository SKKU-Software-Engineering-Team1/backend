package com.example.demo.repository;

import com.example.demo.dto.Login.LoginDto;
import com.example.demo.dto.Login.SignUpDto;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.enums.GenderType;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class userRepositoryTest {


    @Autowired UserTagRepository userTagRepository;
    @Autowired LoginRepository loginRepository;

    @Autowired UserService userService;

    @Autowired LoginService loginService;

    @Autowired EntityManager em;

    // 멤버 넣고 전체 조회
    @Test
    void findAllFromUser(){
        // 멤버 객체 생성 부분
        Users user = Users.builder()
                .userEmail("qwer@qwer")
                .userPassword("qwer")
                .userGender(GenderType.MALE)
                .build();

        Users users2 = Users.builder()
                .userEmail("qwer1@qwer")
                .userPassword("qwer")
                .userGender(GenderType.MALE)
                .build();

        Users users3 = Users.builder()
                .userEmail("qwer2@qwer")
                .userPassword("qwer")
                .userGender(GenderType.MALE)
                .build();


        // user 3개 데이터 DB에 저장
        loginRepository.save(user);
        loginRepository.save(users2);
        loginRepository.save(users3);

        // 이거 딱 돌려보면 어? insert 쿼리도 안날라가고 select query도 안날라가네?
        // DB에서 가져와야하는데? 이게 정상입니다.
        // 그건 Spring에서 미리 SQL 날릴 걸 가지고 있다가 나중에 진짜로 날려야할 때 쓰려고
        // 영속성 컨텍스트라는 곳에 보관한 거라. 쿼리를 보고 싶으시면 아래 주석을 해제해주세요.
        em.flush();
        em.clear();


         List<Users> users = loginRepository.findAll();

         // 이건 제가 검증하는 부분으로 hasSize는 리스트 객체의 내부 객체 갯수를 비교해줍니다.
         // 저거 개수가 다르면 실제로 받은 건 null인지 아니면 2개인지 그런 정보도 다 로그로 띄워줘요.
         Assertions.assertThat(users).hasSize(3);
    }

    // Email로 데이터 가져오는 방법
    @Test
    void findAllMemberWithEmail(){
        // user 데이터를 넣는 부분
        Users users = Users.builder()
                .userEmail("qwer@qwer")
                .userPassword("qwer")
                .userGender(GenderType.MALE)
                .build();

        // user 데이터를 가져오는 부분
        loginRepository.save(users);

        // em.clear();
        // em.flush();


        // 마치 select * from user u where user_email = qwer@qwer의 역할을 findByUserEmail이 합니다.
        Users findUsers = loginRepository.findByUserEmail("qwer@qwer");

        // 저건 글자 같은지 확인해주는 메서드에요. Java에서는 ==이랑 equals 뭐 되게 많아요.
        // 해당 부분은 나중에 각각 무슨 차이인지 공부해보시면 도움이 많이 될 겁니다.
        Assertions.assertThat(findUsers.getUserEmail()).isEqualTo("qwer@qwer");
    }


//    @Test // 이건 데이터를 join해서 가져오는 거에요. query는 LoginRepository 확인 부탁드립니다.
//    void userTagTest(){
//        // user 데이터 넣고 저장
//        Users user = Users.builder()
//                .userEmail("qwer2@qwer1r")
//                .userPassword("qwer")
//                .userGender(GenderType.MALE)
//                .build();
//
//        // userTag 정보 넣고 저장
//        UserTag userTag = UserTag.builder()
//                .userTag(UserTagType.번개)
//                .users(user).build();
//
//        // userTag 정보 넣고 저장
//        UserTag userTag2 = UserTag.builder()
//                .userTag(UserTagType.맛집)
//                .users(user).build();
//
//
//        userTagRepository.save(userTag);
//        userTagRepository.save(userTag2);
//        loginRepository.save(user);
//
//        em.flush();
//        em.clear();
//
//        // 이제 fetch join으로 한번에
//        List<Users> users = loginRepository.findAllUserTags();
//
//        Users findUsers = users.get(0);
//
//        System.out.println(findUsers.getUserTags().get(0).getUserTag());
//        System.out.println(findUsers.getUserTags().get(1).getUserTag());
//    }

    @Test
    void 회원가입_테스트(){

        List<String> tmpStr = new ArrayList<>();

        tmpStr.add("영화");
        tmpStr.add("음악");

        SignUpDto signUpDto = SignUpDto.builder()
                .userEmail("qwer@qwer")
                .userPassword("qwer")
                .userName("qwer")
                .userAge(25)
                .userPhoneNumber("12345678")
                .userGender("MALE")
                .userSchool("NATURAL")
                .userTags(tmpStr)
                .build();

        loginService.signUp(signUpDto);

    }

    @Test
    void 로그인_테스트(){

        LoginDto loginDto = LoginDto.builder()
                .userEmail("qwer@qwer")
                .userPassword("qwer")
                .build();

        loginService.login(loginDto);
    }

//    @Test
//    void 유저_일반정보_테스트(){
//        LoginDto loginDto = LoginDto.builder()
//                .userEmail("qwer@qwer")
//                .userPassword("qwer")
//                .build();
//
//        userService.getUserInfomation(loginDto);
//    }

//    @Test
//    void 유저_태그정보_테스트(){
//        LoginDto loginDto = LoginDto.builder()
//                .userEmail("qwer@qwer")
//                .userPassword("")
//                .build();
//
//        userService.getUserTagInfomation(loginDto);
//    }

}
