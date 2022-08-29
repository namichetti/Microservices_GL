package com.authservice.controller;

import com.authservice.model.dto.AuthUserDTO;
import com.authservice.model.dto.NewUserDTO;
import com.authservice.model.dto.TokenDTO;
import com.authservice.model.entity.AuthUser;
import com.authservice.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody AuthUserDTO authUserDTO){
        var tokenDto = authUserService.login(authUserDTO);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDTO> validate(@RequestParam String token){
        var tokenDto = authUserService.validate(token);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserDTO newUserDTO){
        var authUser = authUserService.save(newUserDTO);
        if(authUser == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(authUser);
    }
}
