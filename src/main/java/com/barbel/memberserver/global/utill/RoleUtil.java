package com.barbel.memberserver.global.utill;

import com.barbel.memberserver.domain.role.document.Role;
import com.barbel.memberserver.domain.role.dto.RoleRegistrationRequest;

public class RoleUtil {
  public static Role roleRegistrationRequestToRole(RoleRegistrationRequest roleRegistrationRequest) {
    return Role.builder()
        .name(roleRegistrationRequest.getName())
        .description(roleRegistrationRequest.getDescription())
        .build();
  }
}
