package com.nguyenmauhuy.authentication.controller;

import com.nguyenmauhuy.authentication.exception.BusinessException;
import com.nguyenmauhuy.authentication.exception.ErrorCodeResponse;
import com.nguyenmauhuy.authentication.model.response.BaseResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class ExceptionHandlerController {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<ErrorCodeResponse>> handleUrlExisted(BusinessException businessException) {
        return new ResponseEntity<>(BaseResponse.ofFail(businessException), businessException.getErrorCodeResponse().getStatus());
    }

}
