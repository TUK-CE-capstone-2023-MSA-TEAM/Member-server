package com.barbel.memberserver.domain.member.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class MemberListRequestFailedException extends BusinessException {

  public MemberListRequestFailedException() {
    super(ErrorCode.MEMBER_LIST_REQUEST_FAIL);
  }
}
