package com.example.demo.controller;

import com.example.demo.dto.Response;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.Login.LoginService;
import com.example.demo.service.Login.TokenService;
import com.example.demo.service.Login.UserService;
import com.example.demo.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruit")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class RecruitController {

    final RecruitService recruitService;
    final JwtTokenProvider jwtTokenProvider;
    final LoginService loginService;
    final UserService userService;
    final TokenService tokenService;
    final Response response;

    @GetMapping("/userList")
    public ResponseEntity<?> getUserList(
            @RequestHeader("AccessToken") String accessToken,
            @RequestHeader(value = "RefreshToken", required = false) String refreshToken,
            @RequestParam Long union_id
    ) {
        if (refreshTokenAuthenticate(accessToken, refreshToken)) return tokenService.reissueAccessToken(accessToken, refreshToken);
        if (accessTokenAuthenticate(accessToken)) return tokenService.requestRefreshToken();
        return recruitService.getUserList(union_id);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?> getUserInfo(
            @RequestHeader("AccessToken") String accessToken,
            @RequestHeader(value = "RefreshToken", required = false) String refreshToken,
            @RequestParam Long user_id
    ) {
        if (refreshTokenAuthenticate(accessToken, refreshToken)) return tokenService.reissueAccessToken(accessToken, refreshToken);
        if (accessTokenAuthenticate(accessToken)) return tokenService.requestRefreshToken();
        return recruitService.getUser(user_id);
    }

    private boolean accessTokenAuthenticate(String accessToken) {
        return !jwtTokenProvider.validateToken(accessToken);
    }

    private boolean refreshTokenAuthenticate(String accessToken, String refreshToken) {
        return refreshToken != null;
    }

}
