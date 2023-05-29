package com.barbel.memberserver.domain.member.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberOwnDetailResponse {
  private String email;
  private String nickname;
  private String name;
  private int age;
  private String address;
  private String addressDetail;
  private String gender;
  private String phone;
  private String introduce;
  private String profileImageURL;
  private List<String> interests;
  private List<String> majors;
  private String role;
}
