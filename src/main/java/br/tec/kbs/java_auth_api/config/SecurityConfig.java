package br.tec.kbs.java_auth_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // indica que o arquivo é importante
@EnableWebSecurity // inicia a segurança da aplicação e indica para o spring procurar as definições de segurança no arquivo
public class SecurityConfig {

    @Bean // indica uma ferramente que deve ser gerida pelo spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Throws é usado para delegar a resolução de problemas vindos de exceções, ao framework
        return http
                .csrf(AbstractHttpConfigurer::disable) // desliga a porteção de Cross Site Request Forgery
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // indica que o gerenciamento de sessão é stateless e nao devem ser guardadas informações de sessão
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // permite acesso ao endpoint de login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // permite acesso ao endpoint de cadastro
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() //permite acesso a todos os endppoints necessarios para usar o swagger
                        .anyRequest().authenticated() // qualquer outra requisição precisa de autenticação
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); // sera usado por baixo dos panos para realizar o login por meio da requisições
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ferramenta que faz o hash das senhas
    }
}