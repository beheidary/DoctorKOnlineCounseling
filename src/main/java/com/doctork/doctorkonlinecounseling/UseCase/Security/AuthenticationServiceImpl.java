package com.doctork.doctorkonlinecounseling.UseCase.Security;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.AdminLoginDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.UserOtpLoginDto;
import com.doctork.doctorkonlinecounseling.api.mappers.UserMapper;
import com.doctork.doctorkonlinecounseling.boundary.exit.Financial.WalletRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.Miscellaneous.MiscellaneousRepository;
import com.doctork.doctorkonlinecounseling.boundary.internal.Security.AuthenticationService;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidLoginTypeException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidOtpException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.OtpNotFoundException;
import com.doctork.doctorkonlinecounseling.database.entities.user.OtpDetailsEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.OtpDetailsMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.UserMySqlRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserMySqlRepository userMySqlRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final WalletRepository walletRepository;
    private final AuthenticationManager authenticationManager;
    private final OtpDetailsMySqlRepository otpDetailsMySqlRepository;

    private final MiscellaneousRepository miscellaneousRepository;

    public AuthenticationServiceImpl(WalletRepository walletRepository,OtpDetailsMySqlRepository otpDetailsMySqlRepository, UserMapper userMapper, UserMySqlRepository userMySqlRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
                                     MasterUserInfo masterUserInfo,MiscellaneousRepository miscellaneousRepository) {
        this.authenticationManager = authenticationManager;
        this.otpDetailsMySqlRepository = otpDetailsMySqlRepository;
        this.miscellaneousRepository = miscellaneousRepository;
        this.walletRepository = walletRepository;
        this.userMapper = userMapper;
        this.userMySqlRepository = userMySqlRepository;
        this.passwordEncoder = passwordEncoder;

        if(masterUserInfo!=null && userMySqlRepository.count()==0){
            signupAdmin(new RegisterUserDto(masterUserInfo.getEmail(), masterUserInfo.getMobileNumber(), masterUserInfo.getPassword(), UserType.valueOf(masterUserInfo.getRoles()), masterUserInfo.getNationalCode()));
        }
    }

    public UserEntity signupAdmin(RegisterUserDto registerUserDto) {

        if(registerUserDto.getRole() == UserType.Physician || registerUserDto.getRole() == UserType.Patient)
            throw new InvalidDataException();

        UserEntity userEntity = userMapper.DtoToEntity(registerUserDto);
        userEntity.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        userEntity.setLoginByOPT(Boolean.FALSE);



        userEntity = userMySqlRepository.save(userEntity);
        walletRepository.createWallet(userEntity.getId());


        return userEntity;
    }

    public UserEntity signupByOTP(String mobileNumber, UserType userType) {

        if(userType == UserType.Admin || userType == UserType.Support)
            throw new InvalidDataException();

        UserEntity userEntity = new UserEntity(mobileNumber,userType, passwordEncoder.encode(UUID.randomUUID().toString()));

        userEntity = userMySqlRepository.save(userEntity);
        walletRepository.createWallet(userEntity.getId());


        return userEntity;
    }

    @Override
    public void CreateOtp(String phoneNumber) {

        Optional<OtpDetailsEntity> otpDetailsEntityOptional = miscellaneousRepository.findLatestByMobileNumber(phoneNumber);
        if (otpDetailsEntityOptional.map(detailsEntity -> detailsEntity.getCreateTime().isBefore(LocalDateTime.now().minusMinutes(5))).orElse(true)){
            OtpDetailsEntity otpDetailsEntity = new OtpDetailsEntity();
            //String otp = Integer.toString((int) (Math.random() * 90000) + 10000);
            otpDetailsEntity.setPhoneNumber(phoneNumber);
            otpDetailsEntity.setOtpCode("11111");
            otpDetailsEntity.setIsUsed(false);
            otpDetailsEntity.setCreateTime(LocalDateTime.now());
            otpDetailsMySqlRepository.save(otpDetailsEntity);
        }


    }

    public UserEntity authenticate(AdminLoginDto input) {


        UserEntity userEntity = userMySqlRepository.findByEmail(input.getEmail())
                .orElseThrow();
        if (userEntity.getLoginByOPT())
            throw new InvalidLoginTypeException(101,"You cannot Login with UserName and Password",HttpStatus.FORBIDDEN);


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );



        return userEntity;
    }


    public UserEntity authenticateByOTP(UserOtpLoginDto userOtpLoginDto) {

        OtpDetailsEntity otpDetailsEntity = miscellaneousRepository.findLatestByMobileNumber(userOtpLoginDto.getMobileNumber()).orElseThrow(OtpNotFoundException::new);
        if (!otpDetailsEntity.getIsUsed() && otpDetailsEntity.getCreateTime().isAfter(LocalDateTime.now().minusMinutes(5)) && otpDetailsEntity.getOtpCode().equals(userOtpLoginDto.getOtp())) {

            otpDetailsEntity.setIsUsed(true);
            otpDetailsMySqlRepository.save(otpDetailsEntity);
        }
        else{
            throw new InvalidOtpException();
        }
        Optional<UserEntity> userEntity = userMySqlRepository.findByMobileNumber(userOtpLoginDto.getMobileNumber());
        if (userEntity.isEmpty())
            userEntity = Optional.ofNullable(signupByOTP(userOtpLoginDto.getMobileNumber(),userOtpLoginDto.getRole()));

        if (userEntity.map(user -> !user.getLoginByOPT()).orElse(true))
            throw new InvalidLoginTypeException(101,"You cannot Login with Otp", HttpStatus.FORBIDDEN);


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userOtpLoginDto.getMobileNumber(),
                        " "
                )
        );

        return userMySqlRepository.findByMobileNumber(userOtpLoginDto.getMobileNumber())
                .orElseThrow();
    }

}
