package com.sistemaifes.sistemaifes.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize

                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/register").permitAll()

                .requestMatchers(HttpMethod.POST, "/api/print-barcode").permitAll()

                 // Api Course
                .requestMatchers(HttpMethod.POST, "/api/course/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/course/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/course/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/course/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                 // Api Coordinator
                .requestMatchers(HttpMethod.POST, "/api/coordinator/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/coordinator/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/coordinator/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/coordinator/**").hasAnyRole("ADMIN","COORDINATOR", "TEACHER", "STUDENT")

                // Api Equipment
                .requestMatchers(HttpMethod.POST, "/api/equipment/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/equipment/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/equipment/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/equipment/**").hasAnyRole("ADMIN",  "COORDINATOR", "TEACHER", "STUDENT")

                // Api Discipline
                .requestMatchers(HttpMethod.POST, "/api/discipline/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/discipline/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/discipline/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/discipline/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                // Api Local
                .requestMatchers(HttpMethod.POST, "/api/local/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/local/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/local/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/local/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                // Api Student
                .requestMatchers(HttpMethod.POST, "/api/student/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/student/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/student/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/student/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                // Api Teacher
                .requestMatchers(HttpMethod.POST, "/api/teacher/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/teacher/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/teacher/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/teacher/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                 // Api Schedule
                .requestMatchers(HttpMethod.POST, "/api/schedule/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/schedule/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/schedule/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/schedule/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "COORDINATOR")

                 // Api Allocation
                .requestMatchers(HttpMethod.POST, "/api/allocate/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")
                .requestMatchers(HttpMethod.PUT, "/api/allocate/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")
                .requestMatchers(HttpMethod.DELETE, "/api/allocate/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")
                .requestMatchers(HttpMethod.GET, "/api/allocate/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                 // Api Classe
                .requestMatchers(HttpMethod.POST, "/api/classe/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/classe/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/classe/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/classe/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                 // Api Coordination
                .requestMatchers(HttpMethod.POST, "/api/coordination/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/coordination/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/coordination/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/coordination/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                 // Api Event
                .requestMatchers(HttpMethod.POST, "/api/event/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/event/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/event/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/event/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                // Api History
                .requestMatchers(HttpMethod.POST, "/api/history/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/history/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/history/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/history/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                // Api Lesson
                .requestMatchers(HttpMethod.POST, "/api/lesson/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/lesson/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/lesson/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/lesson/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                // Api Semester
                .requestMatchers(HttpMethod.POST, "/api/semester/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/semester/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/semester/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/semester/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                 // Api User
                .requestMatchers(HttpMethod.POST, "/api/user/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/user/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/user/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER", "STUDENT")

                .anyRequest().authenticated()
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
