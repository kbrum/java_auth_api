package br.tec.kbs.java_auth_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity // indica ao java que esta clase é uma tabela da minha aplicação
@Table(name = "users")
public class UserModel {

    @Id // indica que essa é a primary key da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que sera gerado automaticamente e delega esse trabalho para o banco postgres
    private Long id; // atributo privado

    private String name; //

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

}
