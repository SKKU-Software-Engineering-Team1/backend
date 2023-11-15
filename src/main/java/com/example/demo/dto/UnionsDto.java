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
import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.User.UserTag;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UnionsDto {
    private Long unions_id;
    private String unionName;
    private UnionCategoryType unionCategory;
    private String unionIntroduction;
    private String unionRecruit;
    private LocalDate unionRecruitDateStart;
    private LocalDate unionRecruitDateEnd;
    @Builder.Default
    private boolean unionSkkuYn = false;
    private UnionSubType unionSkkuSub;
    private String unionDues;
    private String unionContactPhone;
    private String unionKakao;
    private String unionSns;
    private String unionContactMail;
    private String unionYears;
    @Builder.Default
    private List<UnionTag> unionTags = new ArrayList<>();
    private List<String> unionTagsString;

    public Unions toEntity(Unions union) {
        System.out.println("*******************************");
        List<UnionTag> unionTags = new ArrayList<>();
        for (String str: unionTagsString) {
            TagType unionTagType = TagType.valueOf(str);
            // 위 정보로 userTag 생성후 반환
            UnionTag unionTag = new UnionTag();
            unionTag.setUnions(union);
            unionTag.setUnionTag(unionTagType);
            unionTags.add(unionTag);
        }
        System.out.println(unionTags);
        Unions unionTemp = new Unions();
        unionTemp.setId(this.unions_id);
        unionTemp.setUnionName(this.unionName);
        unionTemp.setUnionCategory(this.unionCategory);
        unionTemp.setUnionIntroduction(this.unionIntroduction);
        unionTemp.setUnionRecruit(this.unionRecruit);
        unionTemp.setUnionRecruitDateStart(this.unionRecruitDateStart);
        unionTemp.setUnionRecruitDateEnd(this.unionRecruitDateEnd);
        unionTemp.setUnionSkkuYn(this.unionSkkuYn);
        unionTemp.setUnionSkkuSub(this.unionSkkuSub);
        unionTemp.setUnionDues(this.unionDues);
        unionTemp.setUnionContactPhone(this.unionContactPhone);
        unionTemp.setUnionKakao(this.unionKakao);
        unionTemp.setUnionSns(this.unionSns);
        unionTemp.setUnionContactMail(this.unionContactMail);
        unionTemp.setUnionYears(this.unionYears);
        unionTemp.setUnionTags(unionTags);
        return unionTemp;
    }

}
