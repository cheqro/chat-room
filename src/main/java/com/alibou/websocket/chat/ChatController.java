package com.alibou.websocket.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ChatController {
    @Autowired
    private ChatMessageRepo chatMessageRepo;
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {
        System.out.println("........................"+chatMessage+"./////from sendMessage......................");
        createChatMessage(chatMessage);


        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        System.out.println("........................"+chatMessage +"/////from addUser..........................");

        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


    public ChatMessage createChatMessage( ChatMessage cm) {
        return chatMessageRepo.save(cm);
    }



}
