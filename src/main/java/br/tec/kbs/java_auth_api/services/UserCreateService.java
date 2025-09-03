package br.tec.kbs.java_auth_api.services;

import br.tec.kbs.java_auth_api.dto.UserCreateDTO;
import br.tec.kbs.java_auth_api.models.UserModel;
import br.tec.kbs.java_auth_api.repositories.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Anotation usada para indicar um serviço para o spring, que contem logica de negocio
public class UserCreateService {

    @Autowired // Anotation usada para injetar dependencias
    private UserRepository userRepository;

    public UserModel registerUser(@NotNull UserCreateDTO userDTO) { // Função que recebe o DTO e se comunica com o repository
        UserModel newUser = new UserModel(); // criando um novo objeto usuario

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        newUser.setName(userDTO.getName());
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));

         return userRepository.save(newUser); //Salvando o objeto com a classe UserRepository
    }

}