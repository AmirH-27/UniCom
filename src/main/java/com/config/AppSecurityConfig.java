package com.config;

import com.service.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.dao","com.service","com.controller"})
public class AppSecurityConfig{

    private final CustomAuthenticationProvider customAuthenticationProvider;

    public AppSecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

//    public HttpSecurity httpSecurity(HttpSecurity http) throws Exception {
//        return http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .authenticationProvider(customAuthenticationProvider);
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login**").permitAll()
                .antMatchers("/register**").permitAll()
                .antMatchers("/check/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .csrf().ignoringAntMatchers("/check","/register","/login")
                .and()
                .authenticationProvider(customAuthenticationProvider);
        return http.build();
    }

}
