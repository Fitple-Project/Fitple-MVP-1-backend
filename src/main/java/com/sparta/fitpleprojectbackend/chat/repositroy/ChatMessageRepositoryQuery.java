package com.sparta.fitpleprojectbackend.chat.repositroy;

import com.sparta.fitpleprojectbackend.chat.entity.ChatMessage;
import java.util.List;

public interface ChatMessageRepositoryQuery {
  List<ChatMessage> getChatMessages(String userId, String receiverId);
}
