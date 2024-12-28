package com.sparta.fitpleprojectbackend.chat.service;

import com.sparta.fitpleprojectbackend.chat.dto.ChatMessageDTO;
import com.sparta.fitpleprojectbackend.chat.entity.ChatMessage;
import com.sparta.fitpleprojectbackend.chat.repositroy.ChatMessageRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatMessageRepository chatMessageRepository;

  public List<ChatMessageDTO> getMessagesBetweenUsers(String userId, String receiverId) {
    List<ChatMessage> chatMessageList = chatMessageRepository.getChatMessages(userId, receiverId);

    // ChatMessage -> ChatMessageDTO 변환
    return chatMessageList.stream()
      .map(chatMessage -> new ChatMessageDTO(
        chatMessage.getSenderId(),
        chatMessage.getReceiverId(),
        chatMessage.getMessageText() // 필요시 포맷 추가
      ))
      .collect(Collectors.toList());
  }
}
