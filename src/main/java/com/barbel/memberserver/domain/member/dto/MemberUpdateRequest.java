package com.barbel.memberserver.domain.member.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequest {
  private String memberId;
  private String nickname;
  private String address;
  private String addressDetail;
  private String introduce;
  private List<String> majors;
  private List<String> interests;
  private String role;
}
