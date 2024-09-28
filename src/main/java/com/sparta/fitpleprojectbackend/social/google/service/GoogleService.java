package com.sparta.fitpleprojectbackend.social.google.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.enums.SocialProvider;
import com.sparta.fitpleprojectbackend.jwtutil.JwtUtil;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
import com.sparta.fitpleprojectbackend.social.google.dto.GoogleUserInfoDto;
import com.sparta.fitpleprojectbackend.social.kakao.dto.AddInfoRequestDto;
import com.sparta.fitpleprojectbackend.social.naver.dto.NaverUserInfoDto;
import com.sparta.fitpleprojectbackend.user.entity.User;
import com.sparta.fitpleprojectbackend.user.repository.UserRepository;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j(topic = "Google Login")
@Service
@RequiredArgsConstructor
public class GoogleService {

  private final UserRepository userRepository;
  private final RestTemplate restTemplate;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  // yml의 google.client-id 값을 주입
  @Value("${google.client-id}")
  private String googleClientId;

  // yml의 google.client-secret 값을 주입
  @Value("${google.client-secret}")
  private String googleClientSecret;

  // yml의 google.redirect-uri 값을 주입
  @Value("${google.redirect-uri}")
  private String googleRedirectUri;

  public CommonResponse<String> googleLogin(String code) throws JsonProcessingException {
    // 인가 코드를 사용하여 엑세스 토큰 요청
    String accessToken = getToken(code);

    // 엑세스 토큰을 이용하여 카카오 사용자 정보 가져오기
    GoogleUserInfoDto googleUserInfoDto = getGoogleUserInfo(accessToken);

    // 중복 사용자 체크 후 회원가입 진행
    User googleUser = registerGoogleUserIfNeeded(googleUserInfoDto);

    // JWT 토큰 생성
    String token = jwtUtil.generateAccessToken(googleUser.getNickname());

    // 추가 정보 입력 구분
    if(googleUser.isAddInfo()) {
      // response
      return new CommonResponse<>(
        HttpStatus.OK.value(), "구글 로그인 완료", token);

    } else {
      // response
      return new CommonResponse<>(
        HttpStatus.OK.value(), "입력필요", token);
    }
  }

  /**
   * 인가 코드를 사용하여 엑세스 토큰을 요청
   *
   * @param code 인가 코드
   * @return 엑세스 토큰
   * @TODO 구글 API에서 반환하는 에러 메시지나 상황에 따라 추가적인 예외 처리가 필요할 수 있습니다.
   */
  private String getToken(String code) throws JsonProcessingException {
    // 요청 URL 만들기
    URI uri = UriComponentsBuilder
      .fromUriString("https://oauth2.googleapis.com")
      .path("/token")
      .encode()
      .build()
      .toUri();

    // HTTP Header 생성
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    // HTTP Body 생성
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("grant_type", "authorization_code");
    body.add("client_id", googleClientId);
    body.add("client_secret", googleClientSecret);
    body.add("redirect_uri", googleRedirectUri);
    body.add("code", code);

    RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
      .post(uri)
      .headers(headers)
      .body(body);

    // HTTP 요청 보내기
    ResponseEntity<String> response = restTemplate.exchange(
      requestEntity,
      String.class
    );

    // HTTP 응답 (JSON) -> 액세스 토큰 파싱
    JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());
    return jsonNode.get("access_token").asText();
  }

  /**
   * 엑세스 토큰을 이용하여 구글 사용자 정보 가져오기
   *
   * @param accessToken 엑세스 토큰
   * @return 구글 사용자 정보
   */
  private GoogleUserInfoDto getGoogleUserInfo(String accessToken) throws JsonProcessingException {
    // 요청 URL 만들기
    URI uri = UriComponentsBuilder
      .fromUriString("https://www.googleapis.com")
      .path("/oauth2/v2/userinfo")
      .encode()
      .build()
      .toUri();

    // HTTP Header 생성
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + accessToken);
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity
      .post(uri)
      .headers(headers)
      .body(new LinkedMultiValueMap<>());

    // HTTP 요청 보내기
    ResponseEntity<String> response = restTemplate.exchange(
      requestEntity,
      String.class
    );

    // JSON 응답 파싱
    JsonNode jsonNode = new ObjectMapper().readTree(response.getBody());

    // 필요한 정보만 추출
    String id = jsonNode.get("id").asText();
    String email = jsonNode.get("email").asText();
    String nickname = jsonNode.get("name").asText();

    // 로그 기록
    log.info("소셜 로그인 사용자 정보: ID: {}, 닉네임: {}, 이메일: {}", id, nickname, email);

    // GoogleUserInfoDto 객체 생성 후 반환
    return new GoogleUserInfoDto(id, nickname, email);
  }

  private User registerGoogleUserIfNeeded(GoogleUserInfoDto googleUserInfoDto) {
    // DB 에 중복된 구글 Id 가 있는지 확인
    String googleId = googleUserInfoDto.getId();
    User googleUser = userRepository.findByGoogleId(googleId).orElse(null);

    if (googleUser == null) {
      // 구글 사용자 email 동일한 email 가진 회원이 있는지 확인
      String googleEmail = googleUserInfoDto.getEmail();
      User sameEmailUser = userRepository.findByEmail(googleEmail).orElse(null);
      if (sameEmailUser != null) {
        googleUser = sameEmailUser;
        // 기존 회원정보에 구글 Id 추가
        googleUser = googleUser.googleIdUpdate(googleId);
      } else {
        // 신규 회원가입
        // password: random UUID
        String password = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(password);

        // email: 구글 email
        String email = googleUserInfoDto.getEmail();

        googleUser = new User(googleUserInfoDto.getNickname(), encodedPassword, email, googleId, SocialProvider.GOOGLE);
      }
      userRepository.save(googleUser);
    }
    return googleUser;
  }

  public void googleUserAddInfo(UserDetailsImpl userDetails, AddInfoRequestDto requestDto) {
    User googleUser = userDetails.getUser();
    googleUser.addInfo(requestDto);
  }
}
