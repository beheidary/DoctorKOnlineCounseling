package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserOtpLoginDto {

    @NotBlank
    @NotNull
    private String mobileNumber;

    @NotBlank
    @NotNull
    private String otp;

    public UserOtpLoginDto(String mobileNumber, String otp) {
        this.mobileNumber = mobileNumber;
        this.otp = otp;
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
