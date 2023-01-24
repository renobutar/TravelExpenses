
package com.example.travelexpenses.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((auth) -> {
            try {
                auth
                        .antMatchers("/user-management/**").permitAll()
                        .antMatchers("/user/**").permitAll()
                        // .anyRequest()
                        // .authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/user-management/login")
                        .loginProcessingUrl("/check")
                        .and()
                        .logout();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordHashing() {
        return new BCryptPasswordEncoder();
    }
}