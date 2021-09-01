package com.nguyenmauhuy.authentication.model.response;

import com.nguyenmauhuy.authentication.exception.BusinessException;
import com.nguyenmauhuy.authentication.exception.ErrorCodeResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse<T> {
    private HttpStatus code;
    private T data;

    private BaseResponse(HttpStatus code, T data) {
        this.code = code;
        this.data = data;
    }


    public static <M> BaseResponse<M> ofSuccess(M data){
        return new BaseResponse<>(HttpStatus.OK, data);
    }

    public static BaseResponse<ErrorCodeResponse> ofFail(BusinessException businessException){
        return new BaseResponse<>(businessException.getErrorCodeResponse().getStatus(), businessException.getErrorCodeResponse());
    }


}
