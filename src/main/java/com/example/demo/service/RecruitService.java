package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.Unions;
import com.example.demo.repository.UnionsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final UnionsRepository unionsRepository;

    public ResponseEntity<ResponseDto<?>> getList(Long union_id){
        try {
            List<Unions> union = unionsRepository.findAll();
            System.out.println(union_id);
            if(union.isEmpty()){
                System.out.println("it is not existed");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseDto.setFailed("Union not found"));
            }
            UnionList unionList = new UnionList();
            unionList.setList(union);
            return ResponseEntity.ok(ResponseDto.setSuccess("Union List", unionList));
        } catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDto.setFailed("INTERNAL_SERVER_ERROR"));
        }
    }

    @Getter
    @Setter
    public static class UnionList {
        List<Unions> list;
    }
}
