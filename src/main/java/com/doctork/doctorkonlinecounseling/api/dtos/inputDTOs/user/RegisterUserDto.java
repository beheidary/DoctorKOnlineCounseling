package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user;

import com.doctork.doctorkonlinecounseling.domain.user.UserType;

public class RegisterUserDto {
    private String email;

    private String password;

    private String fullName;

    private UserType userType;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
