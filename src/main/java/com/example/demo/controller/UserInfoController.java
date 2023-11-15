package com.example.demo.controller;

import com.example.demo.dto.Login.ModifyDto;
import com.example.demo.dto.Response;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.Login.LoginService;
import com.example.demo.service.Login.TokenService;
import com.example.demo.service.Login.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/UserInfo")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class UserInfoController {

    final JwtTokenProvider jwtTokenProvider;
    final LoginService loginService;
    final UserService userService;
    final TokenService tokenService;
    final Response response;

    @GetMapping("/userInfo")
    public ResponseEntity<?> UserInfo(@RequestHeader("AccessToken") String accessToken
            , @RequestHeader(value = "RefreshToken", required = false) String refreshToken) {

        // 모든 코드에 이거 복붙해서 아래 부분 return만 한 줄 본인이 넣으실거 추가하시면 됩니다.
        // RefreshToken이랑 다 날라가는 거 검증 완료했습니다.
        if (refreshToken == null) {
            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if (!jwtTokenProvider.validateToken(accessToken))
                return tokenService.requestRefreshToken();
            else
                return userService.getUserInformation(accessToken); // 여기만 바꿔주세요

        } else
            return tokenService.reissueAccessToken(accessToken, refreshToken);

    }

    @PostMapping("/userShortInfo")
    public ResponseEntity<?> UserShortInfo(@RequestHeader("AccessToken") String accessToken
                                           , @RequestHeader(value = "RefreshToken", required = false) String refreshToken){
        // 모든 코드에 이거 복붙해서 아래 부분 return만 한 줄 본인이 넣으실거 추가하시면 됩니다.
        // RefreshToken이랑 다 날라가는 거 검증 완료했습니다.
        if (refreshToken == null) {
            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if (!jwtTokenProvider.validateToken(accessToken)) {
                return tokenService.requestRefreshToken();
            } else {
                return userService.getUserTagInformation(accessToken); // 여기만 바꿔주세요
            }
        } else {
            return tokenService.reissueAccessToken(accessToken, refreshToken);
        }
    }

    @PostMapping("/userDelete")
    public ResponseEntity<?> UserDelete(@RequestHeader("AccessToken") String accessToken
            , @RequestHeader(value = "RefreshToken", required = false) String refreshToken) {

        if (refreshToken == null) {
            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if (!jwtTokenProvider.validateToken(accessToken))
                return tokenService.requestRefreshToken();
            else
                return userService.deleteUserInfomation(accessToken); // 여기만 바꿔주세요

        } else
            return tokenService.reissueAccessToken(accessToken, refreshToken);

    }

    @PostMapping("/ModifyInfo")
    public ResponseEntity<?> UserModifyInfo(@RequestHeader("AccessToken") String accessToken
            , @RequestHeader(value = "RefreshToken", required = false) String refreshToken, @RequestBody ModifyDto modifyDto){

        if (refreshToken == null) {
            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if (!jwtTokenProvider.validateToken(accessToken))
                return tokenService.requestRefreshToken();
            else
                return userService.ModifyUser(accessToken, modifyDto); // 여기만 바꿔주세요

        } else
            return tokenService.reissueAccessToken(accessToken, refreshToken);
    }
}
