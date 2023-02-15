package com.barbel.memberserver.domain.keyword.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordRegistrationRequest {
  private String keyword;
}

