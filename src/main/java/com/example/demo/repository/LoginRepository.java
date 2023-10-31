package com.example.demo.repository;


import com.example.demo.entity.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long> {

    // 유저의 이메일 정보로 찾는 함수
    public Users findByUserEmail(String userEmail);

    public boolean existsByUserEmail(String userEmail);
}
