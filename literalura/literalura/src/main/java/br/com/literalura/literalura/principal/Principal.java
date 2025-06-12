package br.com.literalura.literalura.principal;

import br.com.literalura.literalura.service.GutendexService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private final GutendexService service;
    private final Scanner scanner = new Scanner(System.in);

    public Principal(GutendexService service) {
        this.service = service;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== LiterAlura ===");
            System.out.println("1. Buscar livro (via API)");
            System.out.println("2. Listar livros salvos");
            System.out.println("3. Listar autores");
            System.out.println("4. Listar autores vivos em determinado ano");
            System.out.println("5. Listar livros por idioma");
            System.out.println("0. Sair");
            System.out.print("> ");
            int op = Integer.parseInt(scanner.nextLine());

            switch (op) {
                case 1:
                    System.out.print("Título: ");
                    service.buscarSalvar(scanner.nextLine());
                    break;
                case 2:
                    service.listarLivros();
                    break;
                case 3:
                    service.listarAutores();
                    break;
                case 4:
                    System.out.print("Ano: ");
                    int ano = Integer.parseInt(scanner.nextLine());
                    service.listarAutoresPorAno(ano);
                    break;
                case 5:
                    System.out.print("Idioma (PT, EN, ES, FR): ");
                    String idioma = scanner.nextLine().toUpperCase();
                    service.listarLivrosPorIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Tchau!");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
