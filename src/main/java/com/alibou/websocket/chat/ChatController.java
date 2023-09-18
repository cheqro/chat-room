package com.alibou.websocket.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
public class ChatController {
    @Autowired
    private ChatMessageRepo chatMessageRepo;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public List<ChatMessage> sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        saveChatMessage(chatMessage);
        return List.of(chatMessage);
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public List<ChatMessage> addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        //
        List<ChatMessage> chatHistory = chatMessageRepo.findAll();
        chatHistory.add(chatMessage);
        saveChatMessage(chatMessage);

        log.info("from add user ..........> : {}", chatHistory);
        // Add username in web socket session
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatHistory;
    }

    public void saveChatMessage(ChatMessage cm) {
        chatMessageRepo.save(cm);
    }


}
