package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {

    // 유저의 이메일 정보로 찾는 함수
    public UserEntity findByUserEmail(String UserEmail);
}
