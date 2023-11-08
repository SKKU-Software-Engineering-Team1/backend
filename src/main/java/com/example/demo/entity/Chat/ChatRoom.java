package com.example.demo.entity.Chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {

    @Id @GeneratedValue
    @Column(name = "CHAT_ROOM_ID")
    private Long id;

    String roomId;
    String roomName;

    @OneToMany(mappedBy = "chatRoom")
    @Builder.Default
    @JsonIgnore
    private List<ChatRoomInfo> chatRoomInfos = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    @Builder.Default
    @JsonIgnore
    private List<ChatText> chatTexts = new ArrayList<>();

}
