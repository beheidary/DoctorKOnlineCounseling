package com.doctork.doctorkonlinecounseling.UseCase.Security;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
public class MasterUserInfo {
    @Value("${com.doctork.doctorkonlinecounseling.UseCase.Security.email}")
    private String email;
    @Value("${com.doctork.doctorkonlinecounseling.UseCase.Security.password}")
    private String password;
    @Value("${com.doctork.doctorkonlinecounseling.UseCase.Security.roles}")
    private String roles;
    @Value("09386887502")
    private String mobileNumber;

    @Value("3241751139")
    private String nationalCode;

    @Value("4")
    private Long adminWalletId;

    public Long getAdminWalletId() {
        return adminWalletId;
    }

    public void setAdminWalletId(Long adminWalletId) {
        this.adminWalletId = adminWalletId;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
