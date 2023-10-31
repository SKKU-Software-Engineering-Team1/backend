package com.example.demo.repository;

import com.example.demo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    public Token findByRefreshToken(String RefreshToken);
    public Token findByAuthName(String AuthName);

    public boolean existsByAuthName(String AuthName);

}
