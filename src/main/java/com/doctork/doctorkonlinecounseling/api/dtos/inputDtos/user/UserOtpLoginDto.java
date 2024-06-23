package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user;

import com.doctork.doctorkonlinecounseling.domain.Enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserOtpLoginDto {

    @NotBlank
    @NotNull
    private String mobileNumber;

    @NotBlank
    @NotNull
    private String otp;

    @NotBlank
    @NotNull
    private UserType role;

    public UserOtpLoginDto(String mobileNumber, String otp, UserType role) {
        this.mobileNumber = mobileNumber;
        this.otp = otp;
        this.role = role;
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

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
