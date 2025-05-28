package com.theanh1301.myapp.service;


import com.theanh1301.myapp.dto.request.NormalizeApiResponse;
import com.theanh1301.myapp.dto.request.UserCreationRequest;
import com.theanh1301.myapp.dto.request.UserUpdateRequest;
import com.theanh1301.myapp.dto.response.UserResponse;
import com.theanh1301.myapp.entity.User;
import com.theanh1301.myapp.exception.AppException;
import com.theanh1301.myapp.exception.ErrorCode;
import com.theanh1301.myapp.mapper.UserMapper;
import com.theanh1301.myapp.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // thay cho autowride(tạo contructor cho mọi attribute final)
@FieldDefaults(level = AccessLevel.PRIVATE ,makeFinal = true)// private final -> cho tất cả biến
public class UserService {

    //Tạo contructor 2 tham số cho UserService -> không cần @Autowired
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {


        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);//quản lý bằng chính exception của mình
        }


        //Thay vì làm thủ công thì đã có mapstruct
        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));



//        user.setUsername(request.getUsername());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setPassword(request.getPassword());
//        user.setBirthday(request.getBirthday());
//        user.setBirthday(request.getBirthday());

        userRepository.save(user);

        return userMapper.toUserResponse(user);

    }

    public List<UserResponse> getAllUser(){
        return
                userRepository.findAll().stream().map(userMapper::toUserResponse).collect(Collectors.toList()); // userMapper::toUserResponse
        //tương đướng với u -> userMapper.toUserResponse(u)
        //findAll() của thằng JPA -> Select * FROM User
    }

    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy User ")));
    }

    public UserResponse updateUserById(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy User"));


        userMapper.updateUser(user,request);
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setBirthday(request.getBirthday());
//        user.setPassword(request.getPassword());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    //này chưa bắt lỗi
    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }
}
