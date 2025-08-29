package br.tec.kbs.java_auth_api.controler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // indica que  o controler é REST
@RequestMapping("/auth") // Delega a responsabilidade de todas as rotas com raiz /auth
public class AuthControler {

    @PostMapping("/login") // Endpoint de login
    public String auth() {
        return "Authenticating...";
    }

    @PostMapping("/register")
    public String register() {
        return "Registering...";
    }
}
