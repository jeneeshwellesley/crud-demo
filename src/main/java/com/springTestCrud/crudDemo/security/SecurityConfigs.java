package com.springTestCrud.crudDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfigs {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    /*
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}123@")
                .roles("EMPLOYEE")
                .build();

        UserDetails preethi = User.builder()
                .username("preethi")
                .password("{noop}123@")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails jeneesh = User.builder()
                .username("jeneesh")
                .password("{noop}123@")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();


        return new InMemoryUserDetailsManager(john, preethi, jeneesh);
    }

     */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasAnyRole("EMPLOYEE", "MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasAnyRole("ADMIN"));

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();

    }
}
