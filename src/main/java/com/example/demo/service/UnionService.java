package com.example.demo.service;

import com.example.demo.dto.Response;
import com.example.demo.dto.UnionsDto;
import com.example.demo.entity.Union.UnionTag;
import com.example.demo.entity.Union.Unions;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionTagRepository;
import com.example.demo.repository.UnionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnionService {

    final UnionsRepository unionsRepository;
    final UnionTagRepository unionTagRepository;
    final LoginRepository loginRepository;

    final Response response;

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
}
