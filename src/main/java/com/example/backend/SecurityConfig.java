package com.example.backend;

import com.example.backend.user.AppUser;
import com.example.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity sss) throws Exception {
        return sss
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers("/api/autos").authenticated()
                .anyRequest().denyAll()
                .and().build();
    }

    @Bean
    public PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }


    public UserDetailsManager userDetailsService() {
        return new UserDetailsManager() {
            @Bean
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser userByName = userService.findByUserName(username);

                if (userByName == null) {
                    throw new UsernameNotFoundException("Username not found");
                }

                return User.builder()
                        .username(username)
                        .password(userByName.hashPassword())
                        .roles("BASIC")
                        .build()
                        ;
            }

            @Override
            public void createUser(UserDetails user) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public void updateUser(UserDetails user) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public void deleteUser(String username) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public void changePassword(String oldPassword, String newPassword) {
                throw new UnsupportedOperationException("You cannot use this custom UserDetailsManager for this action.");
            }

            @Override
            public boolean userExists(String username) {
                return false;
            }
        };
    }
}
