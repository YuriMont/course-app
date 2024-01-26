package com.api.server.controllers;

import com.api.server.dtos.user.AuthenticationDTO;
import com.api.server.dtos.user.RegisterDTO;
import com.api.server.exceptions.EmailOrPasswordNotMatchesException;
import com.api.server.exceptions.PasswordsDoNotMatchException;
import com.api.server.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO login) throws EmailOrPasswordNotMatchesException {
        return ResponseEntity.status(200).body(authenticationService.login(login));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO registerDTO) throws PasswordsDoNotMatchException {
         return ResponseEntity.status(200).body(authenticationService.register(registerDTO));
    }
}
