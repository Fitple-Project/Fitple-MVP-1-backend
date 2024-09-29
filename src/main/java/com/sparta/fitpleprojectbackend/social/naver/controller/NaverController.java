package com.sparta.fitpleprojectbackend.social.naver.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import com.sparta.fitpleprojectbackend.social.kakao.dto.AddInfoRequestDto;
import com.sparta.fitpleprojectbackend.social.naver.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class NaverController {

  private final NaverService naverService;

  @GetMapping("/api/user/social/naver/callback")
  public ResponseEntity<CommonResponse<String>> naverCallback(@RequestParam String code) throws JsonProcessingException {

    // 리다이렉트로 전달 받은 인가 코드로 -> JwtAccessToken 발급 또는 추가 정보 입력 메세지 출력
    CommonResponse<String> response = naverService.naverLogin(code);

    // JwtAccessToken 발급 또는 추가 정보 입력 메세지 출력
    return ResponseEntity.ok(response);
  }

  @PutMapping("/api/user/naver/addInfo")
  public ResponseEntity<CommonResponse<String>> naverAddInfo(
    @AuthenticationPrincipal UserDetailsImpl userDetails,
    @RequestBody AddInfoRequestDto requestDto ) {

    naverService.naverUserAddInfo(userDetails, requestDto);

    CommonResponse<String> response = new CommonResponse<>(
      HttpStatus.OK.value(), "추가 정보 입력 완료", "추가 정보 입력 완료");

    return ResponseEntity.ok(response);
  }
}
