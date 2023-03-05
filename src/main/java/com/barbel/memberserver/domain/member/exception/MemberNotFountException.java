package com.barbel.memberserver.domain.member.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class MemberNotFountException extends BusinessException {
  public MemberNotFountException() {
    super(ErrorCode.MEMBER_NOT_FOUND);
  }
}
