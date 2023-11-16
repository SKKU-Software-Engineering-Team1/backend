package com.example.demo.service.Login;

import com.example.demo.dto.Response;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UserTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class TokenService {

    final LoginRepository loginRepository;
    final UserTagRepository userTagRepository;
    final EntityManager em;
    final Response response;
    final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> requestRefreshToken() {
        return response.fail("AccessToken이 만료되었습니다.", HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<?> reissueAccessToken(String AccessToken, String RefreshToken) {

        // 1. Refresh Token 검증
        if (!jwtTokenProvider.validateToken(RefreshToken))
            return response.fail("Refresh Token 정보가 유효하지 않습니다.", HttpStatus.BAD_REQUEST);

        // 2. RefreshToken은 정상인 경우?
        Authentication authentication = jwtTokenProvider.getAuthentication(RefreshToken); // Refresh Token의 auth정보 가져옴

        return response.success(jwtTokenProvider.generateToken(authentication));

    }


}
