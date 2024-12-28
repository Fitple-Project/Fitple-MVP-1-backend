package com.sparta.fitpleprojectbackend.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fitpleprojectbackend.chat.dto.ChatMessageDTO;
import com.sparta.fitpleprojectbackend.chat.entity.ChatMessage;
import com.sparta.fitpleprojectbackend.chat.repositroy.ChatMessageRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

  private static List<WebSocketSession> sessions = new ArrayList<>(); // 전체 연결된 세션
  private static Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();  // 사용자 ID -> 세션 매핑

  @Autowired
  private ChatMessageRepository chatMessageRepository;

  /**
   * 메세지 처리
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    String payload = message.getPayload();
    log.info("Received message: {}", payload);

    try {
      // JSON 문자열을 ChatMessage DTO로 변환
      ObjectMapper objectMapper = new ObjectMapper();
      ChatMessageDTO chatMessageDTO = objectMapper.readValue(payload, ChatMessageDTO.class);

      // DTO에서 송신자, 수신자, 메시지 내용 추출
      String senderId = chatMessageDTO.getSenderId();
      String receiverId = chatMessageDTO.getReceiverId();
      String messageText = chatMessageDTO.getMessageText();

      // 메시지를 DB에 저장
      saveMessageToDatabase(senderId, receiverId, messageText);

      // 수신자에게만 메시지 전송
      WebSocketSession receiverSession = userSessions.get(receiverId);
      if (receiverSession != null) {
        // 응답 메시지를 ChatMessage 객체로 생성 후 JSON 변환
        ChatMessageDTO replyMessage = new ChatMessageDTO(senderId, receiverId, messageText);
        String replyPayload = objectMapper.writeValueAsString(replyMessage);
        receiverSession.sendMessage(new TextMessage(replyPayload)); // 수신자에게 메시지 전송
      } else {
        log.warn("Receiver {} not connected", receiverId);
      }
    } catch (Exception e) {
      log.error("Failed to process message: {}", e.getMessage(), e);
      // 잘못된 형식의 메시지에 대해 오류 응답 (옵션)
      session.sendMessage(new TextMessage("Error: Invalid message format"));
    }
  }

  // 메시지를 DB에 저장하는 메서드
  private void saveMessageToDatabase(String senderId, String receiverId, String messageText) {
    ChatMessage messageEntity = new ChatMessage(senderId, receiverId, messageText);
    chatMessageRepository.save(messageEntity); // DB에 메시지 저장
  }


  /**
   * 연결 및 연결 메세지
   */
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    // WebSocketSession에서 userId 추출
    String userId = (String) session.getAttributes().get("userId");

    if (userId != null) {
      log.info("User {} connected", userId);
      userSessions.put(userId, session); // 송신자 ID와 연결된 세션 저장
      sessions.add(session); // 전체 세션 리스트에 추가
    } else {
      log.warn("No userId found in WebSocket session attributes");
    }
  }

  /**
   * 쿼리 파라미터 파싱 메서드
   */
  private Map<String, String> parseQueryParams(String query) {
    Map<String, String> params = new HashMap<>();
    if (query != null) {
      String[] pairs = query.split("&");
      for (String pair : pairs) {
        String[] keyValue = pair.split("=");
        if (keyValue.length == 2) {
          params.put(keyValue[0], keyValue[1]);
        }
      }
    }
    return params;
  }

  /**
   * 연결 해제 및 세션 제거
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    String senderId = (String) session.getAttributes().get("userId");
    if (senderId != null) {
      userSessions.remove(senderId); // 세션 매핑에서 제거
    }
    sessions.remove(session); // 전체 세션 리스트에서 제거
    log.info("User {} disconnected", senderId);
  }

  // 전체 사용자에게 메시지 전송
  public void broadcastMessage(String message) throws Exception {
    for (WebSocketSession session : sessions) {
      session.sendMessage(new TextMessage(message)); // 전체 클라이언트에게 메시지 전송
    }
  }
}
