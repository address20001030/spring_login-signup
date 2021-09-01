package com.nguyenmauhuy.authentication.mapper.User;

import com.nguyenmauhuy.authentication.entity.User;
import com.nguyenmauhuy.authentication.mapper.Mapper;
import com.nguyenmauhuy.authentication.model.response.User.UserResponse;
@org.mapstruct.Mapper(componentModel = "spring")
public interface UserResponseMapper extends Mapper<UserResponse, User> {
}
