// Em controller/AuthController.java
package br.tec.kbs.java_auth_api.controllers;

import br.tec.kbs.java_auth_api.dto.LoginReponseDTO;
import br.tec.kbs.java_auth_api.dto.UserCreateDTO;
import br.tec.kbs.java_auth_api.dto.UserLoginDTO;
import br.tec.kbs.java_auth_api.models.User;
import br.tec.kbs.java_auth_api.repositories.UserRepository;
import br.tec.kbs.java_auth_api.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserCreateDTO userDTO){

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        User newUser = new User(userDTO.name(), userDTO.username(), encryptedPassword);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid UserLoginDTO userDTO){
        var usernamePassword =new UsernamePasswordAuthenticationToken(userDTO.username(),userDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginReponseDTO(token));
    }
}