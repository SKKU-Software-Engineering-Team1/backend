package com.example.demo.service;

import com.example.demo.dto.ResponseDto;

import com.example.demo.dto.UniResponseDto;
import com.example.demo.dto.UnionDto;
import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.User.Users;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnionService {


    final UnionRepository unionRepository;

    public UniResponseDto<?> getUnionInfo(String uniName){

//        String uniName = dto.getUnionName();

        // email이 DB 내부에 있는지만 확인
        Unions uni = unionRepository.findByUnionName(uniName);

        if(uni == null){
            System.out.println("it is not existed");

        } else{
            System.out.println("Success to find union");
            return UniResponseDto.setSuccess("Success", uni);
        }

        return UniResponseDto.setSuccess("Success", uni);
    }
}
