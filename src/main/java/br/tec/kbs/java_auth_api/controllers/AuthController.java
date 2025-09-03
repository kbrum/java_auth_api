// Em controller/AuthController.java
package br.tec.kbs.java_auth_api.controllers;

import br.tec.kbs.java_auth_api.dto.UserCreateDTO;
import br.tec.kbs.java_auth_api.dto.UserLoginDTO;
import br.tec.kbs.java_auth_api.models.UserModel;
import br.tec.kbs.java_auth_api.services.UserAuthService;
import br.tec.kbs.java_auth_api.services.UserCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserCreateService userCreateService; // instancia do servicd que cria os usuarios

    @Autowired
    private UserAuthService userAuthService; // instancia do service que faz login dos usuarios

    @PostMapping("/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserCreateDTO userDTO){
        UserModel newUser = userCreateService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid UserLoginDTO userDTO){

    }
}