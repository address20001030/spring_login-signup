package com.nguyenmauhuy.authentication.exception;

import org.springframework.http.HttpStatus;

public interface BusinessCodeResponse {
    ErrorCodeResponse USER_NOT_FOUNT = new ErrorCodeResponse("USER-01", "User not found", HttpStatus.NOT_FOUND);
    ErrorCodeResponse JWT_EXPIRED = new ErrorCodeResponse("JWT-01", "Jwt expired", HttpStatus.UNAUTHORIZED);
    ErrorCodeResponse JWT_INVALID = new ErrorCodeResponse("JWT-02", "Jwt invalid", HttpStatus.UNAUTHORIZED);
}
