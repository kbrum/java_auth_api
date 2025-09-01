package br.tec.kbs.java_auth_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // indica ao java que esta clase é uma tabela da minha aplicação
@Table(name = "users")
public class UserModel { // classe ORM para criação do usuario no banco

    @Id // indica que essa é a primary key da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que sera gerado automaticamente e delega esse trabalho para o banco postgres
    private Long id; // id do usuario

    private String name; //nome

    @Column(unique = true)
    private String username; // nome de usuario para login

    @Column(unique = true)
    private String email; // email

    private String password; // senha antes de passar  pelo hash

}
