package com.example.threeforecasts.config;

import com.example.threeforecasts.service.CustomUserDetailsService;
import com.example.threeforecasts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider (PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider ();
        authProvider.setUserDetailsService (userDetailsService);
        authProvider.setPasswordEncoder (passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager (DaoAuthenticationProvider authProvider) {
        return new ProviderManager (authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        return http
                .csrf (csrf -> csrf.disable ())
                .authorizeHttpRequests (auth -> auth
                        .requestMatchers ("/register", "/", "/login").permitAll ()
                        .anyRequest ().authenticated ()
                )
                .formLogin (form -> form
                        .loginPage ("/login")
                        .defaultSuccessUrl ("/home", true)
                        .failureUrl ("/login?error=true")
                        .permitAll ()
                )
                .logout (logout -> logout.permitAll ())
                .build ();
    }
}