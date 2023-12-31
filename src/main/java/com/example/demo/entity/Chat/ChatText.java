package com.example.demo.entity.Chat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class ChatText {

    @Id @GeneratedValue
    @Column(name = "CHAT_TEXT_ID")
    private Long Id;

    // 외래키 표시, 연관관계의 주인이라는 의미, user랑 조인돼서 가져옴.
    @ManyToOne
    @JoinColumn(name = "CHAT_ROOM_ID")
    private ChatRoom chatRoom;

    @Column(columnDefinition = "TEXT")
    private String chatTextContent;

    private String chatTextWriter;

    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime chatTextDate;

    @Override
    public String toString() {
        return "ChatText{" +
                "Id=" + Id +
                ", chatTextContent='" + chatTextContent + '\'' +
                ", chatTextWriter='" + chatTextWriter + '\'' +
                ", chatTextDate=" + chatTextDate +
                '}';
    }
}
