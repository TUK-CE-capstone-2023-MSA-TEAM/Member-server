package com.barbel.memberserver.domain.keyword.exception;

import static com.barbel.memberserver.global.error.ErrorCode.KEYWORD_DUPLICATED;

import com.barbel.memberserver.global.error.exception.BusinessException;

public class KeywordDuplicatedException extends BusinessException {
  public KeywordDuplicatedException() {
    super(KEYWORD_DUPLICATED);
  }
}
