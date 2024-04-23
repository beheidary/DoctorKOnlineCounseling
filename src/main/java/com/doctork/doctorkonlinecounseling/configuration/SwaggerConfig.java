package com.doctork.doctorkonlinecounseling.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@OpenAPIDefinition()
@SecurityScheme(name = "security_auth", type = SecuritySchemeType.HTTP,bearerFormat = "JWT",
        scheme = "bearer",in = SecuritySchemeIn.HEADER)

public class SwaggerConfig {

}

