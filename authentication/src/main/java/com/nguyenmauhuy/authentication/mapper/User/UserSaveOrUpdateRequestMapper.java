package com.nguyenmauhuy.authentication.mapper.User;

import com.nguyenmauhuy.authentication.entity.User;
import com.nguyenmauhuy.authentication.mapper.Mapper;
import com.nguyenmauhuy.authentication.model.request.User.UseSaveOrUpdateRequest;



@org.mapstruct.Mapper(componentModel = "spring")
public interface UserSaveOrUpdateRequestMapper extends Mapper<User, UseSaveOrUpdateRequest> {
}
