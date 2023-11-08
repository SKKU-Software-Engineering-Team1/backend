package com.example.demo.repository.Chat;

import com.example.demo.entity.Chat.ChatRoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomInfoRepository extends JpaRepository<ChatRoomInfo, Long> {
    List<ChatRoomInfo> findByUserEmail(String userEmail);
}
