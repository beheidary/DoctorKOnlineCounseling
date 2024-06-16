package com.doctork.doctorkonlinecounseling.UseCase.Security;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.LoginUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.mappers.UserMapper;
import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.boundary.internal.Security.AuthenticationService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.UserMySqlRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.UserType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserMySqlRepository userMySqlRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final WalletRepository walletRepository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(WalletRepository walletRepository, UserMapper userMapper, UserMySqlRepository userMySqlRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                                     MasterUserInfo masterUserInfo) {
        this.authenticationManager = authenticationManager;
        this.walletRepository = walletRepository;
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



        userEntity = userMySqlRepository.save(userEntity);
        walletRepository.createWallet(userEntity.getId());


        return userEntity;
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
