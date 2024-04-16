package com.doctork.doctorkonlinecounseling.boundary.in.Security;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.LoginUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;

public interface AuthenticationService {
    UserEntity signup(RegisterUserDto registerUserDto);

    UserEntity authenticate(LoginUserDto loginUserDto);
}
