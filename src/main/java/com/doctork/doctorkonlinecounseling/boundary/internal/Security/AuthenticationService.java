package com.doctork.doctorkonlinecounseling.boundary.internal.Security;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.AdminLoginDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.UserOtpLoginDto;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;

public interface AuthenticationService {
    UserEntity signupAdmin(RegisterUserDto registerUserDto);

    UserEntity authenticate(AdminLoginDto adminloginDto);

    UserEntity authenticateByOTP(UserOtpLoginDto userOtpLoginDto);
    UserEntity signupByOTP(String mobileNumber);

    void  CreateOtp(String mobileNumber);
}
