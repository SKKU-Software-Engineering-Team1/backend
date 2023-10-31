package com.example.demo.entity.Union;

import com.example.demo.entity.enums.GenderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class UnionUser {
    @Id @GeneratedValue
    @Column(name="UNION_USER_ID")
    private Long Id;

    // 외래키 표시, 연관관계의 주인이라는 의미, UNION랑 조인돼서 가져옴.
    @ManyToOne
    @JoinColumn(name = "UNION_ID")
    private Unions unions;

    private String userName;

    // Enum 타입이 되 STRING으로 저장함.
    // 성별은 수정될 일이 없지만 다른 경우에 요청 사항이 늘어나면 이렇게 작성할 예정.
    @Enumerated(EnumType.STRING)
    private GenderType userGender;

    private Integer userAge;
}
