package com.example.demo.entity.User;

import com.example.demo.entity.enums.TagType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
@ToString(exclude = {"users"})
@Entity // Entity 테이블 표시
@Builder
public class UserTag {
    @Id
    @GeneratedValue
    @Column(name = "USER_TAG_ID")
    private Long id;

    // 외래키 표시, 연관관계의 주인이라는 의미, user랑 조인돼서 가져옴.
    @ManyToOne
    @JoinColumn(name = "USERS_ID")
    private Users users;

    // Enum 타입이 되 STRING으로 저장함.
    // 이 경우에 요청 사항이 늘어나면 이게 매우 유리함.
    @Enumerated(EnumType.STRING)
    @NotNull
    private TagType userTag;

}
