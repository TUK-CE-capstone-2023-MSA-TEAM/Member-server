package com.barbel.memberserver.global.utill;

import com.barbel.memberserver.domain.member.dto.MemberUpdateRequest;
import com.barbel.memberserver.domain.member.entity.Member;

public class MemberUpdateDTOMapper {
  public static Member memberUpdateRequestToMember(MemberUpdateRequest memberUpdateRequest) {
    return Member.builder()
            .nickname(memberUpdateRequest.getNickname())
            .address(memberUpdateRequest.getAddress())
            .addressDetail(memberUpdateRequest.getAddressDetail())
            .introduce(memberUpdateRequest.getIntroduce())
            .majors(memberUpdateRequest.getMajors())
            .interests(memberUpdateRequest.getInterests())
            .role(memberUpdateRequest.getRole())
            .build();
  }
}
