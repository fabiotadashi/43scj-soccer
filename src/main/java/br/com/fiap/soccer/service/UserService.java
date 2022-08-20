package br.com.fiap.soccer.service;

import br.com.fiap.soccer.dto.AuthDTO;
import br.com.fiap.soccer.dto.CreateUserDTO;
import br.com.fiap.soccer.dto.TokenDTO;
import br.com.fiap.soccer.dto.UserDTO;

public interface UserService {
    TokenDTO login(AuthDTO authDTO);

    UserDTO create(CreateUserDTO createUserDTO);
}
