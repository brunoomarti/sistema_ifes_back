package com.sistemaifes.sistemaifes.model;

import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sistemaifes.sistemaifes.dto.request.UserRequestDTO;
import com.sistemaifes.sistemaifes.util.UserRole;

import jakarta.persistence.*; 
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_ifes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
 
    @Length(min = 3, max = 100)
    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean estahAtivo;

    private String login;

    private String password;

    private UserRole role;

    public User(UserRequestDTO data){
        this.name = data.name();
        this.login = data.login();
        this.password = data.password();
        this.role = data.role();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        switch (this.role){
            case ADMIN:
                return List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_STUDENT"),
                        new SimpleGrantedAuthority("ROLE_TEACHER"),
                        new SimpleGrantedAuthority("ROLE_COORDINATOR")
                );
            case COORDINATOR:
                return List.of(
                        new SimpleGrantedAuthority("ROLE_COORDINATOR"),
                        new SimpleGrantedAuthority("ROLE_USER"),
                        new SimpleGrantedAuthority("ROLE_STUDENT"),
                        new SimpleGrantedAuthority("ROLE_TEACHER"));

            case STUDENT:
                return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));

            case TEACHER:
                return List.of(new SimpleGrantedAuthority("ROLE_TEACHER"));

            default:
                return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
 
}
