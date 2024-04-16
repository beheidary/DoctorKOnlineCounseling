package com.doctork.doctorkonlinecounseling.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = "security_auth", type = SecuritySchemeType.HTTP,bearerFormat = "JWT",
        scheme = "bearer")
//        flows = @OAuthFlows(authorizationCode = @OAuthFlow(
//                authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}"
//                , tokenUrl = "${springdoc.oAuthFlow.tokenUrl}")))
public class SwaggerConfig {


}



//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.env.Environment;
////import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
////import springfox.documentation.builders.PathSelectors;
////import springfox.documentation.builders.RequestHandlerSelectors;
////import springfox.documentation.oas.annotations.EnableOpenApi;
////import springfox.documentation.service.ApiInfo;
////
////import springfox.documentation.spi.DocumentationType;
////import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
////import springfox.documentation.spring.web.plugins.Docket;
////import springfox.documentation.swagger.web.SecurityConfiguration;
////import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
////import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//
//@Configuration
////@EnableSwagger2
//@EnableOpenApi
//@Import(SpringDataRestConfiguration.class)
//public class SwaggerConfig {
//
//    @Autowired
//    Environment env;
//
//
//    private static final String CLIENT_ID = "adminapp";
//
//    private static final String CLIENT_SECRET = "password";
//
//
////
////    @Bean
////    public Docket api() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
////                .build()
////                .apiInfo(getApiInfo())
////                ;
////    }
////
////    private ApiInfo getApiInfo() {
////        return new ApiInfo(
////                "DoctorKa Api ",
////                "web Api ",
////                "1.0",
////                "You know the terms!!!",
////                new Contact("Behnam Heidary","Behnam heidary","svdsvd0033@gmail.com"),
////                "proprietary",
////                "iranianposhes",
////                Collections.emptyList()
////        );
////    }
//
//
//
//    private SecurityScheme createAPIKeyScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//                .bearerFormat("JWT")
//                .scheme("bearer");
//    }
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI().addSecurityItem(new SecurityRequirement().
//                        addList("Bearer Authentication"))
//                .components(new Components().addSecuritySchemes
//                        ("Bearer Authentication", createAPIKeyScheme()))
//                .info(new Info().title("My REST API")
//                        .description("Some custom description of API.")
//                        .version("1.0").contact(new Contact().name("Sallo Szrajbman")
//                                .email( "www.baeldung.com").url("salloszraj@gmail.com"))
//                        .license(new License().name("License of API")
//                                .url("API license URL")));
//    }
//
////    @Bean
////    public SecurityConfiguration security() {
////        return SecurityConfigurationBuilder.builder()
////                .clientId(CLIENT_ID)
////                .clientSecret(CLIENT_SECRET)
////                .scopeSeparator(" ")
////                .useBasicAuthenticationWithAccessCodeGrant(true)
////                .build();
////    }
//
//}
