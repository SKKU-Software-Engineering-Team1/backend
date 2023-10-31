package com.example.demo.entity;

import com.example.demo.entity.enums.UnionCategoryType;
import com.example.demo.entity.enums.UnionSubType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class Unions {

    @Id
    @GeneratedValue
    @Column(name = "UNION_ID")
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

    private String unionKakao;

    private String unionSns;

    private String unionContactMail;

    private String unionYears;

    @JsonIgnore
    @OneToMany(mappedBy = "unions")
    @Builder.Default
    private List<UnionTag> unionTags = new ArrayList<>();

    @OneToMany(mappedBy = "unions")
    @Builder.Default
    private List<UnionUser> unionUsers = new ArrayList<>();


}
