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

                 // Api Course
                .requestMatchers(HttpMethod.POST, "/api/course").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/course/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/course/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/course/**").hasAnyRole("ADMIN", "COORDINATOR")

                 // Api Coordinator
                .requestMatchers(HttpMethod.POST, "/api/coordinator").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/coordinator/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/coordinator/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/coordinator/**").hasAnyRole("ADMIN","COORDINATOR")

                // Api Equipment
                .requestMatchers(HttpMethod.POST, "/api/equipment").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/equipment/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/equipment/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/equipment/**").hasAnyRole("ADMIN",  "COORDINATOR")

                // Api Discipline
                .requestMatchers(HttpMethod.POST, "/api/discipline").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/discipline/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/discipline/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/discipline/**").hasAnyRole("ADMIN", "COORDINATOR")

                // Api Local
                .requestMatchers(HttpMethod.POST, "/api/local").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/local/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/local/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/local/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")

                // Api Student
                .requestMatchers(HttpMethod.POST, "/api/student").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/student/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/student/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/student/**").hasAnyRole("ADMIN", "COORDINATOR")

                // Api Teacher
                .requestMatchers(HttpMethod.POST, "/api/teacher").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/teacher/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/teacher/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/teacher/**").hasAnyRole("ADMIN", "COORDINATOR")

                 // Api Schedule
                .requestMatchers(HttpMethod.POST, "/api/schedule").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/schedule/").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/schedule/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/schedule/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT", "COORDINATOR")

                 // Api Allocation
                .requestMatchers(HttpMethod.POST, "/api/Allocation").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")
                .requestMatchers(HttpMethod.PUT, "/api/Allocation/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")
                .requestMatchers(HttpMethod.DELETE, "/api/Allocation/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")
                .requestMatchers(HttpMethod.GET, "/api/Allocation/**").hasAnyRole("ADMIN", "COORDINATOR", "TEACHER")

                 // Api Classe
                .requestMatchers(HttpMethod.POST, "/api/Classe").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/Classe/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/Classe/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/Classe/**").hasAnyRole("ADMIN", "COORDINATOR")

                 // Api Coordination
                .requestMatchers(HttpMethod.POST, "/api/Coordination").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/Coordination/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/Coordination/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/Coordination/**").hasAnyRole("ADMIN", "COORDINATOR")

                 // Api Event
                .requestMatchers(HttpMethod.POST, "/api/Event").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/Event/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/Event/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/Event/**").hasAnyRole("ADMIN", "COORDINATOR")

                // Api History
                .requestMatchers(HttpMethod.POST, "/api/History").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/History/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/History/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/History/**").hasAnyRole("ADMIN",  "COORDINATOR")

                // Api Lesson
                .requestMatchers(HttpMethod.POST, "/api/Lesson").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/Lesson/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/Lesson/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/Lesson/**").hasAnyRole("ADMIN", "TEACHER", "COORDINATOR")

                // Api Semester
                .requestMatchers(HttpMethod.POST, "/api/Semester").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/Semester/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/Semester/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/Semester/**").hasAnyRole("ADMIN", "COORDINATOR")

                 // Api User
                .requestMatchers(HttpMethod.POST, "/api/User").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.PUT, "/api/User/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.DELETE, "/api/User/**").hasAnyRole("ADMIN", "COORDINATOR")
                .requestMatchers(HttpMethod.GET, "/api/User/**").hasAnyRole("ADMIN", "COORDINATOR")

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
