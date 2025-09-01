// Em controller/AuthController.java
package br.tec.kbs.java_auth_api.controller;

import br.tec.kbs.java_auth_api.dto.UserCreateDTO;
import br.tec.kbs.java_auth_api.model.UserModel;
import br.tec.kbs.java_auth_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService; // instancia do User service

    // O endpoint agora espera um JSON no formato do nosso DTO
    @PostMapping("/register")
    public ResponseEntity<UserModel> registrarUsuario(@RequestBody UserCreateDTO userDTO){
        UserModel newUser = userService.registerUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

    }

}