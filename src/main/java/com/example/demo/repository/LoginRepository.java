package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.userTags")
    List<User> findAllUserTags();

    public Optional<User> findById(Long user_id);

    // 유저의 이메일 정보로 찾는 함수
    public User findByUserEmail(String UserEmail);
}
