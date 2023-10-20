package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.UnionTag;
import com.example.demo.entity.Unions;
import com.example.demo.entity.User;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final UnionsRepository unionsRepository;
    private final LoginRepository loginRepository;

    public ResponseDto<List<User>> getUserList(Long union_id){
        try {
//            List<Unions> union = unionsRepository.findAllUnionTags();
            Optional<Unions> union = unionsRepository.findById(union_id);

            if(union.isEmpty()){
                System.out.println("it is not existed");
                return ResponseDto.setFailed("Union not found");
            }

            Unions unions = union.get();
            List<UnionTag> list = unions.getUnionTags();
            loginRepository.findAllUserTags();

            return ResponseDto.setSuccess("Union List", loginRepository.findAllUserTags());
        } catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseDto.setFailed("INTERNAL_SERVER_ERROR");
        }
    }

    public ResponseDto<Optional<User>> getUser(Long user_id){
        try {
            Optional<User> user = loginRepository.findById(user_id);

            System.out.println("here!!");
            if(user.isEmpty()){
                System.out.println("it is not existed");
                return ResponseDto.setFailed("User not found");
            }
            return ResponseDto.setSuccess("Union List", user);
        } catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseDto.setFailed("INTERNAL_SERVER_ERROR");
        }
    }

}
