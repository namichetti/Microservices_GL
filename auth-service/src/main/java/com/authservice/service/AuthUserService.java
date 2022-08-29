package com.authservice.service;

import com.authservice.model.dto.AuthUserDTO;
import com.authservice.model.dto.NewUserDTO;
import com.authservice.model.dto.TokenDTO;
import com.authservice.model.entity.AuthUser;

import java.util.Optional;

public interface AuthUserService {

    AuthUser save(NewUserDTO authUserDTO);
    TokenDTO login(AuthUserDTO authUserDTO);
    TokenDTO validate(String token);
}
