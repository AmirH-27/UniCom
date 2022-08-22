package com.config;

import com.service.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.dao","com.service","com.controller"})
public class AppSecurityConfig{

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public AppSecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                .authorizeRequests()
                .antMatchers("/login**").permitAll()
                .antMatchers("/register**").permitAll()
                .antMatchers("/check**").permitAll()
                .antMatchers("/signing**").permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .csrf().ignoringAntMatchers("/check","/register","/login","/signing")
                .and()
                .logout().deleteCookies("JSESSIONID")
                .and()
                .authenticationProvider(customAuthenticationProvider);
        return http.build();
    }
}