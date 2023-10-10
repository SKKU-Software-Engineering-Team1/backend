package com.example.demo.repository;

import com.example.demo.entity.User;
import com.example.demo.entity.UserTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.userTags")
    List<User> findAllUserTags();

    // 유저의 이메일 정보로 찾는 함수
    public User findByUserEmail(String UserEmail);
}
