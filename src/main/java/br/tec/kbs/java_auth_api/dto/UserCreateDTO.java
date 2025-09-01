package br.tec.kbs.java_auth_api.dto;

import lombok.Data;

@Data
public class UserCreateDTO { // essa classe ser usada como intermediaria para conexão entre a classe ORM e a criação do objeto usuario
    private String name;
    private String username;
    private String email;
    private String password;
}
