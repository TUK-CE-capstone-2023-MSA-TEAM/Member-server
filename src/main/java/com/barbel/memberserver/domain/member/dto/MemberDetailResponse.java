package com.barbel.memberserver.domain.member.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MemberDetailResponse {
  private String email;
  private String nickname;
  private int age;
  private String gender;
  private String introduce;
  private String profileImageURL;
  private String role;
  private List<String> majors;
  private List<String> interests;
}
