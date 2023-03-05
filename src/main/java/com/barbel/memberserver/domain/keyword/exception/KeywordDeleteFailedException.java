package com.barbel.memberserver.domain.keyword.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class KeywordDeleteFailedException extends BusinessException {
    public KeywordDeleteFailedException() {
      super(ErrorCode.KEYWORD_DELETE_FAIL);
    }
}
