package com.sparta.fitpleprojectbackend.chat.controller;

import com.sparta.fitpleprojectbackend.chat.dto.ChatMessageDTO;
import com.sparta.fitpleprojectbackend.chat.entity.ChatMessage;
import com.sparta.fitpleprojectbackend.chat.service.ChatService;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

  private final ChatService chatService;

  @GetMapping
  public ResponseEntity<List<ChatMessageDTO>> getMessages(@RequestParam String receiverId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

    String userId = userDetails.getUser().getAccountId(); // 인증된 사용자 ID 가져오기 실제 로그인 아이디를 가져오기 때문에 문제 생길 듯

    List<ChatMessageDTO> messagesDTO = chatService.getMessagesBetweenUsers(userId, receiverId);

    return ResponseEntity.ok(messagesDTO);
  }
}
