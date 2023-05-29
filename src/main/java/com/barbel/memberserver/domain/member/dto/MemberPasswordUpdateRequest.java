package com.barbel.memberserver.domain.member.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPasswordUpdateRequest {
    private String memberId;
    private String password;
    private String newPassword;

}
