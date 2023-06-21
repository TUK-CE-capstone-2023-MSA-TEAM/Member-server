package com.barbel.memberserver.domain.member.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class MemberNotFoundException extends BusinessException {
  public MemberNotFoundException() {
    super(ErrorCode.MEMBER_NOT_FOUND);
  }
}
