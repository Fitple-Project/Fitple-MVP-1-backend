package com.sparta.fitpleprojectbackend.chat.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatMessageDTO {
  private String senderId; // 보내는 사용자 ID
  private String receiverId; // 받는 사용자 ID
  private String messageText; // 메시지 내용

  public ChatMessageDTO(String senderId, String receiverId, String messageText) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.messageText = messageText;
  }

}

