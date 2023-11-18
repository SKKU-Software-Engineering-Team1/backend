package com.example.demo.service;

import com.example.demo.dto.ArtResponseDto;
import com.example.demo.dto.Response;
import com.example.demo.dto.UniResponseDto;
import com.example.demo.dto.UnionsDto;
import com.example.demo.entity.Board.Board;
import com.example.demo.entity.Union.UnionTag;
import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.enums.TagType;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UnionRepository;
import com.example.demo.repository.UnionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    final ArticleRepository articleRepository;
    final UnionsRepository unionsRepository;
    final Response response;
//    public ArtResponseDto<?> getArticleList(String uniName){
//
//////        String uniName = dto.getUnionName();
////
////        // email이 DB 내부에 있는지만 확인
//        List<Board> aritcleList= articleRepository.findAll();
////
////        if(uni == null){
////            System.out.println("it is not existed");
////
////        } else{
////            System.out.println("Success to find union");
////            return UniResponseDto.setSuccess("Success", uni);
////        }
////
//        return ArtResponseDto.setSuccess("Success", null);
//    }
    public ResponseEntity<?> getArticleList(){
        try {
            List<Unions> unionList = unionsRepository.findAll();
            List<UnionsDto> artList = new ArrayList<>();
            for(int i=0;i<unionList.size();i++){
                Unions uni_info = unionList.get(i);
//                Unions uni_info = uni.get();
                List<UnionTag> list = uni_info.getUnionTags();
                List<TagType> tags = list.stream()
                        .map(UnionTag::getUnionTag) // Player 객체를 이름(String)으로 매핑
                        .collect(Collectors.toList()); // 이름들을 리스트로 수집
                List<String> temp = new ArrayList<>();
                temp.add("1");
                UnionsDto data = new UnionsDto(uni_info.getId(), uni_info.getUnionName(), uni_info.getUnionCategory(), uni_info.getUnionIntroduction(), uni_info.getUnionRecruit(),uni_info.getUnionRecruitDateStart(),uni_info.getUnionRecruitDateEnd(), false,uni_info.getUnionSkkuSub(), uni_info.getUnionDues(),uni_info.getUnionContactPhone(), uni_info.getUnionKakao(),uni_info.getUnionSns(),uni_info.getUnionContactMail(),uni_info.getUnionYears(),tags,temp);
                artList.add(data);
            }

            if (unionList.isEmpty()) {
                System.out.println("it is not existed");
                return response.fail("Union List is not found", HttpStatus.NO_CONTENT);
            }


            return response.success(artList, "Article", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return response.fail("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
