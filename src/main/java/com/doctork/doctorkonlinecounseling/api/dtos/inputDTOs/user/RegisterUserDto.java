package com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user;

import com.doctork.doctorkonlinecounseling.domain.user.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterUserDto {
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private UserType role;
    @NotBlank
    private String mobileNumber;


    public RegisterUserDto() {
    }


    public RegisterUserDto(String email, String mobileNumber, String password, UserType role) {
        this.email = email;
        this.mobileNumber=mobileNumber;
        this.password = password;
        this.role = role;
    }

    public RegisterUserDto(String email, String password, UserType role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

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

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
