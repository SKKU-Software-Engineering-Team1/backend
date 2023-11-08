package com.example.demo.repository;

import com.example.demo.entity.Union.UnionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnionTagRepository extends JpaRepository<UnionTag, Long> {
}
