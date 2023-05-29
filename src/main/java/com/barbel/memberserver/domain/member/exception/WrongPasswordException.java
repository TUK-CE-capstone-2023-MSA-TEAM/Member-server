package com.barbel.memberserver.domain.member.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class WrongPasswordException extends BusinessException {
  public WrongPasswordException() {
    super(ErrorCode.MEMBER_PASSWORD_WRONG);
  }
}
