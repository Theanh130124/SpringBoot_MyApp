package com.theanh1301.myapp.controller;


import com.theanh1301.myapp.dto.request.NormalizeApiResponse;
import com.theanh1301.myapp.dto.request.UserCreationRequest;
import com.theanh1301.myapp.dto.request.UserUpdateRequest;
import com.theanh1301.myapp.entity.User;
import com.theanh1301.myapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private UserService userService;




    //@Valid de validation theo rule ben dto
    @PostMapping
    public NormalizeApiResponse<User> createUser(@RequestBody
                               @Valid  UserCreationRequest request)
    {
        NormalizeApiResponse<User> apiResponse = new NormalizeApiResponse<>();
         apiResponse.setResult(userService.createUser(request));
         return apiResponse;
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable(value = "userId") String id){
        return userService.getUserById(id);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable(value="userId") String id, @RequestBody UserUpdateRequest request){
        return userService.updateUserById(id, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable(value = "userId")  String id){
        userService.deleteUserById(id);
        return "Xóa thành công user với id:" + id ;
    }
}
