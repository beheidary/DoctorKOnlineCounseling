package com.doctork.doctorkonlinecounseling.UseCase.Security;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
//@PropertySource("classpath:application.properties")
//@ConfigurationProperties()
public class MasterUserInfo {
    @Value("${com.doctork.doctorkonlinecounseling.UseCase.Security.email}")
    private String email;
    @Value("${com.doctork.doctorkonlinecounseling.UseCase.Security.password}")
    private String password;
    @Value("${com.doctork.doctorkonlinecounseling.UseCase.Security.roles}")
    private String roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
