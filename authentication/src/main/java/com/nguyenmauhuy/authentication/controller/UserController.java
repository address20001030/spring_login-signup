package com.nguyenmauhuy.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenmauhuy.authentication.model.request.User.UseSaveOrUpdateRequest;
import com.nguyenmauhuy.authentication.model.request.User.UserAuthRequest;
import com.nguyenmauhuy.authentication.model.response.BaseResponse;
import com.nguyenmauhuy.authentication.model.response.User.UserResponse;
import com.nguyenmauhuy.authentication.security.jwt.TokenProducer;
import com.nguyenmauhuy.authentication.security.jwt.model.JwtPayload;
import com.nguyenmauhuy.authentication.service.UserService;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
public class UserController {
    private final UserService userService;
    private final TokenProducer tokenProducer;
    private final ObjectMapper objectMapper;

    @PostMapping("/user")
    public ResponseEntity<BaseResponse<UserResponse>> create(@RequestBody UseSaveOrUpdateRequest saveOrUpdateRequest){
        userService.save(saveOrUpdateRequest);
        return ResponseEntity.ok(BaseResponse.ofSuccess(null));
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable long id, @RequestBody UseSaveOrUpdateRequest saveOrUpdateRequest){
        userService.update(id,saveOrUpdateRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/user/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BaseResponse<String>> auth(@RequestBody UserAuthRequest userAuthRequest){
        UserResponse userResponse = userService.auth(userAuthRequest);
        JwtPayload jwtPayload = buildPayload(userResponse);
        String token = tokenProducer.token(jwtPayload);
        BaseResponse<String> tokenBody = BaseResponse.ofSuccess(token);

        return ResponseEntity.ok(tokenBody);
    }

    private JwtPayload buildPayload(UserResponse userResponse) {
        JwtPayload jwtPayload = new JwtPayload();
        jwtPayload.setId(userResponse.getId());
        jwtPayload.setUserName(userResponse.getUserName());
        jwtPayload.setRole(String.join(",", userResponse.getRoleName()));

        return jwtPayload;
    }
}
