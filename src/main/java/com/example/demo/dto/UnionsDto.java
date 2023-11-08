package com.example.demo.dto;

import com.example.demo.entity.Union.UnionTag;
import com.example.demo.entity.Union.UnionUser;
import com.example.demo.entity.enums.UnionCategoryType;
import com.example.demo.entity.enums.UnionSubType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnionsDto {
    private Long Id;
    private String unionName;
    private UnionCategoryType unionCategory;
    private String unionIntroduction;
    private String unionRecruit;
    private LocalDate unionRecruitDateStart;
    private LocalDate unionRecruitDateEnd;
    private boolean unionSkkuYn = false;
    private UnionSubType unionSkkuSub;
    private String unionDues;
    private String unionContactPhone;
    private String unionKakao;
    private String unionSns;
    private String unionContactMail;
    private String unionYears;
    private List<UnionTag> unionTags = new ArrayList<>();
    private List<UnionUser> unionUsers = new ArrayList<>();

}
