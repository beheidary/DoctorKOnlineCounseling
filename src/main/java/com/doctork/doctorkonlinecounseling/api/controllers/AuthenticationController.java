package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.LoginUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.user.LoginResponse;
import com.doctork.doctorkonlinecounseling.boundary.in.Security.AuthenticationService;
import com.doctork.doctorkonlinecounseling.boundary.in.Security.JwtService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_Admin')")
    @SecurityRequirement(name = "security_auth")
    @Operation(summary = "User register")
    @ApiResponse(content = { @Content(mediaType = "application/json" , schema = @Schema(implementation = RegisterUserDto.class))})
    public ResponseEntity<UserEntity> register(@RequestBody  RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    @Operation(summary = "User Login")
    @ApiResponse(content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) })
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {

        try {

            UserEntity authenticatedUser = authenticationService.authenticate(loginUserDto);


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
}