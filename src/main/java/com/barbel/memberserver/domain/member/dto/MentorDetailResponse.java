package com.barbel.memberserver.domain.member.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MentorDetailResponse{
  private String email;
  private String nickname;
  private int age;
  private String gender;
  private String introduce;
  private String profileImageURL;
  private List<String> majors;
}
