package com.sparta.fitpleprojectbackend.social.naver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fitpleprojectbackend.common.CommonResponse;
import com.sparta.fitpleprojectbackend.enums.SocialProvider;
import com.sparta.fitpleprojectbackend.jwtutil.JwtUtil;
import com.sparta.fitpleprojectbackend.security.UserDetailsImpl;
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

@Slf4j(topic = "Naver Login")
@Service
@RequiredArgsConstructor
public class NaverService {

  private final UserRepository userRepository;
  private final RestTemplate restTemplate;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  // yml의 naver.client-id 값을 주입
  @Value("${naver.client-id}")
  private String naverClientId;

  // yml의 naver.client-secret 값을 주입
  @Value("${naver.client-secret}")
  private String naverClientSecret;

  // yml의 naver.redirect-uri 값을 주입
  @Value("${naver.redirect-uri}")
  private String naverRedirectUri;

  public CommonResponse<String> naverLogin(String code) throws JsonProcessingException {

    String accessToken = getToken(code);

    // 엑세스 토큰을 이용하여 카카오 사용자 정보 가져오기
    NaverUserInfoDto naverUserInfoDto = getNaverUserInfo(accessToken);

    // 중복 사용자 체크 후 회원가입 진행
    User NaverUser = registerNaverUserIfNeeded(naverUserInfoDto);

    // JWT 토큰 생성
    String token = jwtUtil.generateAccessToken(NaverUser.getNickname());

    // 추가 정보 입력 구분
    if(NaverUser.isAddInfo()) {
      // response
      return new CommonResponse<>(
        HttpStatus.OK.value(), "네이버 로그인 완료", token);

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
   * @TODO 네이버 API에서 반환하는 에러 메시지나 상황에 따라 추가적인 예외 처리가 필요할 수 있습니다.
   */
  private String getToken(String code) throws JsonProcessingException {
    // 요청 URL 만들기
    URI uri = UriComponentsBuilder
      .fromUriString("https://nid.naver.com")
      .path("/oauth2.0/token")
      .encode()
      .build()
      .toUri();

    // HTTP Header 생성
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    // HTTP Body 생성
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("grant_type", "authorization_code");
    body.add("client_id", naverClientId);
    body.add("client_secret", naverClientSecret);
    body.add("redirect_uri", naverRedirectUri);
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
   * 엑세스 토큰을 이용하여 네이버 사용자 정보 가져오기
   *
   * @param accessToken 엑세스 토큰
   * @return 네이버 사용자 정보
   */
  private NaverUserInfoDto getNaverUserInfo(String accessToken) throws JsonProcessingException {
    // 요청 URL 만들기
    URI uri = UriComponentsBuilder
      .fromUriString("https://openapi.naver.com")
      .path("/v1/nid/me")
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

    // ID를 String으로 가져오기
    String id = jsonNode.get("response").get("id").asText();

    // 닉네임 가져오기
    String nickname = jsonNode.get("response").get("nickname").asText();

    // 이메일 가져오기
    String email = jsonNode.get("response").get("email").asText();

    // 로그 기록
    log.info("네이버 사용자 정보: ID: {}, 닉네임: {}, 이메일: {}", id, nickname, email);

    // NaverUserInfoDto 객체 생성 후 반환
    return new NaverUserInfoDto(id, nickname, email);
  }

  private User registerNaverUserIfNeeded(NaverUserInfoDto naverUserInfoDto) {
    // DB 에 중복된 네이버 Id 가 있는지 확인
    String naverId = naverUserInfoDto.getId();
    User naverUser = userRepository.findByNaverId(naverId).orElse(null);

    if (naverUser == null) {
      // 네이버 사용자 email 동일한 email 가진 회원이 있는지 확인
      String naverEmail = naverUserInfoDto.getEmail();
      User sameEmailUser = userRepository.findByEmail(naverEmail).orElse(null);
      if (sameEmailUser != null) {
        naverUser = sameEmailUser;
        // 기존 회원정보에 네이버 Id 추가
        naverUser = naverUser.naverIdUpdate(naverId);
      } else {
        // 신규 회원가입
        // password: random UUID
        String password = UUID.randomUUID().toString();
        String encodedPassword = passwordEncoder.encode(password);

        // email: 네이버 email
        String email = naverUserInfoDto.getEmail();

        naverUser = new User(naverUserInfoDto.getNickname(), encodedPassword, email, naverId, SocialProvider.NAVER);
      }
      userRepository.save(naverUser);
    }
    return naverUser;
  }

  /**
   * 추가정보 입력
   *
   * @param userDetails 로그인 한 네이버 유저
   * @param requestDto 유저의 추가 정보
   */
  public void naverUserAddInfo(UserDetailsImpl userDetails, AddInfoRequestDto requestDto) {
    User naverUser = userDetails.getUser();
    naverUser.addInfo(requestDto);
  }
}
