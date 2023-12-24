package com.rabin.websocketproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/ws/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user1 = User.builder()
                .username("rabin")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("bishal")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();

        UserDetails user3 = User.builder()
                .username("sabin")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }

}
