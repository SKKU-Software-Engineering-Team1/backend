package com.example.demo.controller;

import com.example.demo.dto.Response;
import com.example.demo.dto.UnionsDto;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.Login.TokenService;
import com.example.demo.service.UnionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/union")
@RequiredArgsConstructor
public class UnionController {

    final JwtTokenProvider jwtTokenProvider;
    final UnionService unionService;
    final TokenService tokenService;
    final Response response;

    @PutMapping("/edit")
    public ResponseEntity<?> editUnionInfo(
            @RequestHeader("AccessToken") String accessToken,
            @RequestHeader(value = "RefreshToken", required = false) String refreshToken,
            @RequestBody UnionsDto requestBody
    ) {
        if (refreshTokenAuthenticate(accessToken, refreshToken))
            return tokenService.reissueAccessToken(accessToken, refreshToken);
        if (accessTokenAuthenticate(accessToken)) return tokenService.requestRefreshToken();
        return unionService.editUnionInfo(requestBody);
    }

    private boolean accessTokenAuthenticate(String accessToken) {
        return !jwtTokenProvider.validateToken(accessToken);
    }

    private boolean refreshTokenAuthenticate(String accessToken, String refreshToken) {
        return refreshToken != null;
    }

}
