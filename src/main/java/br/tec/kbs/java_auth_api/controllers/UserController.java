package br.tec.kbs.java_auth_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/authenticated") // rota protegida que só estara visivel por meio de autenticação
    public String authenticated() {
        return "Authentication Successful";
    }
}
