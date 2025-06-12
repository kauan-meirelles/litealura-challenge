package br.com.literalura.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final br.com.literalura.literalura.principal.Principal principal;

    public LiteraluraApplication(br.com.literalura.literalura.principal.Principal principal) {
        this.principal = principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        principal.start();
    }
}