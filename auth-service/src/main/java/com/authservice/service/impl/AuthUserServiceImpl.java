package com.authservice.service.impl;

import com.authservice.model.dto.AuthUserDTO;
import com.authservice.model.dto.NewUserDTO;
import com.authservice.model.dto.TokenDTO;
import com.authservice.model.entity.AuthUser;
import com.authservice.repository.AuthUserRepository;
import com.authservice.security.JWTProvider;
import com.authservice.security.PasswordEncoderSecurity;
import com.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JWTProvider jwtProvider;

    public AuthUser save(NewUserDTO newUserDTO) {
        Optional<AuthUser> user = authUserRepository.findByUsername(newUserDTO.getUsername());
        if(user.isPresent()){
            return null;
        }
        var password = passwordEncoder.encode(newUserDTO.getPassword());
        var authUser = new AuthUser();
        authUser.setUsername(newUserDTO.getUsername());
        authUser.setPassword(password);
        authUser.setRole(newUserDTO.getRole());
        return authUserRepository.save(authUser);
    }

    public TokenDTO login(AuthUserDTO authUserDTO) {
        var user = authUserRepository.findByUsername(authUserDTO.getUsername());
        if(!user.isPresent()){
            return null;
        }
        if(passwordEncoder.matches(authUserDTO.getPassword(), user.get().getPassword()))
            return new TokenDTO(jwtProvider.createToken(user.get()));
        return null;
    }

    public TokenDTO validate(String token) {
        if(!jwtProvider.validate(token))
            return null;
        String username = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUsername(username).isPresent()){
            return null;
        }
        return new TokenDTO(token);
    }
}
