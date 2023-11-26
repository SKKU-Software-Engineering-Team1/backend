package com.example.demo.repository;

import com.example.demo.entity.User.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class LoginRepositoryTest {

    @Autowired
    LoginRepository loginRepository;

    @Test
    void 유저전체조회(){
        loginRepository.findAllUserTags();
    }

    @Test
    void 유저이메일객체가져오기(){
        loginRepository.findByUserEmail("20");
    }

    @Test
    void 유저이메일객체존재여부확인(){
        loginRepository.existsByUserEmail("20");
    }

    @Test
    void 유저각자조회(){
        loginRepository.findUserWithUserTags("20");
    }
}
