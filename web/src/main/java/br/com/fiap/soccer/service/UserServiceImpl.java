package br.com.fiap.soccer.service;

import br.com.fiap.soccer.dto.AuthDTO;
import br.com.fiap.soccer.dto.CreateUserDTO;
import br.com.fiap.soccer.dto.TokenDTO;
import br.com.fiap.soccer.dto.UserDTO;
import br.com.fiap.soccer.entity.UserEntity;
import br.com.fiap.soccer.repository.UserRepository;
import br.com.fiap.soccer.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public TokenDTO login(AuthDTO authDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authDTO.getUsername(),
                    authDTO.getPassword()
            ));
        } catch (DisabledException | BadCredentialsException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid.credentials");
        }
        String token = jwtUtil.generateToken(authDTO.getUsername());
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        return tokenDTO;
    }

    @Override
    public UserDTO create(CreateUserDTO createUserDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));

        UserEntity savedUser = userRepository.save(userEntity);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setUsername(savedUser.getUsername());
        return userDTO;
    }

}
