package com.example.demo.repository;

import com.example.demo.entity.Union.Unions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Unions, Long> {
    @Query("select u from Unions u join fetch u.unionTags")
    List<Unions> findAllUnionTags();
}
