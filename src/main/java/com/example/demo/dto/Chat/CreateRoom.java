package com.example.demo.dto.Chat;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRoom {
    String receiverEmail;
    String roomName;
}
