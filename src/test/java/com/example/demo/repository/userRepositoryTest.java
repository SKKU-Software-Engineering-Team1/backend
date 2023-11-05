package com.example.demo.repository;

import com.example.demo.dto.Login.LoginDto;
import com.example.demo.dto.Login.SignUpDto;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.enums.GenderType;
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

}
