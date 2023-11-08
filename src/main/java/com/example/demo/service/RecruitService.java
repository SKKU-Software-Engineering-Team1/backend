package com.example.demo.service;

import com.example.demo.dto.RecruitingUserDto;
import com.example.demo.dto.Response;
import com.example.demo.entity.Union.UnionTag;
import com.example.demo.entity.Union.Unions;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.User.UserTag;
import com.example.demo.entity.enums.TagType;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UnionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitService {

    final UnionsRepository unionsRepository;
    final LoginRepository loginRepository;

    final Response response;

    public ResponseEntity<?> getUserList(Long union_id) {
        try {
            Optional<Unions> union = unionsRepository.findById(union_id);

            if (union.isEmpty()) {
                System.out.println("it is not existed");
                return response.fail("Union not found", HttpStatus.NOT_FOUND);
            }

            Unions unions = union.get();
            List<UnionTag> list = unions.getUnionTags();
            List<TagType> tags = list.stream().map(UnionTag::getUnionTag).collect(Collectors.toList()); // 이름들을 리스트로 수집

            List<Users> users = loginRepository.findAllUserTags();
            List<RecruitingUserDto> result = new ArrayList<>();
            for (Users user : users) {
                List<TagType> userTags = user.getUserTags().stream().map(UserTag::getUserTag).collect(Collectors.toList()); // 이름들을 리스트로 수집
                for (TagType tag : tags) {
                    if (userTags.contains(tag)) {
                        RecruitingUserDto data = new RecruitingUserDto(user.getId(), user.getUsername(), user.getUserGender(), user.getUserAge(), user.getUserPhone(), user.getUserEmail(), user.getUserCampus(), user.getUserIntroduction(), userTags);
                        if (!result.contains(data)) {
                            result.add(data);
                        }
                        break;
                    }
                }
            }
            return response.success(result, "User List", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return response.fail("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getUser(Long user_id) {
        try {
            Optional<Users> user = loginRepository.findById(user_id);

            if (user.isEmpty()) {
                System.out.println("it is not existed");
                return response.fail("User not found", HttpStatus.NO_CONTENT);
            }
            Users real = user.get();
            List<TagType> userTags = real.getUserTags().stream()
                    .map(UserTag::getUserTag) // Player 객체를 이름(String)으로 매핑
                    .collect(Collectors.toList()); // 이름들을 리스트로 수집

            RecruitingUserDto result = new RecruitingUserDto(real.getId(), real.getUsername(), real.getUserGender(), real.getUserAge(), real.getUserPhone(), real.getUserEmail(), real.getUserCampus(), real.getUserIntroduction(), userTags);
            return response.success(result, "Union List", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return response.fail("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
