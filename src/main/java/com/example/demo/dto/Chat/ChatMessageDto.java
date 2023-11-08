package com.example.demo.dto.Chat;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class ChatMessageDto {

    private String roomId;
    private String chatTextWriter;
    private String chatTextContent;
}
