package br.tec.kbs.java_auth_api;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaAuthApiApplication {

	public static void main(String[] args) {
        Dotenv.configure().systemProperties().load();

		SpringApplication.run(JavaAuthApiApplication.class, args);
	}

}
