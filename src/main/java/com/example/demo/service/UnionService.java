package com.example.demo.service;

import com.example.demo.dto.Login.UserResponseDto;

import com.example.demo.dto.RecruitingUserDto;
import com.example.demo.dto.Response;
import com.example.demo.dto.UniResponseDto;
import com.example.demo.dto.UnionDto;
import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.User.UserTag;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.enums.TagType;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionRepository;
import com.example.demo.repository.UnionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnionService {


    final UnionsRepository unionsRepository;
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

}
