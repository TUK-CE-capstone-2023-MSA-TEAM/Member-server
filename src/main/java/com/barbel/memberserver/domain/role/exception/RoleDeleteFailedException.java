package com.barbel.memberserver.domain.role.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class RoleDeleteFailedException extends BusinessException {
    public RoleDeleteFailedException() {
        super(ErrorCode.ROLE_DELETE_FAIL);
    }
}
