/* package com.moviebookingapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Profile;
import com.moviebookingapp.security.JwtAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
 @Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth

            // Public APIs
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/v1.0/moviebooking/all").permitAll()

            // Protected APIs
            .requestMatchers("/api/v1.0/moviebooking/**").hasAnyRole("USER", "ADMIN")
            .requestMatchers("/admin/**").hasRole("ADMIN")

            .anyRequest().authenticated()
        );

    return http.build();
}


 
}
 */

package com.moviebookingapp.config;

import com.moviebookingapp.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
        /*     .authorizeHttpRequests(auth -> auth

                // Public APIs
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/v1.0/moviebooking/all").permitAll()

                // Protected APIs
                .requestMatchers("/api/v1.0/moviebooking/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            ) */
           .authorizeHttpRequests(auth -> auth
    .requestMatchers("/api/auth/**").permitAll()
    .requestMatchers("/api/v1.0/moviebooking/all").permitAll()
    .requestMatchers("/api/v1.0/moviebooking/reset-password").permitAll()
    .requestMatchers("/api/v1.0/moviebooking/**").hasAnyRole("USER", "ADMIN")
    .requestMatchers("/admin/**").hasRole("ADMIN")
    .anyRequest().authenticated()
)

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
