package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.AdminLoginDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.UserOtpLoginDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.user.LoginResponse;
import com.doctork.doctorkonlinecounseling.boundary.internal.Security.AuthenticationService;
import com.doctork.doctorkonlinecounseling.boundary.internal.Security.JwtService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/auth")
@EnableMethodSecurity
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup/admin")
    @PreAuthorize("hasRole('ROLE_Admin')")
    @SecurityRequirement(name = "security_auth")
    @Operation(summary = "Admin register")
    @ApiResponse(content = { @Content(mediaType = "application/json" , schema = @Schema(implementation = RegisterUserDto.class))})
    public ResponseEntity<UserEntity> AdminRegister(@RequestBody  RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signupAdmin(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login/admin")
    @Operation(summary = "Admin Login")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) })
    public ResponseEntity<LoginResponse> authenticate(@RequestBody AdminLoginDto adminloginDto) {

        try {

            UserEntity authenticatedUser = authenticationService.authenticate(adminloginDto);


            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION
                    )
                    .body(loginResponse);

        }catch (BadCredentialsException ex ){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/login")
    @Operation(summary = "User Login")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) })
    public ResponseEntity<LoginResponse> authenticateOTP(@RequestBody UserOtpLoginDto userOtpLoginDto) {

        try {

            UserEntity authenticatedUser = authenticationService.authenticateByOTP(userOtpLoginDto);


            String jwtToken = jwtService.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(jwtToken);
            loginResponse.setExpiresIn(jwtService.getExpirationTime());

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION
                    )
                    .body(loginResponse);

        }catch (BadCredentialsException ex ){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/createOtp")
    @Operation(summary = "otp request")
    public Map<String,Object> CreateOtp(@RequestParam String phoneNumber){
        Map<String,Object> returnMap=new HashMap<>();
        try{
            authenticationService.CreateOtp(phoneNumber);
            returnMap.put("status","success");
            returnMap.put("message","Otp sent successfully");
        }catch (Exception e){
            returnMap.put("status","failed");
            returnMap.put("message",e.getMessage());
        }

        return returnMap;
    }

}