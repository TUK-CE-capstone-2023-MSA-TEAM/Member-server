package com.barbel.memberserver.domain.member.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class AuthenticationFailedException extends BusinessException {
  public AuthenticationFailedException() {
    super(ErrorCode.MEMBER_AUTH_FAIL);
  }
}
