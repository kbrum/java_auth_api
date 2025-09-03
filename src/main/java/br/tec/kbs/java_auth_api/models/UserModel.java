package br.tec.kbs.java_auth_api.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Entity(name = "users") // indica ao java que esta clase é uma tabela da minha aplicação
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserModel implements UserDetails { // classe ORM para criação do usuario no banco

    @Id // indica que essa é a primary key da tabela
    @GeneratedValue(strategy = GenerationType.UUID) // indica que sera gerado automaticamente e delega esse trabalho para o banco postgres
    private String id; // id do usuario

    private String name; //nome

    @Column(unique = true)
    private String username; // nome de usuario para login

    private String password; // senha antes de passar  pelo hash


    public UserModel(String name, String username, String password) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
