package com.nguyenmauhuy.authentication.model.response.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserResponse {
    private long id;
    private String userName;
    private String password;
    private String name;
    private String email;
    private transient List<String> roleName;

}
