package com.example.demo.dto.Chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShortChatInfo {

    String roomId;
    String lastMessage;
    String lastSender;
    LocalDateTime lastTime;

    @Override
    public String toString() {
        return "ShortChatInfo{" +
                "roomId='" + roomId + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", lastSender='" + lastSender + '\'' +
                ", lastTime=" + lastTime +
                '}';
    }
}
