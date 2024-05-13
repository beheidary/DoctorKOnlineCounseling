package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user;

import com.doctork.doctorkonlinecounseling.domain.Enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterUserDto {
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    @NotBlank
    private UserType role;
    @NotBlank
    @NotNull
    private String mobileNumber;

    private Long nationalCode;


    public RegisterUserDto() {
    }


    public RegisterUserDto(String email, String mobileNumber, String password, UserType role,Long nationalCode) {
        this.email = email;
        this.nationalCode = nationalCode;
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

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
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
