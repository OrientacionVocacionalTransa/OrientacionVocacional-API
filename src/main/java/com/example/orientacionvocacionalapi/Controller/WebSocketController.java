package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.ChatMessage;
import com.example.orientacionvocacionalapi.model.entity.Chat;
import com.example.orientacionvocacionalapi.repository.ChatSocketRepository;
import com.example.orientacionvocacionalapi.repository.IChatSocketRepository;
import com.example.orientacionvocacionalapi.service.impl.ChatService;
import com.example.orientacionvocacionalapi.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@CrossOrigin("*")
public class WebSocketController {

    @Autowired
    private IChatSocketRepository iChatSocketRepository;
    @Autowired
    private ChatSocketRepository chatSocketRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private EmailService emailService;

    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message){
        System.out.println(message);
        Chat chatMessageModel = new Chat();
        chatMessageModel.setUser_name(message.getUser());
        chatMessageModel.setMessage(message.getMessage());
        chatMessageModel.setRoom_id(roomId);
        chatMessageModel.setTimestamp(LocalDateTime.now());


        iChatSocketRepository.save(chatMessageModel);

        processEmail(message);
        return new ChatMessage(message.getMessage(), message.getUser());
    }


    private void processEmail(ChatMessage message) {
        Integer userId = Integer.valueOf(message.getUser());
        String receiverEmail = chatService.getReceiverEmail(userId);

        if (receiverEmail != null && chatService.shouldSendEmail(userId)) {
            emailService.sendEmailNotification(receiverEmail, message.getMessage());
        }
    }

    @GetMapping("/api/chat/{roomId}")
    public ResponseEntity<List<Chat>> getAllChatMessages(@PathVariable String roomId){
        List<Chat> result = chatSocketRepository.findByRoomId(roomId);
        return ResponseEntity.ok(result);
    }
}

