package com.theanh1301.myapp.service;


import com.theanh1301.myapp.dto.request.UserCreationRequest;
import com.theanh1301.myapp.entity.User;
import com.theanh1301.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setBirthday(request.getBirthday());
        user.setBirthday(request.getBirthday());

        userRepository.save(user);
        return user ;

    }

    public List<User> getAllUser(){
        return userRepository.findAll();
        //findAll() của thằng JPA -> Select * FROM User
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy User "));
    }
}
