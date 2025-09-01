package br.tec.kbs.java_auth_api.service;

import br.tec.kbs.java_auth_api.dto.UserCreateDTO;
import br.tec.kbs.java_auth_api.model.UserModel;
import br.tec.kbs.java_auth_api.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service // Anotation usada para indicar um serviço para o spring, que contem logica de negocio
public class UserService {

    @Autowired // Anotation usada para injetar dependencias
    private UserRepository userRepository;

    public UserModel registerUser(@NotNull UserCreateDTO userDTO) {
        UserModel newUser = new UserModel(); // criando um novo objeto usuario

        newUser.setName(userDTO.getName());
        newUser.setUsername(userDTO.getUsername());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

         return userRepository.save(newUser); //Salvando o objeto com a classe UserRepository
    }

    public String getUserByUsername(String username) {

        boolean user = Boolean.parseBoolean(String.valueOf(userRepository.findByUsername(username)));

        if (user) {
            return username;
        }

        else {
            return null;
        }
    }
}