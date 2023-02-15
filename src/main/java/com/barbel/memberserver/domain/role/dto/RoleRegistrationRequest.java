package com.barbel.memberserver.domain.role.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleRegistrationRequest {
    private String name;
    private String description;
}
