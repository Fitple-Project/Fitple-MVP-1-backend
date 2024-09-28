package com.sparta.fitpleprojectbackend.social.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.social.service.KakoaService;
import com.sparta.fitpleprojectbackend.store.dto.StoreResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class KakaoController {
  private final KakoaService kakoaService;



  @GetMapping("/api/user/kakao/callback")
  public ResponseEntity<CommonResponse<String>> kakaoLogin(@RequestParam String code) throws JsonProcessingException {

    // 리다이렉트로 전달 받은 인가 코드로 -> JwtAccessToken 발급
    String token = kakoaService.kakaoLogin(code);

    CommonResponse<String> response = new CommonResponse<>(
      HttpStatus.OK.value(), "카카오 로그인 완료", token);

    // 토큰 반환
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}


