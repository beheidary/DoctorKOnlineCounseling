package com.doctork.doctorkonlinecounseling.UseCase.Security;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.LoginUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.mappers.User.UserMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Security.AuthenticationService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.UserMySqlRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserMySqlRepository userMySqlRepository;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserMapper userMapper, UserMySqlRepository userMySqlRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userMySqlRepository = userMySqlRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(RegisterUserDto input) {
        UserEntity userEntity = userMapper.DtoToEntity(input);
        userEntity.setPassword(passwordEncoder.encode(input.getPassword()));

        System.out.println(userEntity);
        System.out.println("hi");



        return userMySqlRepository.save(userEntity);
    }

    public UserEntity authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userMySqlRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }


}
