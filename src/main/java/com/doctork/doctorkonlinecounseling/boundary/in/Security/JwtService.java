package com.doctork.doctorkonlinecounseling.boundary.in.Security;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUsername(String jwt);


    boolean isTokenValid(String jwt, UserDetails userDetails);


    String generateToken(UserDetails userDetails);
    Long getExpirationTime();
}
