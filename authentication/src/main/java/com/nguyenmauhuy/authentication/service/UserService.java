package com.nguyenmauhuy.authentication.service;


import com.nguyenmauhuy.authentication.model.request.User.UseSaveOrUpdateRequest;
import com.nguyenmauhuy.authentication.model.request.User.UserAuthRequest;
import com.nguyenmauhuy.authentication.model.response.User.UserResponse;


public interface UserService {
    void save(UseSaveOrUpdateRequest saveOrUpdate);

    void update(long id, UseSaveOrUpdateRequest update);

    void delete(long id);

    UserResponse auth(UserAuthRequest authRequest);
}
