package com.barbel.memberserver.domain.role.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class RoleListRequestFailedException extends BusinessException {
    public RoleListRequestFailedException() {
        super(ErrorCode.ROLE_LIST_REQUEST_FAIL);
    }
}
