package com.example.demo.repository;

import com.example.demo.entity.Unions;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnionsRepository extends JpaRepository<Unions, Long> {
    @Query("select u from Unions u join fetch u.unionTags")
    List<Unions> findAllUnionTags();

    @Query("select u from Unions u join fetch u.unionTags where u.Id = :union_id")
    public Optional<Unions> findById(Long union_id);
}
