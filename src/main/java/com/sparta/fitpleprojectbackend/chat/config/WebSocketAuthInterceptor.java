package com.sparta.fitpleprojectbackend.chat.config;

import com.sparta.fitpleprojectbackend.jwtutil.JwtUtil;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

  private final JwtUtil jwtUtil;

  public WebSocketAuthInterceptor(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
    WebSocketHandler wsHandler, Map<String, Object> attributes) {

    // 쿼리 파라미터에서 token 추출
      String token = UriComponentsBuilder.fromUri(request.getURI()).build().getQueryParams().getFirst("token");

    // 토큰 검증
    if (token == null || token.isEmpty() || !jwtUtil.validateToken(token)) {
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      return false;  // 유효하지 않으면 연결 차단
    }

    // 토큰에서 사용자 정보 추출 (예: username)
    String userId = jwtUtil.getUsername(token);
    if (userId == null) {
      response.setStatusCode(HttpStatus.UNAUTHORIZED);
      return false;  // 유효하지 않으면 연결 차단
    }

    // 인증된 사용자 정보 attributes에 저장 유저가 로그인 하는 아이디
    attributes.put("userId", userId);
    return true;
  }

  @Override
  public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
    WebSocketHandler wsHandler, Exception exception) {
    
  }
}
