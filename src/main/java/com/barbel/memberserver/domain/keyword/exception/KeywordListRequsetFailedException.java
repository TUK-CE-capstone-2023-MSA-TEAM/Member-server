package com.barbel.memberserver.domain.keyword.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class KeywordListRequsetFailedException extends BusinessException {
  public KeywordListRequsetFailedException() {
      super(ErrorCode.KEYWORD_LIST_REQUEST_FAIL);
    }
}
