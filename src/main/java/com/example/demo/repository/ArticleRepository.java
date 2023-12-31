package com.example.demo.repository;


import com.example.demo.entity.Board.Board;
import com.example.demo.entity.Union.Unions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ArticleRepository extends JpaRepository<Board, Long> {
//
//    @Query("select b from Board b")
//    List<Board> findAll();
//    // 유저의 이메일 정보로 찾는 함수
//    public Unions findByUserEmail(String UserEmail);

}
