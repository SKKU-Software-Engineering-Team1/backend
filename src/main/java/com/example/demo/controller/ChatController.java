package com.example.demo.controller;

import com.example.demo.dto.Chat.ChatMessageDto;
import com.example.demo.dto.Chat.CreateRoom;
import com.example.demo.dto.Response;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.service.Chat.ChatService;
import com.example.demo.service.Login.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ChatController {
    final ChatService chatService;
    final JwtTokenProvider jwtTokenProvider;
    final TokenService tokenService;
    final Response response;


    @PostMapping("/createChatRoom")
    public ResponseEntity<?> createChatRoom(@RequestHeader("AccessToken") String accessToken
            , @RequestHeader(value = "RefreshToken", required = false) String refreshToken
            , @RequestBody CreateRoom createRoom){

        // 모든 코드에 이거 복붙해서 아래 부분 return만 한 줄 본인이 넣으실거 추가하시면 됩니다.
        // RefreshToken이랑 다 날라가는 거 검증 완료했습니다.
        if(refreshToken == null){
            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if(!jwtTokenProvider.validateToken(accessToken))
                return tokenService.requestRefreshToken();
            else
                return chatService.createChatRoomWithUserEmail(accessToken,createRoom); // 여기만 바꿔주세요

        }
        else
            return tokenService.reissueAccessToken(accessToken, refreshToken);

    }

    @PostMapping("/requestChat")
    public ResponseEntity<?> requestChat(@RequestHeader("AccessToken") String accessToken
            , @RequestHeader(value = "RefreshToken", required = false) String refreshToken
            , @RequestBody ChatMessageDto chatMessageDto){

        // 모든 코드에 이거 복붙해서 아래 부분 return만 한 줄 본인이 넣으실거 추가하시면 됩니다.
        // RefreshToken이랑 다 날라가는 거 검증 완료했습니다.
        if(refreshToken == null){

            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if(!jwtTokenProvider.validateToken(accessToken))
                return tokenService.requestRefreshToken();
            else{
                return chatService.requestChat(chatMessageDto); // 여기만 바꿔주세요
            }


        }
        else
            return tokenService.reissueAccessToken(accessToken, refreshToken);
    }

    @GetMapping("/getChatRoomRecord")
    public ResponseEntity<?> getChatRoomRecord(@RequestHeader("AccessToken") String accessToken
            , @RequestHeader(value = "RefreshToken", required = false) String refreshToken){

        // 모든 코드에 이거 복붙해서 아래 부분 return만 한 줄 본인이 넣으실거 추가하시면 됩니다.
        // RefreshToken이랑 다 날라가는 거 검증 완료했습니다.
        if(refreshToken == null){

            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if(!jwtTokenProvider.validateToken(accessToken)){
                return tokenService.requestRefreshToken();
            }
            else{
                return chatService.findAllRoomWithUserEmail(accessToken); // 여기만 바꿔주세요
            }
        }
        else
            return tokenService.reissueAccessToken(accessToken, refreshToken);
    }

    @GetMapping("/findAllTextWithRoomID/{roomId}")
    public ResponseEntity<?> findAllTextWithRoomId(@RequestHeader("AccessToken") String accessToken
            , @RequestHeader(value = "RefreshToken", required = false) String refreshToken
            , @PathVariable("roomId") String roomId){

        // 모든 코드에 이거 복붙해서 아래 부분 return만 한 줄 본인이 넣으실거 추가하시면 됩니다.
        // RefreshToken이랑 다 날라가는 거 검증 완료했습니다.
        if(refreshToken == null){

            // AccessToken부터가 글러먹었으면 바로 에러를 줍니다.
            if(!jwtTokenProvider.validateToken(accessToken))
                return tokenService.requestRefreshToken();
            else{
                return chatService.findAllTextWithRoomId(roomId); // 여기만 바꿔주세요
            }
        }
        else
            return tokenService.reissueAccessToken(accessToken, refreshToken);
    }
}
