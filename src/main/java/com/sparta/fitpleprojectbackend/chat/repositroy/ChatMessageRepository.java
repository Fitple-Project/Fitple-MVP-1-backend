package com.sparta.fitpleprojectbackend.chat.repositroy;

import com.sparta.fitpleprojectbackend.chat.entity.ChatMessage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>, ChatMessageRepositoryQuery {

  // senderId와 receiverId로 데이터 조회
  List<ChatMessage> findBySenderIdAndReceiverId(String senderId, String receiverId);
}
