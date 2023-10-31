package com.example.demo.service;

import com.example.demo.dto.RecruitingUserDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.entity.UnionTag;
import com.example.demo.entity.Unions;
import com.example.demo.entity.User;
import com.example.demo.entity.UserTag;
import com.example.demo.entity.enums.TagType;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final UnionsRepository unionsRepository;
    private final LoginRepository loginRepository;

    public ResponseDto<List<RecruitingUserDto>> getUserList(Long union_id) {
        try {
//            List<Unions> union = unionsRepository.findAllUnionTags();
            Optional<Unions> union = unionsRepository.findById(union_id);
//            System.out.println(union);

            if (union.isEmpty()) {
                System.out.println("it is not existed");
                return ResponseDto.setFailed("Union not found");
            }

            Unions unions = union.get();
            List<UnionTag> list = unions.getUnionTags();
            List<TagType> tags = list.stream()
                    .map(UnionTag::getUnionTag) // Player 객체를 이름(String)으로 매핑
                    .collect(Collectors.toList()); // 이름들을 리스트로 수집

//            loginRepository.findAllUserTags();
            List<User> users = loginRepository.findAllUserTags();
            System.out.println("RecruitService.getUserList");
//            System.out.println(users);
            List<RecruitingUserDto> result = new ArrayList<>();
            for (User user : users) {
                List<TagType> userTags = user.getUserTags().stream()
                        .map(UserTag::getUserTag) // Player 객체를 이름(String)으로 매핑
                        .collect(Collectors.toList()); // 이름들을 리스트로 수집
                for (TagType tag : tags) {
                    if (userTags.contains(tag)) {
                        RecruitingUserDto data = new RecruitingUserDto(user.getId(), user.getUserName(), user.getUserGender(), user.getUserAge(), user.getUserPhone(), user.getUserEmail(), user.getUserCampus(), user.getUserIntroduction(), userTags);
                        result.add(data);
                        System.out.println("ADD");
                        System.out.println(user);
                        break;
                    }
                }
            }
            System.out.println(result);

            return ResponseDto.setSuccess("User List", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseDto.setFailed("INTERNAL_SERVER_ERROR");
        }
    }

    public ResponseDto<RecruitingUserDto> getUser(Long user_id) {
        try {
            Optional<User> user = loginRepository.findById(user_id);

            System.out.println("here!!");
            if (user.isEmpty()) {
                System.out.println("it is not existed");
                return ResponseDto.setFailed("User not found");
            }
            User real = user.get();
            List<TagType> userTags = real.getUserTags().stream()
                    .map(UserTag::getUserTag) // Player 객체를 이름(String)으로 매핑
                    .collect(Collectors.toList()); // 이름들을 리스트로 수집

            RecruitingUserDto result = new RecruitingUserDto(real.getId(), real.getUserName(), real.getUserGender(), real.getUserAge(), real.getUserPhone(), real.getUserEmail(), real.getUserCampus(), real.getUserIntroduction(), userTags);
            return ResponseDto.setSuccess("Union List", result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseDto.setFailed("INTERNAL_SERVER_ERROR");
        }
    }

}
