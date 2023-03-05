package com.barbel.memberserver.domain.role.exception;

import com.barbel.memberserver.global.error.ErrorCode;
import com.barbel.memberserver.global.error.exception.BusinessException;

public class RoleDuplicatedException extends BusinessException {
    public RoleDuplicatedException() {
        super(ErrorCode.ROLE_DUPLICATED);
    }
}
