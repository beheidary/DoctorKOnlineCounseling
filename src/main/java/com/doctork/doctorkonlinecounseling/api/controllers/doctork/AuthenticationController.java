package com.doctork.doctorkonlinecounseling.api.controllers.doctork;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.LoginUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.user.RegisterUserDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.user.LoginResponse;
import com.doctork.doctorkonlinecounseling.boundary.in.Security.AuthenticationService;
import com.doctork.doctorkonlinecounseling.boundary.in.Security.JwtService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import jakarta.validation.Valid;
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

    //@PreAuthorize("hasAnyAuthority('Admin')")
    @PostMapping("/signup")
    public ResponseEntity<UserEntity> register(@RequestBody @Valid RegisterUserDto registerUserDto) {
        UserEntity registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    //@PreAuthorize("isAnonymous()")
    @PostMapping("/login")
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