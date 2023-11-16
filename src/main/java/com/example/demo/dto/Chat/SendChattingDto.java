package com.example.demo.dto.Chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendChattingDto {

    String Sender;
    String Receiver;
    String Content;

}
