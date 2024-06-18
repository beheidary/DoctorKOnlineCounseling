package com.doctork.doctorkonlinecounseling.boundary.internal.Security;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.LoginUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;

public interface AuthenticationService {
    UserEntity signup(RegisterUserDto registerUserDto);

    UserEntity authenticate(LoginUserDto loginUserDto);

}
