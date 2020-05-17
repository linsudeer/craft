package com.czht.smartpark.common.base.exception;

import com.czht.smartpark.common.base.enums.ErrorCode;

public class NotExistException extends BusinessException {
    public NotExistException() {
        super(ErrorCode.NOT_EXIST);
    }

    public NotExistException(String message) {
        super(ErrorCode.NOT_EXIST, message);
    }

    public NotExistException(String pattern, Object... args) {
        super(ErrorCode.NOT_EXIST, String.format(pattern, args));

    }
}
