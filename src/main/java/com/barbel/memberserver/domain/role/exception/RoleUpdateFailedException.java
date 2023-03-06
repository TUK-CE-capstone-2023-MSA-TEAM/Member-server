package com.barbel.memberserver.domain.role.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class RoleUpdateFailedException extends BusinessException {
    public RoleUpdateFailedException() {
        super(ErrorCode.ROLE_UPDATE_FAIL);
    }
}
