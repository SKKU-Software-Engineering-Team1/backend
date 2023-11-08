package com.example.demo.repository.Chat;

import com.example.demo.entity.Chat.ChatText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatTextRespository extends JpaRepository<ChatText, Long> {
}
