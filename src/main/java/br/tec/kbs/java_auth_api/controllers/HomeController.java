package br.tec.kbs.java_auth_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/") // Controler que recebe as requisições encaminhadas para a raiz do projeto
public class HomeController {

    @GetMapping //Diz que a requisição é do tipo GET
    public String home() {

        return "Hello, welcome to my Java Auth API";
    }
}
