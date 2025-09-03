// Em controller/AuthController.java
package br.tec.kbs.java_auth_api.controllers;

import br.tec.kbs.java_auth_api.dto.UserCreateDTO;
import br.tec.kbs.java_auth_api.dto.UserLoginDTO;
import br.tec.kbs.java_auth_api.models.UserModel;
import br.tec.kbs.java_auth_api.repositories.UserRepository;
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

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody @Valid UserCreateDTO userDTO){

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        UserModel newUser = new UserModel(userDTO.name(), userDTO.username(), encryptedPassword);

        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid UserLoginDTO userDTO){
        var usernamePassword =new UsernamePasswordAuthenticationToken(userDTO.username(),userDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }
}