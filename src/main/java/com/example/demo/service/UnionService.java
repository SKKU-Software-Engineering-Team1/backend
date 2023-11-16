package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.dto.Login.ModifyDto;
import com.example.demo.dto.Login.UserResponseDto;

import com.example.demo.entity.Union.UnionTag;
import com.example.demo.entity.Union.UnionUser;
import com.example.demo.dto.Response;
import com.example.demo.dto.UnionsDto;
import com.example.demo.entity.Union.UnionTag;
import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.User.UserTag;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.enums.*;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionTagRepository;
import com.example.demo.repository.UnionsRepository;
import com.example.demo.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnionService {

    final UnionsRepository unionsRepository;
    final UnionTagRepository unionTagRepository;
    final LoginRepository loginRepository;
    final Response response;
//    public UniResponseDto<?> getUnionInfo(String uniName){
//
////        String uniName = dto.getUnionName();
//
//        // email이 DB 내부에 있는지만 확인
//        Unions uni = unionRepository.findByUnionName(uniName);
//
//        if(uni == null){
//            System.out.println("it is not existed");
//
//        } else{
//            System.out.println("Success to find union");
//            return UniResponseDto.setSuccess("Success", uni);
//        }
//
//        return UniResponseDto.setSuccess("Success", uni);
//    }
    public ResponseEntity<?> getUnionInfo(Long unionId){
        try {
            Optional<Unions> uni = unionsRepository.findById(unionId);


            if (uni.isEmpty()) {
                System.out.println("it is not existed");
                return response.fail("Union not found", HttpStatus.NO_CONTENT);
            }
            Unions uni_info = uni.get();
            List<UnionTag> list = uni_info.getUnionTags();
            List<TagType> tags = list.stream()
                    .map(UnionTag::getUnionTag) // Player 객체를 이름(String)으로 매핑
                    .collect(Collectors.toList()); // 이름들을 리스트로 수집
            List<String> temp = new ArrayList<>();
            temp.add("1");
            UnionsDto data = new UnionsDto(uni_info.getId(), uni_info.getUnionName(), uni_info.getUnionCategory(), uni_info.getUnionIntroduction(), uni_info.getUnionRecruit(),uni_info.getUnionRecruitDateStart(),uni_info.getUnionRecruitDateEnd(), false,uni_info.getUnionSkkuSub(), uni_info.getUnionDues(),uni_info.getUnionContactPhone(), uni_info.getUnionKakao(),uni_info.getUnionSns(),uni_info.getUnionContactMail(),uni_info.getUnionYears(),tags,temp);
            return response.success(data, "Union", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return response.fail("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<?> editUnionInfo(UnionsDto dto) {
        try {
            Unions target = unionsRepository.findById(dto.getUnions_id()).orElse(null);
            if (target == null) {
                return response.fail("해당 동아리 id가 없습니다.", HttpStatus.BAD_REQUEST);
            }
            Unions unionEntity = dto.toEntity(target);

            // union의 모든 기존 tag 정보 삭제
            List<UnionTag> beforeUnionTag = target.getUnionTags();
            System.out.println(beforeUnionTag);
            unionTagRepository.deleteAll(beforeUnionTag);

            // union의 새로운 tag 정보 추가
            List<UnionTag> afterUnionTag = unionEntity.getUnionTags();
            unionTagRepository.saveAll(afterUnionTag);

            unionsRepository.save(unionEntity);
            return response.success("동아리 정보 변경 성공");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return response.fail("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> regUnion(UnionsDto dto) {


        Long unionId = dto.getUnions_id();
        String unionName = dto.getUnionName();
        UnionCategoryType unionCategory = dto.getUnionCategory();
        String unionIntroduction = dto.getUnionIntroduction();
        String unionRecruit = dto.getUnionRecruit();
        LocalDate unionRecruitDateStart = dto.getUnionRecruitDateStart();
        LocalDate unionRecruitDateEnd = dto.getUnionRecruitDateEnd();
//        boolean unionSkkuYn = dto.getUnionSkkuYn();
        UnionSubType unionSkkuSub = dto.getUnionSkkuSub();
        String unionDues = dto.getUnionDues();
        String unionContactPhone = dto.getUnionContactPhone();
        String unionKakao = dto.getUnionKakao();
        String unionSns = dto.getUnionSns();
        String unionContactMail = dto.getUnionContactMail();
        String unionYears = dto.getUnionYears();
        List<UnionTag> unionTags = dto.getUnionTags();

        // Name이 DB 내부에 있는지만 확인
//        final boolean unionExist = UnionsRepository.existsByUnionName(unionName);
        final boolean unionExist = false;
        // 유저 없음 회원가입 가능!
        if (!unionExist) {

            try {



                Unions uni = Unions.builder()
                        .Id(unionId)
                        .unionName(unionName)
                        .unionCategory(unionCategory)
                        .unionIntroduction(unionIntroduction)
                        .unionRecruit(unionRecruit)
                        .unionRecruitDateStart(unionRecruitDateStart)
                        .unionRecruitDateEnd(unionRecruitDateEnd)
                        .unionSkkuYn(false)
                        .unionSkkuSub(unionSkkuSub)
                        .unionDues(unionDues)
                        .unionContactPhone(unionContactPhone)
                        .unionKakao(unionKakao)
                        .unionSns(unionSns)
                        .unionContactMail(unionContactMail)
                        .unionYears(unionYears)
                        .unionTags(unionTags)
                        .build();


                // user 정보 저장
                unionsRepository.save(uni);

//                // user Tag 정보도 DB에 저장
//                for (UserTag userTag : userTagList) {
//                    userTagRepository.save(userTag);
//                }

            } catch (Exception e) {
                return response.fail("데이터 베이스 오류입니다.", HttpStatus.BAD_REQUEST);
            }

        } else {
            return response.fail("이미 등록된 동아리입니다.", HttpStatus.BAD_REQUEST);
        }

        return response.success("회원가입에 성공했습니다.");
    }

}
