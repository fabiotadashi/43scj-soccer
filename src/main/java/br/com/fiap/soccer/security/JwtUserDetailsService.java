package br.com.fiap.soccer.security;

import br.com.fiap.soccer.entity.UserEntity;
import br.com.fiap.soccer.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByUsername(username);

        if(userEntity == null){
            throw new UsernameNotFoundException("user.not.found");
        }

        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                new ArrayList<>()); // ROLES
    }

}
