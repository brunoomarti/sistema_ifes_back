package com.sistemaifes.sistemaifes.dto.response;
 
import com.sistemaifes.sistemaifes.model.User;

public record UserResponseDTO( 
    Long _id,
    String name,
    String login,
    String password,
    boolean estahAtivo
) {
  public UserResponseDTO(User user){
        this(
            user.get_id(),
            user.getName(),
            user.getLogin(),
            user.getPassword(),
            user.isEstahAtivo()
        );
  }

}
