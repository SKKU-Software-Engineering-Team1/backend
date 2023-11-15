package com.example.demo.service;

import com.example.demo.dto.ArtResponseDto;
import com.example.demo.dto.Response;
import com.example.demo.dto.UniResponseDto;
import com.example.demo.entity.Board.Board;
import com.example.demo.entity.Union.Unions;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UnionRepository;
import com.example.demo.repository.UnionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            List<Unions> artList = unionsRepository.findAll();


            if (artList.isEmpty()) {
                System.out.println("it is not existed");
                return response.fail("Union not found", HttpStatus.NO_CONTENT);
            }


            return response.success(artList, "Union", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return response.fail("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
