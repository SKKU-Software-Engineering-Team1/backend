package com.example.demo.service;

import com.example.demo.dto.Login.LoginDto;
import com.example.demo.dto.Response;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserTagDto;
import com.example.demo.entity.*;
import com.example.demo.entity.User.UserTag;
import com.example.demo.entity.User.Users;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    final LoginRepository loginRepository;
    final UserTagRepository userTagRepository;
    final EntityManager em;
    final Response response;
    final JwtTokenProvider jwtTokenProvider;

//     User 정보 전체 가져오기
//     TEXT Description이 필요한 건가요?? 2023/10/28 백명규
    public ResponseEntity<?> getUserInfomation(String AccessToken){

        System.out.println(AccessToken);
        // 1. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(AccessToken);

        // 2. fetch join 단일 user join 해오는 거는 좀 있다가 할게요 일단은 LAZY로 만듭니다.
        final Users users = loginRepository.findByUserEmail(authentication.getName());

        if(users == null || !jwtTokenProvider.validateToken(AccessToken))
            return response.fail("유효하지 않은 AccessToken입니다", HttpStatus.BAD_REQUEST);

        // 화면용 정보 중 enum 타입들 string 변환
        String userGender = users.getUserGender().toString();
        String userSchool = users.getUserSchool().toString();

        // Tag들 string 목록으로 변환
        List<String> userTags = new ArrayList<>();
        List<UserTag> userTagList = users.getUserTags();

        for(int i = 0; i < userTagList.size() ; i++){
            userTags.add(userTagList.get(i).getUserTag().toString());
        }

        UserDto userDto = UserDto.builder()
                .userName(users.getUsername())
                .userGender(userGender)
                .userAge(users.getUserAge())
                .userPhoneNumber(users.getUserPhonenumber())
                .userEmail(users.getUserEmail())
                .userSchool(userSchool)
                .userTags(userTags).build();

        return response.success(userDto, "성공했습니다.", HttpStatus.OK);
    }

    // User 관심사 정보만 가져오기
    public ResponseEntity<?> getUserTagInfomation(LoginDto dto){

        String userEmail = dto.getUserEmail();

        // fetch join 단일 user join 해오는 거는 좀 있다가 하죠.
        final Users users = loginRepository.findByUserEmail(userEmail);

        // Tag들 string 목록으로 변환
        List<String> userTags = new ArrayList<>();
        List<UserTag> userTagList = users.getUserTags();

        for(int i = 0; i < userTagList.size() ; i++){
            userTags.add(userTagList.get(i).getUserTag().toString());
        }

        UserTagDto userTagDto = UserTagDto.builder()
                .userName(users.getUsername())
                .userTags(userTags).build();

//        System.out.println(userTagDto);
        return response.success("일단 성공으로 놔둠");
    }
}