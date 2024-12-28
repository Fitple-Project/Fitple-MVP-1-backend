package com.sparta.fitpleprojectbackend.chat.repositroy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.fitpleprojectbackend.chat.entity.ChatMessage;
import com.sparta.fitpleprojectbackend.chat.entity.QChatMessage;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class ChatMessageRepositoryQueryImpl implements ChatMessageRepositoryQuery {

  private final JPAQueryFactory queryFactory;

  public ChatMessageRepositoryQueryImpl(EntityManager em) {
    this.queryFactory = new JPAQueryFactory(em);
  }


  @Override
  public List<ChatMessage> getChatMessages(String userId, String receiverId) {
    QChatMessage chatMessage = QChatMessage.chatMessage;

    return queryFactory
      .selectFrom(chatMessage)
      .where(chatMessage.senderId.eq(userId).and(chatMessage.receiverId.eq(receiverId))
        .or(chatMessage.senderId.eq(receiverId).and(chatMessage.receiverId.eq(userId))))
      .orderBy(chatMessage.timestamp.asc())
      .fetch();
  }

}
