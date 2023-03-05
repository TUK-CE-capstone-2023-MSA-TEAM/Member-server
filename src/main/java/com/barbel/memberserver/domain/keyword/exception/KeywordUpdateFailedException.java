package com.barbel.memberserver.domain.keyword.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class KeywordUpdateFailedException extends BusinessException {
  public KeywordUpdateFailedException() {
    super(ErrorCode.KEYWORD_UPDATE_FAIL);
  }
}
