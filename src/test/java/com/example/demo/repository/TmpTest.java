package com.example.demo.repository;

import com.example.demo.entity.User.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class TmpTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    LoginRepository loginRepository;
    @Test
    void 유저정보가져오기(){
        System.out.println(userDetailsService.loadUserByUsername("200").getAuthorities().stream().anyMatch(o->o.getAuthority().equals("ROLE_ADMIN")));
    }

}
