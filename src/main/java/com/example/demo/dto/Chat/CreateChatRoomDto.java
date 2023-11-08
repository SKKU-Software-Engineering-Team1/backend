package com.example.demo.dto.Chat;

import com.example.demo.entity.Chat.ChatText;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@Builder
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class CreateChatRoomDto {
    ChatText chatText;
    String senderEmail;
    String receiverEmail;
}
