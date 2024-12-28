package com.sparta.fitpleprojectbackend.chat.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String senderId;

  private String receiverId;

  private String messageText;

  private LocalDateTime timestamp;

  public ChatMessage(String senderId, String receiverId, String messageText) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.messageText = messageText;
    this.timestamp = LocalDateTime.now();
  }
}
