package com.example.demo.service;

import com.example.demo.dto.Login.*;
import com.example.demo.dto.Response;
import com.example.demo.entity.Token;
import com.example.demo.entity.enums.Authority;
import com.example.demo.entity.enums.GenderType;
import com.example.demo.entity.enums.SchoolType;
import com.example.demo.entity.User.Users;
import com.example.demo.entity.User.UserTag;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserTagRepository;
import com.example.demo.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    final LoginRepository loginRepository;
    final UserTagRepository userTagRepository;
    final TokenRepository tokenRepository;
    final EntityManager em;


    // security를 추가하면서 생긴 것들
    final Response response;
    final PasswordEncoder passwordEncoder;
    final JwtTokenProvider jwtTokenProvider;
    final AuthenticationManagerBuilder authenticationManagerBuilder;

    // SignUp 완료
    public ResponseEntity<?> signUp(SignUpDto dto){

        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPhoneNumber = dto.getUserPhoneNumber();

        int userAge = dto.getUserAge();
        String userName = dto.getUserName();
        String userGender = dto.getUserGender();
        List<String> userTags = dto.getUserTags();
        String userSchool = dto.getUserSchool();


        // email이 DB 내부에 있는지만 확인
        final boolean userExist = loginRepository.existsByUserEmail(userEmail);

        // 유저 없음 회원가입 가능!
        if(!userExist){

            try{

                GenderType genderType= GenderType.getGender(userGender);
                SchoolType schoolType = SchoolType.getSchool(userSchool);

                Users users = Users.builder()
                        .userEmail(userEmail)
                        .userPassword(passwordEncoder.encode(userPassword))
                        .userName(userName)
                        .userGender(genderType)
                        .userAge(userAge)
                        .userSchool(schoolType)
                        .userPhonenumber(userPhoneNumber)
                        .roles(Collections.singletonList(Authority.USER.name()))
                        .build();

                // user Tag 정보 저장용 Tags 생성
                List<UserTag> userTagList = Users.makeUserTags(users, userTags);

                // user 정보 저장
                loginRepository.save(users);

                // user Tag 정보도 DB에 저장
                for (UserTag userTag : userTagList) {
                    System.out.println(userTag.getUserTag());
                    userTagRepository.save(userTag);
                }

            } catch(Exception e){
                return response.fail("데이터 베이스 오류입니다.", HttpStatus.BAD_REQUEST);
            }

        } else{
            return response.fail("이미 회원가입된 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        return response.success("회원가입에 성공했습니다.");
    }

    // login
    public ResponseEntity<?> login(LoginDto dto){

        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();

        try{

            Users users = loginRepository.findByUserEmail(userEmail);

            if(users == null) // DB에서 없어서 조회 불가능
                return response.fail("해당 유저는 존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST);
            else{ // 이 경우에 아이디가 DB에 존재

                // 1. Login ID / PW 기반으로 Authentication 객체를 생성합니다.
                // authentication는 인증 여부를 확인하는 authenticated 값이 false입니다.
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userEmail, userPassword);

                // 2. 실제 검증 (사용자의 비밀번호 체크)를 하는 부분입니다.
                // authenticate 메서드가 실행될 때 CustomUserDetailService에서 만든 loadUserByUsername 메서드 실행
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

                // 3. 인증 정보를 기반으로 Jwt 토큰 생성
                UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

                Token token = tokenRepository.findByAuthName(userEmail);
                // 로그인 후 로그아웃 후 정상 접속
                if(token == null){
                    // 4. Token 테이블에 RefreshToken 내용 저장
                    tokenRepository.save(
                            Token.builder()
                                    .authName(authentication.getName())
                                    .refreshToken(tokenInfo.getRefreshToken())
                                    .refreshTokenExpirationTime(tokenInfo.getAccessTokenExpiresIn())
                                    .build());
                }
                // 로그인 후 로그아웃 처리가 제대로 안됐을 때 값 duplicate되는 것 방지
                else{
                    token.setRefreshToken(tokenInfo.getRefreshToken());
                    token.setRefreshTokenExpirationTime(tokenInfo.getAccessTokenExpiresIn());
                    tokenRepository.save(token);
                }

                return response.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK);

            }
        } catch(Exception e){
            return response.fail("데이터 베이스 오류입니다.", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> reissue(Reissue reissue){

        // 1. Refresh Token 검증
        if(!jwtTokenProvider.validateToken(reissue.getRefreshToken())){
            return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(reissue.getAccessToken());

        // 3. DB에서 Refresh Token 값 userEmail로 가져옴
        Token token = tokenRepository.findByAuthName(authentication.getName());
        String refreshToken = token.getRefreshToken();

        // 4. 새로운 토큰 생성
        UserResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        // 4. 토큰 업데이트
        token.setAuthName(authentication.getName());
        token.setRefreshToken(tokenInfo.getRefreshToken());
        token.setRefreshTokenExpirationTime(tokenInfo.getAccessTokenExpiresIn());

        tokenRepository.save(token);

        return response.success(tokenInfo, "Token 정보가 갱신되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<?> logout(Logout logout) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(logout.getAccessToken())) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }

        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logout.getAccessToken());

        // 3. 해당 User email 로 저장된 Refresh Token 이 있는지 여부를 확인 후 있을 경우 삭제합니다.
        Token token = tokenRepository.findByAuthName(authentication.getName());
        if(token != null)
           // Refresh Token DB에서 삭제
           tokenRepository.deleteById(token.getId());

        // 4. 해당 Access Token 유효시간 가지고 와서 BlackList 로 저장하기
        Long expiration = jwtTokenProvider.getExpiration(logout.getAccessToken());

        return response.success("로그아웃 되었습니다.");
    }

    public ResponseEntity<?> authority() {
        // SecurityContext에 담겨 있는 authentication userEamil 정보
        String userEmail = SecurityUtil.getCurrentUserEmail();

        Users user = loginRepository.findByUserEmail(userEmail);

        if (user == null)
            throw new UsernameNotFoundException("No authentication information");


        // add ROLE_ADMIN
        user.getRoles().add(Authority.ADMIN.name());
        loginRepository.save(user);

        return response.success();
    }

}
