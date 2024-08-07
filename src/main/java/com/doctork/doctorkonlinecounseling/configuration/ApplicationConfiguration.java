package com.doctork.doctorkonlinecounseling.configuration;

import com.doctork.doctorkonlinecounseling.common.Messages;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.UserMySqlRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;


@Configuration
public class ApplicationConfiguration {

    private final UserMySqlRepository userMySqlRepository;

    public ApplicationConfiguration(UserMySqlRepository userMySqlRepository) {
        this.userMySqlRepository = userMySqlRepository;
    }


    @Bean
    UserDetailsService userDetailsService() {
        int i=0;
        return username -> userMySqlRepository.findByEmail(username)
                .orElseGet(() -> userMySqlRepository.findByMobileNumber(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @Bean
    public Messages messages()
    {
        return new Messages();
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}
