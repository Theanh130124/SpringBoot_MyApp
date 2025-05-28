package com.theanh1301.myapp.mapper;


import com.theanh1301.myapp.dto.request.UserCreationRequest;
import com.theanh1301.myapp.dto.request.UserUpdateRequest;
import com.theanh1301.myapp.dto.response.UserResponse;
import com.theanh1301.myapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
//componentModel = "spring"  để đăng ký Mapper thành 1 bean -> dùng
//@Autowired được
public interface UserMapper {
    User toUser(UserCreationRequest request);

//    @Mapping(source = "firstName" , target = "lastName")// source la nguon lay di map , target la gia tri map ra.
    //-> firstName = lastName
//@Mapping( target = "lastName" , ignore = true ) -> ignore field lastName di -> bth lastName map vao lastName
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request); //map request từng trường trong request vào user(entity) được gán MappingTarget
    //Có thể xem logic annotation ở bên target -> mapper

}
