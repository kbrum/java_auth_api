package br.tec.kbs.java_auth_api.configs;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // indica que o arquivo é importante
@EnableWebSecurity // inicia a segurança da aplicação e indica para o spring procurar as definições de segurança no arquivo
public class SecurityConfig {

    @Autowired
    SecurityFilter securityFilter;

    @Bean // indica uma ferramente que deve ser gerida pelo spring
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Throws é usado para delegar a resolução de problemas vindos de exceções, ao framework
        return http
                .csrf(AbstractHttpConfigurer::disable) // desliga a porteção de Cross Site Request Forgery
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // indica que o gerenciamento de sessão é stateless e nao devem ser guardadas informações de sessãO
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**", "/","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}