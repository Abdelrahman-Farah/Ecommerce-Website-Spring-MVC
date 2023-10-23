package com.ecommerce.security;

import com.ecommerce.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class AuthConfiguration {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
////        auth.setUserDetailsService(userService); //set the custom user details service
//        auth.setPasswordEncoder(bCryptPasswordEncoder()); //set the password encoder - bcrypt
//        return auth;
//    }


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select email, password, enabled from custom_user where email=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select u.email, r.name\n" +
                        "from custom_user u\n" +
                        "left outer join users_roles as ur on u.id = ur.user_id\n" +
                        "left outer join role as r on r.id = ur.role_id\n" +
                        "where email=?");

        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/processLoginForm")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll()
                );
        return http.build();
    }
}
