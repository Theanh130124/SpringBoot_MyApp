package com.theanh1301.myapp.dto.request;

import com.theanh1301.myapp.exception.ErrorCode;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserCreationRequest {

    @Size(min = 3, message = "USERNAME_INVALID")
    private String username;
//Dung validate
    @Size(min = 8, message ="PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
