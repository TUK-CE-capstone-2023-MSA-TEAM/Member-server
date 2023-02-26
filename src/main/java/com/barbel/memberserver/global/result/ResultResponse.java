package com.barbel.memberserver.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponse {
  private int code;
  private String message;
  private Object data;
}
