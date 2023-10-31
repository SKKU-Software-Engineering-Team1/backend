package com.example.demo.repository;

import com.example.demo.entity.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long> {
    @Query("select u from Users u join fetch u.userTags")
    List<Users> findAllUserTags();

    // 유저의 이메일 정보로 찾는 함수
    public Users findByUserEmail(String userEmail);

    public boolean existsByUserEmail(String userEmail);
}
