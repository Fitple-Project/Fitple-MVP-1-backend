package com.sparta.fitpleprojectbackend.social.kakao.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import com.sparta.fitpleprojectbackend.social.kakao.dto.AddInfoRequestDto;
import com.sparta.fitpleprojectbackend.social.kakao.service.KakoaService;
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
public class KakaoController {

  private final KakoaService kakoaService;
  
  // TODO: 각 소셜로그인 테스트 완료 후 통합 구현 하기 (중복 많음)(인터페이스, 다같이 중복하기), Json 노드 리스폰 

  // 여기에서 신규 로그인 인지, 기존 로그인 인지 구분해서 보내주면 됨 그럼 jwt 통해서 유저 디테일 받으면 되고 ㅇㅈㅇㅈ
  @GetMapping("/api/user/social/kakao/callback")
  public ResponseEntity<CommonResponse<String>> kakaoLogin(@RequestParam String code) throws JsonProcessingException {

    // 리다이렉트로 전달 받은 인가 코드로 -> JwtAccessToken 발급 또는 추가 정보 입력 메세지 출력
    CommonResponse<String> response = kakoaService.kakaoLogin(code);

    // JwtAccessToken 발급 또는 추가 정보 입력 메세지 출력
    return ResponseEntity.ok(response);
  }

  @PutMapping("/api/user/kakao/addInfo")
  public ResponseEntity<CommonResponse<String>> kakaoUserAddInfo(
    @AuthenticationPrincipal UserDetailsImpl userDetails,
    @RequestBody AddInfoRequestDto requestDto ) {

    kakoaService.kakaoUserAddInfo(userDetails, requestDto);

    CommonResponse<String> response = new CommonResponse<>(
      HttpStatus.OK.value(), "추가 정보 입력 완료", "추가 정보 입력 완료");

    return ResponseEntity.ok(response);
  }
}



