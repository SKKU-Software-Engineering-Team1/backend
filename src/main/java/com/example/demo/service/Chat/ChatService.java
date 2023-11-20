package com.example.demo.service.Chat;

import com.example.demo.dto.Chat.ChatMessageDto;
import com.example.demo.dto.Chat.CreateChatRoomDto;
import com.example.demo.dto.Chat.CreateRoom;
import com.example.demo.dto.Chat.ShortChatInfo;
import com.example.demo.dto.Response;
import com.example.demo.entity.Chat.ChatRoom;
import com.example.demo.entity.Chat.ChatRoomInfo;
import com.example.demo.entity.Chat.ChatText;
import com.example.demo.entity.User.Users;
import com.example.demo.jwt.JwtTokenProvider;
import com.example.demo.repository.Chat.ChatRoomRepository;
import com.example.demo.repository.Chat.ChatRoomInfoRepository;
import com.example.demo.repository.Chat.ChatTextRespository;
import com.example.demo.repository.LoginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {
    // SQL 관련
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomInfoRepository chatRoomInfoRepository;
    private final ChatTextRespository chatTextRespository;
    private final LoginRepository loginRepository;

    // 소켓 관련
    private final ObjectMapper objectMapper;

    // 응답 관련
    private final Response response;

    // 토큰 관련
    private final JwtTokenProvider jwtTokenProvider;


    // 채팅방 개설
    public ResponseEntity<?> createChatRoomWithUserEmail(String accessToken, CreateRoom createRoom){
        try{
            // 1. Access Token 에서 User email 을 가져옵니다.
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

            String senderEmail = authentication.getName();
            String receiverEmail = createRoom.getReceiverEmail();
            String randomId = UUID.randomUUID().toString();
            String roomName = createRoom.getRoomName();

            // 랜덤 방 정보 기억
            ChatRoom chatRoom = ChatRoom.builder()
                    .roomId(randomId)
                    .roomName(roomName)
                    .build();

            chatRoomRepository.save(chatRoom);

            // 방 정보와 유저 정보 기억
            ChatRoomInfo chatRoomInfoSender = ChatRoomInfo.builder()
                    .chatRoom(chatRoom)
                    .roomId(randomId)
                    .userEmail(senderEmail)
                    .build();

            ChatRoomInfo chatRoomInfoReceiver = ChatRoomInfo.builder()
                    .chatRoom(chatRoom)
                    .roomId(randomId)
                    .userEmail(receiverEmail)
                    .build();

            chatRoomInfoRepository.save(chatRoomInfoSender);
            chatRoomInfoRepository.save(chatRoomInfoReceiver);

            // 유저 찾아오기
            Users userSender = loginRepository.findByUserEmail(senderEmail);
            Users userReceiver = loginRepository.findByUserEmail(receiverEmail);

            ChatText chatText = ChatText.builder()
                    .chatRoom(chatRoom)
                    .chatTextContent(userSender.getUserNames() + "님의 초대로 "
                            + userReceiver.getUserNames() + "님이 초대되었습니다.")
                    .chatTextWriter("System")
                    .chatTextDate(LocalDateTime.now())
                    .build();

            chatTextRespository.save(chatText);

            CreateChatRoomDto createChatRoomDto = CreateChatRoomDto.builder()
                    .chatText(chatText)
                    .senderEmail(senderEmail)
                    .receiverEmail(receiverEmail)
                    .build();

            return response.success(createChatRoomDto, "채팅방 개설에 성공하였습니다.", HttpStatus.OK);
        } catch(Exception e){
            return response.fail("데이터 베이스 오류입니다.", HttpStatus.BAD_REQUEST);
        }
    }


    // 채팅 요청
    public ResponseEntity<?> requestChat(ChatMessageDto dto){

        ChatRoom chatRoom = chatRoomRepository.findByRoomId(dto.getRoomId());

        ChatText chatText = ChatText.builder()
                .chatRoom(chatRoom)
                .chatTextDate(LocalDateTime.now())
                .chatTextWriter(dto.getChatTextWriter())
                .chatTextContent(dto.getChatTextContent())
                .build();

        try{
            chatTextRespository.save(chatText);
            return response.success("채팅이 정상적으로 저장되었습니다.");
        } catch(Exception e){
            return response.fail("데이터 베이스 오류입니다.", HttpStatus.EXPECTATION_FAILED);
        }
    }

    // 채팅방 목록 조회
    public ResponseEntity<?> findAllRoomWithUserEmail(String accessToken){
        // 1. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        String userEmail = authentication.getName();

        try{
            List<ChatRoomInfo> ChatRoomInfos = chatRoomInfoRepository.findByUserEmail(userEmail);

            if(ChatRoomInfos == null)
                return response.fail("값이 존재하지 않습니다.", HttpStatus.BAD_REQUEST);

            List<ShortChatInfo> ShortChatInfos = new ArrayList<>();

            for(ChatRoomInfo chatRoomInfo: ChatRoomInfos){
                ChatRoom chatRoom = chatRoomRepository.findChatWithRoomId(chatRoomInfo.getRoomId());
                List<ChatText> chatTexts = chatRoom.getChatTexts();
                Collections.sort(chatTexts, (text1, text2)->text1.getChatTextDate().compareTo(text2.getChatTextDate()));

                ChatText chatText = chatTexts.get(chatTexts.size()-1);
                ShortChatInfo shortChatInfo = ShortChatInfo.builder()
                        .roomId(chatRoom.getRoomId())
                        .lastMessage(chatText.getChatTextContent())
                        .lastSender(chatText.getChatTextWriter())
                        .lastTime(chatText.getChatTextDate())
                        .build();

                ShortChatInfos.add(shortChatInfo);
            }
            for(ShortChatInfo shortChatInfo :ShortChatInfos)
                System.out.println(shortChatInfo.toString());

            return response.success(ShortChatInfos, "모든 룸 정보를 가져왔습니다.", HttpStatus.OK);

        } catch(Exception e){
            return response.fail("데이터 베이스 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    // 채팅방 이전 내역 조회
    public ResponseEntity<?> findAllTextWithRoomId(String roomId){

        try{
            ChatRoom chatRoom = chatRoomRepository.findChatWithRoomId(roomId);
            List<ChatText> chatTexts = chatRoom.getChatTexts();
            return response.success(chatTexts, "모든 채팅 내용을 조회해왔습니다.", HttpStatus.OK);
        } catch(Exception e){
            return response.fail("데이터 베이스 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
