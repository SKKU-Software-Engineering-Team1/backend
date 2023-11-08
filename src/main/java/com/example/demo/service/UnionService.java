package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.dto.Login.ModifyDto;
import com.example.demo.dto.Login.UserResponseDto;

import com.example.demo.entity.Union.UnionTag;
import com.example.demo.entity.Union.UnionUser;
import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.User.UserTag;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.enums.*;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionRepository;
import com.example.demo.repository.UnionsRepository;
import com.example.demo.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    final Response response;

    final EntityManager em;
    final JwtTokenProvider jwtTokenProvider;
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
            Optional<Unions> uni = unionsRepository.findUnionsById(unionId);


            if (uni.isEmpty()) {
                System.out.println("it is not existed");
                return response.fail("Union not found", HttpStatus.NO_CONTENT);
            }
            Unions uni_info = uni.get();

            return response.success(uni_info, "Union", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return response.fail("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    public ResponseEntity<?> regUnion(UnionsDto dto) {


        Long unionId = dto.getId();
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
        List<UnionUser> unionUsers = dto.getUnionUsers();
//
//        // 1. Access Token 에서 User email 을 가져옵니다.
//        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
//
//        // 2. fetch join으로 데이터를 가지고 옵니다.
//        final Unions uni = UnionsRepository.findUserWithUserTags(authentication.getName());
//
//        users.setUserEmail(userEmail);
//        users.setUserPassword(userPassword);
//        users.setUserPhone(userPhoneNumber);
//
//        users.setUserAge(userAge);
//        users.setUserNames(userName);
//        users.setUserGender(genderType);
//        users.setUserCampus(campType);
        // Name이 DB 내부에 있는지만 확인
//        final boolean unionExist = UnionsRepository.existsByUnionName(unionName);
        final boolean unionExist = false;
        // 유저 없음 회원가입 가능!
        if (!unionExist) {

            try {


//        private Long Id;
//        private String unionName;
//        private UnionCategoryType unionCategory;
//        private String unionIntroduction;
//        private String unionRecruit;
//        private LocalDate unionRecruitDateStart;
//        private LocalDate unionRecruitDateEnd;
//        private boolean unionSkkuYn = false;
//        private UnionSubType unionSkkuSub;
//        private String unionDues;
//        private String unionContactPhone;
//        private String unionKakao;
//        private String unionSns;
//        private String unionContactMail;
//        private String unionYears;
//        private List<UnionTag> unionTags = new ArrayList<>();
//        private List<UnionUser> unionUsers = new ArrayList<>();
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
                        .unionUsers(unionUsers)
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
