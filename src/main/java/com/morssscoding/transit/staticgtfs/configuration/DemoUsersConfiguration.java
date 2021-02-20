package com.morssscoding.transit.staticgtfs.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Profile("demo")
@Component
@AllArgsConstructor
public class DemoUsersConfiguration {

    private PasswordEncoder passwordEncoder;

    @Bean
    UserDetailsService inMemUsers(){
        UserDetails user = User.builder()
                .username("testuser")
                .password(passwordEncoder.encode("Password1"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("testadmin")
                .password(passwordEncoder.encode("PasswordAdmin1"))
                .roles("ADMIN")
                .build();

        System.out.println("\t " + user.getUsername() + ":" + user.getPassword());
        System.out.println("\t " + admin.getUsername() + ":" + admin.getPassword());

        return new InMemoryUserDetailsManager(user, admin);
    }
}
