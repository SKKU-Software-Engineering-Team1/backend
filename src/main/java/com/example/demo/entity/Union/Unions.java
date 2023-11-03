package com.example.demo.entity.Union;

import com.example.demo.entity.enums.UnionCategoryType;
import com.example.demo.entity.enums.UnionSubType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data // @Getter, Setter, ToString, RequiredArgsConstructor, EqualsAndHashCode 적용
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class Unions {

    @Id
    @GeneratedValue
    @Column(name = "UNIONS_ID")
    private Long Id;

    private String unionName;

    // Enum 타입이 되어 STRING으로 저장함.
    @Enumerated(EnumType.STRING)
    @NotNull
    private UnionCategoryType unionCategory;

    @Column(columnDefinition = "TEXT")
    private String unionIntroduction;

    @Column(columnDefinition = "TEXT")
    private String unionRecruit;

    @Builder.Default
    private boolean unionSkkuYn = false;

    // Enum 타입이 되어 STRING으로 저장함.
    @Enumerated(EnumType.STRING)
    @NotNull
    private UnionSubType unionSkkuSub;

    private String unionDues;

    private String unionContactPhone;

    private String unionContactMail;

    @OneToMany(mappedBy = "unions")
    @Builder.Default
    private List<UnionTag> unionTags = new ArrayList<>();

    @OneToMany(mappedBy = "unions")
    @Builder.Default
    private List<UnionUser> unionUsers = new ArrayList<>();


}
