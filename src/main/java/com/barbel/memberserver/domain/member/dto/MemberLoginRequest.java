package com.barbel.memberserver.domain.member.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginRequest {
      private String email;
      private String password;
}
