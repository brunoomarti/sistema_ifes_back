package com.sistemaifes.sistemaifes.dto.request;

import com.sistemaifes.sistemaifes.model.User;
import com.sistemaifes.sistemaifes.util.UserRole;

public record UserRequestDTO(
    String name,
    String login,
    String password,
    UserRole role
) {
    public static UserRequestDTO fromUser(User user) {
        return new UserRequestDTO(
                user.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getRole()
        );
    }
}
