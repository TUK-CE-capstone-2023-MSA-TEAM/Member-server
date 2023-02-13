package com.barbel.memberserver.global.utill;

import com.barbel.memberserver.domain.member.dto.MemberUpdateRequest;
import com.barbel.memberserver.domain.member.entity.Member;

public class MemberUpdateDTOMapper {
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
