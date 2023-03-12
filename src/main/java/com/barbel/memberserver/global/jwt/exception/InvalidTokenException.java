package com.barbel.memberserver.global.jwt.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
  public InvalidTokenException() {
    super(ErrorCode.INVALID_TOKEN);
  }
}
