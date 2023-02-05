package com.barbel.memberserver.domain.member.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequest {
  private String nickname;
  private String address;
  private String phone;
  private String introduce;
  private MultipartFile profileImage; // 프로필 이미지

  //FK
  private Long major;
  private Long interest;
  private Long user_role;
}
