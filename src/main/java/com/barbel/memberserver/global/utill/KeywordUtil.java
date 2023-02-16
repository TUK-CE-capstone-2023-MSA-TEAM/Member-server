package com.barbel.memberserver.global.utill;

import com.barbel.memberserver.domain.keyword.document.Keyword;
import com.barbel.memberserver.domain.keyword.dto.KeywordRegistrationRequest;

public class KeywordUtil {
  public static Keyword keywordRegistrationRequestToKeyword(KeywordRegistrationRequest keywordRegistrationRequest) {
    return Keyword.builder()
        .keyword(keywordRegistrationRequest.getKeyword())
        .build();
  }
}
