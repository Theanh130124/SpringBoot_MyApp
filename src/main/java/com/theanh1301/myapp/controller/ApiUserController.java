package com.theanh1301.myapp.controller;


import com.theanh1301.myapp.dto.request.UserCreationRequest;
import com.theanh1301.myapp.entity.User;
import com.theanh1301.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest request)
    {
        return userService.createUser(request);
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable(value = "userId") String id){
        return userService.getUserById(id);
    }
}
