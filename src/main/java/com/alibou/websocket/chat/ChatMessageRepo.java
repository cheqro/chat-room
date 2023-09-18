package com.alibou.websocket.chat;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Integer> {
}
