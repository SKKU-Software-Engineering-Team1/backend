package com.example.demo.entity;

import com.example.demo.entity.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class User {

    // 혹시나 나중에 수정할지도 몰라서 전혀 관련이 없는 DB 자체 내부 설정값을 PK로 두겠습니다.
    // 다른 Table에서도 id를 사용할 예정이니까. 얘만 이름을 따로 설정할게요.
    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long Id;

    @NotNull
    private String userEmail; // DB 내부 user_Email로 변경

    @NotNull
    private String userPassword; // DB 내부 user_password로 변경


    private String userName; // DB 내부 user_name으로 변경

    private Integer userAge;

    // Enum 타입이 되 STRING으로 저장함.
    // 성별은 수정될 일이 없지만 다른 경우에 요청 사항이 늘어나면 이렇게 작성할 예정.
    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderType userGender;

    // User 1개 당 여러 개의 UserTag를 가짐. 그렇기 때문에 @OneToMany 어노테이션
    // @ManyToOne으로 설계를 할 수도 있지만 여러 문제점이 있고 Tag를 조회하는 방향이
    // 보통 user에서 조회할 가능성이 많기 때문에 이쪽에서 접근 가능하게 양방향으로 열어두었음.
    // mappedBy는 조회만 가능한 것을 의미함.
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<UserTag> userTags = new ArrayList<>();

}
