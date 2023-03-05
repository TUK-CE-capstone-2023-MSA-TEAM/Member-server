package com.barbel.memberserver.domain.member.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class EmailDuplicatedException extends BusinessException {
  public EmailDuplicatedException() {
    super(ErrorCode.MEMBER_ACCOUNT_DUPLICATED);
  }
}
