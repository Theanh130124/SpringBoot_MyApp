package com.theanh1301.myapp.mapper;


import com.theanh1301.myapp.dto.request.UserCreationRequest;
import com.theanh1301.myapp.dto.request.UserUpdateRequest;
import com.theanh1301.myapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
//componentModel = "spring"  để đăng ký Mapper thành 1 bean -> dùng
//@Autowired được
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request); //map request từng trường vào user được gán MappingTarget
    //Có thể xem logic annotation ở bên target -> mapper

}
