package com.example.ecommerce_application.config;

import com.example.ecommerce_application.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    // Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // UserDetailsService
    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("USER")
                .build();

        return username -> {

            if(username.equals("admin")) {
                return admin;
            }

            com.example.ecommerce_application.model.User user =
                    repo.findByEmail(username);

            if(user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        };
    }

    // Security Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/",
                            "/login",
                            "/signup",
                            "/images/**",
                            "/upload/**",
                            "/h2-console/**",
                            "/api/checkout/**"
                    ).permitAll()

                    .anyRequest().authenticated()
            )

            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/catalog", true)
                    .permitAll()
            )

            .logout(logout -> logout.permitAll());

        return http.build();
    }
}