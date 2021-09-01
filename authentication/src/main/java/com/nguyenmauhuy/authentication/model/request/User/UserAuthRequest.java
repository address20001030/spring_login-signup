package com.nguyenmauhuy.authentication.model.request.User;

import lombok.Data;

@Data
public class UserAuthRequest {
    private String userName;
    private String password;
}
