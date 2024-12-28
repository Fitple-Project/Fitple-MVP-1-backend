package com.sparta.fitpleprojectbackend.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

  NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
  NOT_FOUND_OWNER(HttpStatus.NOT_FOUND, "점주를 찾을 수 없습니다."),
  DUPLICATE_USER(HttpStatus.BAD_REQUEST, "중복된 사용자 정보입니다."),
  DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 사용자 아이디입니다."),
  DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
  NOT_FOUND_STORE(HttpStatus.NOT_FOUND, "해당 매장이 존재하지 않습니다."),
  INVALID_USER(HttpStatus.FORBIDDEN, "본인의 매장이 아닙니다."),
  INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력입니다."),
  INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "잘못된 입력입니다."),
  FORBIDDEN_OPERATION(HttpStatus.FORBIDDEN, "매장 등록은 점주만 가능합니다."),
  RESERVATION_CONFLICT(HttpStatus.CONFLICT, "이미 예약이 존재합니다."),
  TRAINER_NOT_FOUND(HttpStatus.NOT_FOUND, "트레이너를 찾을 수 없습니다."),
  USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
  PAYMENT_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제 처리 중 오류가 발생했습니다."),
  PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "결제 정보를 찾을 수 없습니다."),
  PAYMENT_MISMATCH(HttpStatus.BAD_REQUEST, "결제 정보가 일치하지 않습니다."),
  PAYMENT_APPROVAL_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "결제 승인이 실패했습니다."),
  NOT_FOUND_POST(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다."),
  INVALID_PAYMENT_STATUS(HttpStatus.BAD_REQUEST, "잘못된 결제 상태입니다.");

  private final HttpStatus httpStatus;
  private final String message;

  ErrorType(HttpStatus httpStatus, String message) {
    this.httpStatus = httpStatus;
    this.message = message;
  }
}