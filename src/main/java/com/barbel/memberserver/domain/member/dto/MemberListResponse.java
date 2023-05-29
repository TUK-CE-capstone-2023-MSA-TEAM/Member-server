package com.barbel.memberserver.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberListResponse {
  private String email;
  private String nickname;
  private String profileImageURL;
  private String role;
}
