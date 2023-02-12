package com.barbel.memberserver.domain.member.dto;

import com.barbel.memberserver.domain.member.entity.Gender;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRegistrationRequest {

  @Email(
      message = "유효하지 않은 이메일 형식입니다.",
      regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
  private String email;
  @Pattern(
      message = "최소 한개 이상의 대소문자와 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호를 입력해야 합니다.",
      regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!~$%^&-+=()])(?=\\S+$).{8,16}$")
  private String password;
  private String name;
  private String nickname;
  private int age;
  private String address;
  private Gender gender;
  private String phone;
  private String introduce;

  //FK
  private Long major;
  private Long interest;
  private Long user_role;
}
