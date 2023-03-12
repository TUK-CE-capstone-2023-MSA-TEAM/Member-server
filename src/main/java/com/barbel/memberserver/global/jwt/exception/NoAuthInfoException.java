package com.barbel.memberserver.global.jwt.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class NoAuthInfoException extends BusinessException {
  public NoAuthInfoException() {
    super(ErrorCode.UN_AUTHORIZED_ACCESS);
  }
}
