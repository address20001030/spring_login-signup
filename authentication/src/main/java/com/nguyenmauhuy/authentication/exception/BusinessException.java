package com.nguyenmauhuy.authentication.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private ErrorCodeResponse errorCodeResponse;

    public BusinessException(ErrorCodeResponse errorCodeResponse) {
        this.errorCodeResponse = errorCodeResponse;
    }
}
