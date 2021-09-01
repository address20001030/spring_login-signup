package com.nguyenmauhuy.authentication.service.impl;


import com.nguyenmauhuy.authentication.exception.BusinessCodeResponse;
import com.nguyenmauhuy.authentication.exception.BusinessException;
import com.nguyenmauhuy.authentication.mapper.User.UserResponseMapper;
import com.nguyenmauhuy.authentication.model.request.User.UserAuthRequest;
import com.nguyenmauhuy.authentication.model.response.User.UserResponse;
import com.nguyenmauhuy.authentication.repository.RoleRepository;
import com.nguyenmauhuy.authentication.repository.UserRepository;
import com.nguyenmauhuy.authentication.entity.Role;
import com.nguyenmauhuy.authentication.entity.User;
import com.nguyenmauhuy.authentication.exception.ObjectNotFoundException;
import com.nguyenmauhuy.authentication.mapper.User.UserSaveOrUpdateRequestMapper;
import com.nguyenmauhuy.authentication.model.request.User.UseSaveOrUpdateRequest;
import com.nguyenmauhuy.authentication.service.UserService;
import com.nguyenmauhuy.authentication.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSaveOrUpdateRequestMapper saveMapperRequest;
    private final RoleRepository roleRepository;
    private final UserResponseMapper userResponseMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserSaveOrUpdateRequestMapper saveMapperRequest, RoleRepository roleRepository, UserResponseMapper userResponseMapper) {
        this.userRepository = userRepository;
        this.saveMapperRequest = saveMapperRequest;
        this.roleRepository = roleRepository;
        this.userResponseMapper = userResponseMapper;
    }


    @Override
    public void save(UseSaveOrUpdateRequest saveOrUpdate) {
        // chỗ này em nghĩ encode password như này á a
        String password = PasswordUtil.getMd5(saveOrUpdate.getPassword());
        User user = saveMapperRequest.to(saveOrUpdate);
        user.setPassword(password);
        Set<Role> roles = new HashSet<>(roleRepository.findAllByIdIn(saveOrUpdate.getRolesIds()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void update(long id, UseSaveOrUpdateRequest update) {
        Optional<User> user = userRepository.findById(id);
         user.orElseThrow(()->new ObjectNotFoundException("User not found"));

         User updateUser = user.get();
         updateUser.setName(update.getName());
         updateUser.setUserName(update.getUserName());
         updateUser.setPassword(update.getPassword());
         updateUser.setEmail(update.getEmail());
//         anh xem chỗ này em với ạ, nếu em xét role thì nó lại lỗi update
//         Set<Role> updateRoles = new HashSet<>(roleRepository.findAllByIdIn(update.getRolesIds()));
//         updateUser.setRoles(updateRoles);

         userRepository.save(updateUser);

    }

    @Override
    public void delete(long id) {
        Optional<User> user = userRepository.findById(id);
         user.orElseThrow(() -> new ObjectNotFoundException("User not found"));
         userRepository.deleteById(id);
    }

    @Override
    public UserResponse auth(UserAuthRequest authRequest) {
        String password = PasswordUtil.getMd5(authRequest.getPassword());
        Optional<User> user = userRepository.findByUserNameAndPassword(authRequest.getUserName(), password);
        user.orElseThrow(()->new BusinessException(BusinessCodeResponse.USER_NOT_FOUNT));
        UserResponse userResponse = userResponseMapper.to(user.get());
        List<String> roles = user.get().getRoles().stream().map(Role::getName).collect(Collectors.toList());
        userResponse.setRoleName(roles);

        return userResponse;
    }

}
