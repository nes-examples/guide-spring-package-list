package com.herodevs.nes.trial.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .mvcMatchers("/helloPets").permitAll() // publicly available route
                        .mvcMatchers("/allPets").hasRole("USER") // only USER roles can access
                        .mvcMatchers("/pets").hasRole("USER") // only USER roles can access
                        .anyRequest().denyAll() // Prefer to deny all by default
                )
                .httpBasic(); // or .formLogin() depending on preference

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // In-memory user for demo
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}password") // {noop} means no encoding
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
