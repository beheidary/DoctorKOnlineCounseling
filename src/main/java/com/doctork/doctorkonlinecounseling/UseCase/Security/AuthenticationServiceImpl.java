package com.doctork.doctorkonlinecounseling.UseCase.Security;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.LoginUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.mappers.UserMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Security.AuthenticationService;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.UserMySqlRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.UserType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserMySqlRepository userMySqlRepository;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(PhysicianMySqlRepository physicianMySqlRepository, UserMapper userMapper, UserMySqlRepository userMySqlRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                                     MasterUserInfo masterUserInfo) {
        this.authenticationManager = authenticationManager;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.userMapper = userMapper;
        this.userMySqlRepository = userMySqlRepository;
        this.passwordEncoder = passwordEncoder;

        if(masterUserInfo!=null && userMySqlRepository.count()==0){
            signup(new RegisterUserDto(masterUserInfo.getEmail(), masterUserInfo.getMobileNumber(), masterUserInfo.getPassword(), UserType.valueOf(masterUserInfo.getRoles()), masterUserInfo.getNationalCode()));
        }
    }

    public UserEntity signup(RegisterUserDto input) {
        UserEntity userEntity = userMapper.DtoToEntity(input);
        userEntity.setPassword(passwordEncoder.encode(input.getPassword()));




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
