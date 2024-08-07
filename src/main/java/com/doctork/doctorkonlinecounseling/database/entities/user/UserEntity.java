package com.doctork.doctorkonlinecounseling.database.entities.user;

import cn.hutool.core.date.DateTime;
import com.doctork.doctorkonlinecounseling.domain.Enums.UserType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Table(name = "users")
@Entity
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private UserType role;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String nationalCode;

    @Column(name = "mobileNumber",nullable = false)
    private String mobileNumber;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false, name = "Login_ByOtp")
    private Boolean LoginByOPT;

    @Transient
    private List<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_"+getRole().toString()));

        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = new ArrayList<>(authorities);
    }

    public UserEntity(String nationalCode , UUID id,String mobileNumber, UserType userType, String email, String password, Date createdAt, Date updatedAt) {
        this.id = id;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.nationalCode = nationalCode;
        this.role = userType;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserEntity() {

    }

    public UserEntity(String mobileNumber, UserType userType,String password) {
        this.mobileNumber=mobileNumber;
        this.email = mobileNumber;
        this.password = password;
        this.role = userType;
        this.LoginByOPT = true;
        this.setCreatedAt(DateTime.now());
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
        if(LoginByOPT)
            return "$2a$10$VkYuRyVLKqPsE02sB8HCDu7uLMs39QcWyRPbTvk5EyHQ6JM1mrSPe";
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getLoginByOPT() {
        return LoginByOPT;
    }

    public void setLoginByOPT(Boolean loginByOPT) {
        LoginByOPT = loginByOPT;
    }
}
