package com.example.demo.repository.Chat;

import com.example.demo.entity.Chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    @Query("SELECT c FROM ChatRoom c JOIN FETCH c.chatTexts WHERE c.roomId = :roomId")
    ChatRoom findChatWithRoomId(@Param("roomId") String roomId);

    ChatRoom findByRoomId(String roomId);
}
