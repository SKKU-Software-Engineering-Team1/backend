package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class Token {

    @Id
    @GeneratedValue
    @Column(name="TOKEN_ID")
    private Long id;

    private String authName;

    // 글자수 255바이트 넘어갈 수도 있어서 TEXT 형태로 받음
    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    private Long refreshTokenExpirationTime;
}
