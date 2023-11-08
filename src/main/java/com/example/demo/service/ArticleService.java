package com.example.demo.service;

import com.example.demo.dto.ArtResponseDto;
import com.example.demo.dto.UniResponseDto;
import com.example.demo.entity.Board.Board;
import com.example.demo.entity.Union.Unions;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UnionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public ArtResponseDto<?> getArticleList(String uniName){

////        String uniName = dto.getUnionName();
//
//        // email이 DB 내부에 있는지만 확인
        List<Board> aritcleList= articleRepository.findAll();
//
//        if(uni == null){
//            System.out.println("it is not existed");
//
//        } else{
//            System.out.println("Success to find union");
//            return UniResponseDto.setSuccess("Success", uni);
//        }
//
        return ArtResponseDto.setSuccess("Success", null);
    }

}
