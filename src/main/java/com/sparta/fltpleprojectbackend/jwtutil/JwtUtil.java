package com.sparta.fltpleprojectbackend.jwtutil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secretKey;

  private final long accessTokenValidity = 1800000; // 30분
  private final long refreshTokenValidity = 3600000; // 1시간

  /**
   * JWT 액세스 토큰 생성
   * @param username 유저 이름
   * @return 생성된 JWT 액세스 토큰
   */
  public String generateAccessToken(String username) {
    Claims claims = Jwts.claims().setSubject(username);
    Date now = new Date();
    Date validity = new Date(now.getTime() + accessTokenValidity);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  /**
   * JWT 리프레시 토큰 생성
   * @param username 유저 이름
   * @return 생성된 JWT 리프레시 토큰
   */
  public String generateRefreshToken(String username) {
    Claims claims = Jwts.claims().setSubject(username);
    Date now = new Date();
    Date validity = new Date(now.getTime() + refreshTokenValidity);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  /**
   * 토큰에서 유저 이름 추출
   * @param token JWT 토큰
   * @return 유저 이름
   */
  public String getUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  /**
   * 토큰 유효성 검증
   * @param token JWT 토큰
   * @return 유효한 토큰인지 여부
   */
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 리프레시 토큰 유효기간 반환
   * @return 리프레시 토큰 유효기간 (밀리초)
   */
  public long getRefreshTokenValidity() {
    return refreshTokenValidity;
  }
}