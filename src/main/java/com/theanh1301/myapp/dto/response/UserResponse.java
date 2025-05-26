package com.theanh1301.myapp.dto.response;

import jakarta.annotation.Nonnull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
//Để service và controller trả về dto -> chú không trả về hết mội thông tin (pojo có)
public class UserResponse {

    private String id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
}
