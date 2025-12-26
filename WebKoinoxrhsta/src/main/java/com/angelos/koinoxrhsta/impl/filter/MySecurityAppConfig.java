package com.angelos.koinoxrhsta.impl.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.angelos.koinoxrhsta.impl.enums.PermissionGroup;
import com.angelos.koinoxrhsta.impl.op.MyUserDetailOp;
import com.angelos.koinoxrhsta.web.applicationMainMenu.page.LoginLayout;
import com.vaadin.flow.spring.security.VaadinWebSecurity;

@Configuration
@EnableWebSecurity
public class MySecurityAppConfig extends VaadinWebSecurity {

    private final MyUserDetailOp myUserDetailOp;

    // Constructor injection for MyUserDetailService
    public MySecurityAppConfig(MyUserDetailOp myUserDetailOp) {
        this.myUserDetailOp = myUserDetailOp;
    }

    /**
     * Configures the security filter chain.
     * 
     * @param httpSecurity the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception in case of any configuration error
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. Configure specific public paths FIRST
        http.authorizeHttpRequests(
                authorize -> {
                    // Permit access to static resources and login, home, and error pages
                    authorize.requestMatchers("/css/**", "/js/**", "/images/**").permitAll();
                    authorize.requestMatchers("/login", "/error/**", "/logout", "/", "/home").permitAll();
                    // Restrict access to admin and user pages based on roles
                    authorize.requestMatchers("/MainMenu/**").hasRole(PermissionGroup.USER_TYPE_1.name());
                });
        http.csrf().disable();
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
