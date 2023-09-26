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
public class UserEntity {

    // 혹시나 나중에 수정할지도 몰라서 전혀 관련이 없는 DB 자체 내부 설정값을 PK로 두겠습니다.
    // 다른 Table에서도 id를 사용할 예정이니까. 얘만 이름을 따로 설정할게요.
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long Id;

    private String userEmail; // DB 내부 user_Email로 변경

    private String userPassword; // DB 내부 user_password로 변경

    private String userName; // DB 내부 user_name으로 변경

    private String userPhoneNumber; // DB 내부 user_phone_number로 변경

}
