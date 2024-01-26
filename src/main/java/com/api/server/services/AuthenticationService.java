package com.api.server.services;

import com.api.server.dtos.user.AuthenticationDTO;
import com.api.server.dtos.user.LoginResponseDTO;
import com.api.server.dtos.user.RegisterDTO;
import com.api.server.dtos.user.ResponseUserDTO;
import com.api.server.exceptions.EmailOrPasswordNotMatchesException;
import com.api.server.exceptions.PasswordsDoNotMatchException;
import com.api.server.infra.security.TokenService;
import com.api.server.models.UserModel;
import com.api.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO login(AuthenticationDTO login) throws EmailOrPasswordNotMatchesException {
        UserModel userExits = userRepository.findByEmail(login.getEmail());

        if(userExits == null || !passwordEncoder.matches(login.getPassword(), userExits.getPassword())){
            throw new EmailOrPasswordNotMatchesException("Email ou senha incorreto");
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return new LoginResponseDTO(token);
    }

    public ResponseUserDTO register(RegisterDTO register) throws PasswordsDoNotMatchException {
        if(!register.getPassword().equals(register.getConfirmationPassword())){
            throw new PasswordsDoNotMatchException("The passwords must be the same");
        }

        String passwordEncrypted = passwordEncoder.encode(register.getPassword());
        register.setPassword(passwordEncrypted);
        System.out.println(passwordEncrypted);
        UserModel newUser = new UserModel(register);

        return new ResponseUserDTO(userRepository.save(newUser));
    }
}
