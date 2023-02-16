package com.barbel.memberserver.global.utill;

import com.barbel.memberserver.domain.member.dto.MemberRegistrationRequest;
import com.barbel.memberserver.domain.member.document.Member;
import com.barbel.memberserver.domain.member.dto.MemberUpdateRequest;

public class MemberRegistrationDTOMapper {
  //MemberRegistrationRequest -> Member
  public static Member memberRegistrationRequestToMember(MemberRegistrationRequest memberRegistrationRequest) {
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

  public static Member memberUpdateRequestToMember(MemberUpdateRequest memberUpdateRequest, Member member) {
    member.setNickname(memberUpdateRequest.getNickname());
    member.setAddress(memberUpdateRequest.getAddress());
    member.setAddressDetail(memberUpdateRequest.getAddressDetail());
    member.setIntroduce(memberUpdateRequest.getIntroduce());
    member.setMajors(memberUpdateRequest.getMajors());
    member.setInterests(memberUpdateRequest.getInterests());
    member.setRole(memberUpdateRequest.getRole());
    return member;
  }
}
