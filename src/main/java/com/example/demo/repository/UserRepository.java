package com.example.demo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager entityManager;

    public List<String> getUserRolesById(Long userId) {
        return entityManager.createQuery(
                        "SELECT u.roles FROM Users u WHERE u.Id = :userId", List.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }
}
