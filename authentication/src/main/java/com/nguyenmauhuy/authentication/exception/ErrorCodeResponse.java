package com.nguyenmauhuy.authentication.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorCodeResponse {
    private String code;
    private String message;
    private HttpStatus status;

    public ErrorCodeResponse(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
