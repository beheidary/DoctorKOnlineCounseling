package com.doctork.doctorkonlinecounseling.api.controllers;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


public abstract class BaseController {

    public UserEntity getCurrentUser() {
        return ((UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }

}
