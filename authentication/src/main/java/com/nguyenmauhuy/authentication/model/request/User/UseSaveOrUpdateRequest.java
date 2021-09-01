package com.nguyenmauhuy.authentication.model.request.User;

import lombok.Data;

import java.util.List;

@Data
public class UseSaveOrUpdateRequest {
    private String userName;
    private String password;
    private String name;
    private String email;

    private List<Long> rolesIds;
}
