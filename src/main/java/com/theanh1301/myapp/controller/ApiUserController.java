package com.theanh1301.myapp.controller;


import com.theanh1301.myapp.dto.request.NormalizeApiResponse;
import com.theanh1301.myapp.dto.request.UserCreationRequest;
import com.theanh1301.myapp.dto.request.UserUpdateRequest;
import com.theanh1301.myapp.dto.response.UserResponse;
import com.theanh1301.myapp.entity.User;
import com.theanh1301.myapp.mapper.UserMapper;
import com.theanh1301.myapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
public class ApiUserController {


    UserService userService;


    UserMapper userMapper;




    //@Valid de validation theo rule ben dto.request
    @PostMapping
    public NormalizeApiResponse<UserResponse> createUser(@RequestBody
                               @Valid  UserCreationRequest request)
    {
        NormalizeApiResponse<UserResponse> apiResponse = new NormalizeApiResponse<>();
         apiResponse.setResult(userService.createUser(request));
         return apiResponse;
    }

    @GetMapping
    public List<UserResponse> getUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable(value = "userId") String id){
        return userService.getUserById(id);
    }

    @PatchMapping("/{userId}")
    public UserResponse updateUser(@PathVariable(value="userId") String id, @RequestBody UserUpdateRequest request){
        return userService.updateUserById(id, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(value = "userId")  String id){
        userService.deleteUserById(id);
        return "Xóa thành công user với id:" + id ;
    }
}
