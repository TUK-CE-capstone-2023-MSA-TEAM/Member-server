package com.barbel.memberserver.global.utill;

import com.barbel.memberserver.domain.member.dto.MemberRegistrationRequest;
import com.barbel.memberserver.domain.member.document.Member;

public class MemberRegistrationDTOMapper {
  //MemberRegistrationRequest -> Member
  public static Member toMember(MemberRegistrationRequest memberRegistrationRequest) {
    return Member.builder()
        .email(memberRegistrationRequest.getEmail())
        .password(memberRegistrationRequest.getPassword())
        .name(memberRegistrationRequest.getName())
        .age(memberRegistrationRequest.getAge())
        .address(memberRegistrationRequest.getAddress())
        .addressDetail(memberRegistrationRequest.getAddressDetail())
        .phone(memberRegistrationRequest.getPhone())
        .nickname(memberRegistrationRequest.getNickname())
        .sex(memberRegistrationRequest.getGender())
        .introduce(memberRegistrationRequest.getIntroduce())
        .interests(memberRegistrationRequest.getInterests())
        .majors(memberRegistrationRequest.getMajors())
        .role(memberRegistrationRequest.getRole())
        .build();
  }
}
