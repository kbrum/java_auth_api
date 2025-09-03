package br.tec.kbs.java_auth_api.repositories;

import br.tec.kbs.java_auth_api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional; // Importe a classe Optional

public interface UserRepository extends JpaRepository<UserModel, Long> { //clase que cria o usuario no banco

    // Metodo para buscar um usuario pelo username
    UserDetails findByUsername(String username);
}