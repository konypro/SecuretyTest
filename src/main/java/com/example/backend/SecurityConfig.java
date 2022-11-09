package com.example.backend;

import com.example.backend.user.AppUser;
import com.example.backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;



    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity currywurst) throws Exception {
        return currywurst
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/users").permitAll()
                .antMatchers("/api/autos","/api/users/login", "/api/users/logout").authenticated()
                .anyRequest().denyAll()
                .and().build();
    }

    @Bean
    public PasswordEncoder password() {
        return  passwordEncoder;
    }

    @Bean
    public UserDetailsManager userDetailsService() {
        return new UserDetailsManager() {
            String unsupportedOperationMsg="You cannot use this custom UserDetailsManager for this action.";

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
