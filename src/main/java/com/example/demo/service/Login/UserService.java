package com.example.demo.service.Login;

import com.example.demo.dto.Login.ModifyDto;
import com.example.demo.dto.Login.SignUpDto;
import com.example.demo.dto.Response;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserTagDto;
import com.example.demo.entity.User.UserTag;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.enums.Authority;
import com.example.demo.entity.enums.CampusType;
import com.example.demo.entity.enums.GenderType;
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
import java.util.Collections;
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
    public ResponseEntity<?> getUserInformation(String AccessToken){
        // 1. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(AccessToken);
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(o->o.getAuthority().equals("ROLE_ADMIN"));

//        // 2. fetch join 단일 user join 해오는 거는 좀 있다가 할게요 일단은 LAZY로 만듭니다.
//        final Users users = loginRepository.findUserWithUserTags(authentication.getName());

        Users users1 = loginRepository.findByUserEmail(authentication.getName());

        if(users1.getUserTags().isEmpty()){
            String userGender = users1.getUserGender().toString();
            String userCampus = users1.getUserCampus().toString();

            UserDto userDto = UserDto.builder()
                    .userId(users1.getId())
                    .userName(users1.getUserNames())
                    .userGender(userGender)
                    .userAge(users1.getUserAge())
                    .userPhoneNumber(users1.getUserPhone())
                    .userEmail(users1.getUserEmail())
                    .userCampus(userCampus)
                    .build();
            if(users1.getUserIntroduction() != null){
                userDto.setUserIntroduction(users1.getUserIntroduction());
            }

            if(isAdmin)
                userDto.setUserRole("ROLE_ADMIN");
            else
                userDto.setUserRole("ROLE_USER");

            return response.success(userDto, "성공했습니다.", HttpStatus.OK);
        } else{

            Users users = loginRepository.findUserWithUserTags(authentication.getName());
            // 화면용 정보 중 enum 타입들 string 변환
            String userGender = users.getUserGender().toString();
            String userCampus = users.getUserCampus().toString();

            List<String> userTags = new ArrayList<>();
            List<UserTag> userTagList = users.getUserTags();

            for(int i = 0; i < userTagList.size() ; i++){
                userTags.add(userTagList.get(i).getUserTag().toString());
            }

            UserDto userDto = UserDto.builder()
                    .userId(users.getId())
                    .userName(users.getUserNames())
                    .userGender(userGender)
                    .userAge(users.getUserAge())
                    .userPhoneNumber(users.getUserPhone())
                    .userEmail(users.getUserEmail())
                    .userCampus(userCampus)
                    .userTags(userTags).build();
            if(users1.getUserIntroduction() != null){
                userDto.setUserIntroduction(users1.getUserIntroduction());
            }
            if(isAdmin)
                userDto.setUserRole("ROLE_ADMIN");
            else
                userDto.setUserRole("ROLE_USER");

            return response.success(userDto, "성공했습니다.", HttpStatus.OK);
        }




    }

    // User 관심사 정보만 가져오기
    public ResponseEntity<?> getUserTagInformation(String AccessToken){

        // 1. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(AccessToken);

        // 2. fetch join으로 데이터를 가지고 옵니다.
        final Users users = loginRepository.findUserWithUserTags(authentication.getName());

        // Tag들 string 목록으로 변환
        List<String> userTags = new ArrayList<>();
        List<UserTag> userTagList = users.getUserTags();

        for(int i = 0; i < userTagList.size() ; i++){
            userTags.add(userTagList.get(i).getUserTag().toString());
        }

        UserTagDto userTagDto = UserTagDto.builder()
                .userName(users.getUserNames())
                .userTags(userTags).build();

        return response.success(userTagDto, "성공했습니다.", HttpStatus.OK);
    }

    // User 관심사 정보만 가져오기
    public ResponseEntity<?> deleteUserInfomation(String accessToken){

        // 1. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        // 2. fetch join으로 데이터를 가지고 옵니다.
        Users users = loginRepository.findByUserEmail(authentication.getName());

        if(users.getUserTags().isEmpty()){
            // User 정보 삭제
            loginRepository.delete(users);
        }
        else{
            // Tag 정보 삭제
            List<UserTag> userTags = users.getUserTags();

            for(UserTag userTag: userTags){
                userTagRepository.delete(userTag);
            }

            // User 정보 삭제
            loginRepository.delete(users);
        }

        return response.success("성공했습니다.");
    }

    public ResponseEntity<?> ModifyUser(String accessToken, ModifyDto dto) {

        String userEmail = dto.getUserEmail();

        List<String> userTags = dto.getUserTags();
        String userIntroduction = dto.getUserIntroduction();


        // 1. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        // 2. fetch join으로 데이터를 가지고 옵니다.
        Users users1 = loginRepository.findByUserEmail(authentication.getName());
        if(users1.getUserTags().isEmpty()){
            users1.setUserEmail(userEmail);

            users1.setUserIntroduction(userIntroduction);

            try{
                // user 정보 저장
                loginRepository.save(users1);

                // user의 새로운 tag 정보 추가
                List<UserTag> afterUserTag = Users.makeUserTags(users1, userTags);

                // user Tag 정보도 DB에 저장
                for (UserTag userTag : afterUserTag) {
                    userTagRepository.save(userTag);
                }

            } catch(Exception e){
                return response.fail("데이터 베이스 오류입니다.", HttpStatus.BAD_REQUEST);
            }
            return response.success("회원정보 변경에 성공했습니다.");
        }else{
            Users users = loginRepository.findUserWithUserTags(authentication.getName());

            users.setUserEmail(userEmail);

            users.setUserIntroduction(userIntroduction);

            try{
                // user 정보 저장
                loginRepository.save(users);

                // user의 모든 기존 tag정보 삭제
                List<UserTag> beforeUserTag = users.getUserTags();

                for(UserTag userTag: beforeUserTag){
                    userTagRepository.delete(userTag);
                }

                // user의 새로운 tag 정보 추가
                List<UserTag> afterUserTag = Users.makeUserTags(users, userTags);


                // user Tag 정보도 DB에 저장
                for (UserTag userTag : afterUserTag) {
                    userTagRepository.save(userTag);
                }

            } catch(Exception e){
                return response.fail("데이터 베이스 오류입니다.", HttpStatus.BAD_REQUEST);
            }

            return response.success("회원정보 변경에 성공했습니다.");
        }

    }




}
