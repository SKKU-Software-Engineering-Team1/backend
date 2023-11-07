package com.example.demo.repository;

import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnionRepository extends JpaRepository<Unions, Long> {
//    @Query("select u from Unions u join fetch u.unionTags")
//    List<User> findAllUnionsTags();

    // 유저의 이메일 정보로 찾는 함수
    public Unions findByUnionName(String unionName);
}
