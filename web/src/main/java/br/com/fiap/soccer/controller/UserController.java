package br.com.fiap.soccer.controller;

import br.com.fiap.soccer.dto.AuthDTO;
import br.com.fiap.soccer.dto.CreateUserDTO;
import br.com.fiap.soccer.dto.TokenDTO;
import br.com.fiap.soccer.dto.UserDTO;
import br.com.fiap.soccer.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("login")
    public TokenDTO login(@RequestBody AuthDTO authDTO){
        return userService.login(authDTO);
    }

    @PostMapping
    public UserDTO create(@RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

}
