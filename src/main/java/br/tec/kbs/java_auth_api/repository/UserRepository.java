package br.tec.kbs.java_auth_api.repository;

import br.tec.kbs.java_auth_api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Importe a classe Optional

public interface UserRepository extends JpaRepository<UserModel, Long> { //clase que cria o usuario no banco

    // Metodo para buscar um usuario pelo email
    Optional<UserModel> findByEmail(String email);

    // Metodo para buscar um usuario pelo username
    Optional<UserModel> findByUsername(String username);
}