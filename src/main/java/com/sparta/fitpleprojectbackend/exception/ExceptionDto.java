package com.sparta.fitpleprojectbackend.exception;

import com.sparta.fitpleprojectbackend.enums.ErrorType;
import lombok.Getter;

@Getter
public class ExceptionDto {

  private String result;

  private ErrorType errorType;

  private String message;

  public ExceptionDto(ErrorType errorType) {
    this.result = "ERROR";
    this.errorType = errorType;
    this.message = errorType.getMessage();
  }

  public ExceptionDto(String message) {
    this.result = "ERROR";
    this.message = message;
  }
}