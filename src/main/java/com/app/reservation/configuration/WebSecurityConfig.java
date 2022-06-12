package com.app.reservation.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

import com.app.reservation.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserServiceImpl userService;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests((requests) -> requests.antMatchers("/api/v1/user/register").permitAll())
                .authorizeRequests((requests) -> requests.antMatchers(
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html").permitAll())
                .authorizeRequests((requests) -> requests.antMatchers("/**").hasRole("USER"))
                .httpBasic(withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

}
