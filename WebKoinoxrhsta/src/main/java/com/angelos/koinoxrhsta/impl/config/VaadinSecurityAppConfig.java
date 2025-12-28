package com.angelos.koinoxrhsta.impl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.angelos.koinoxrhsta.impl.op.MyUserDetailOp;
import com.angelos.koinoxrhsta.web.applicationMainMenu.page.LoginLayout;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@Configuration
@EnableWebSecurity
public class VaadinSecurityAppConfig extends VaadinWebSecurity {

    private final MyUserDetailOp myUserDetailOp;

    // Constructor injection for MyUserDetailService
    public VaadinSecurityAppConfig(MyUserDetailOp myUserDetailOp) {
        this.myUserDetailOp = myUserDetailOp;
    }

    /**
     * Configures the security filter chain.
     * 
     * @param httpSecurity the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception in case of any configuration error
     */
    @Bean
    @Order(1) // High priority to catch /api requests first
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/api/**") // Apply only to /api path
            .authorizeHttpRequests(auth -> 
                auth.anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable()) // Usually disabled for stateless APIs
            .httpBasic(Customizer.withDefaults()) // Use Basic Auth or JWT here
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );
        return http.build();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 1. Configure specific public paths FIRST
        http.authorizeHttpRequests(
            authorize -> {
                // Permit access to static resources and login, home, and error pages
                authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
                authorize.requestMatchers("/login", "/error/**", "/logout", "/", "/home").permitAll();
            });

        http.userDetailsService(myUserDetailOp);
        super.configure(http);
        // use a custom login view and redirect to root on logout
        setLoginView(http, LoginLayout.class);
    }

    /**
     * Configures the UserDetailsService.
     * 
     * @return the configured UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailService() {
        return myUserDetailOp;
    }

    /**
     * Configures the AuthenticationProvider.
     * 
     * @return the configured AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailOp);
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Configures the password encoder.
     * 
     * @return the configured BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
