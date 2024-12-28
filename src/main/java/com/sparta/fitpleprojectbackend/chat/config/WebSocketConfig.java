package com.sparta.fitpleprojectbackend.chat.config;

import com.sparta.fitpleprojectbackend.chat.controller.ChatHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  private final ChatHandler chatHandler;
  private final WebSocketAuthInterceptor webSocketAuthInterceptor;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(chatHandler, "ws/chat")
      .addInterceptors(webSocketAuthInterceptor)  // WebSocket 인증 인터셉터 추가
      .setAllowedOrigins("*");
  }
}