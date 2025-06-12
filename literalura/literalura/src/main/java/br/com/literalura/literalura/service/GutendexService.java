package br.com.literalura.literalura.service;

import br.com.literalura.literalura.dto.DTOresultado;
import br.com.literalura.literalura.dto.LivroDTO;
import br.com.literalura.literalura.model.Autor;
import br.com.literalura.literalura.model.Livro;
import br.com.literalura.literalura.repository.AutorRepository;
import br.com.literalura.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class GutendexService {

    private final RestTemplate rest = new RestTemplate();
    private final LivroRepository livroRepo;
    private final AutorRepository autorRepo;

    public GutendexService(LivroRepository livroRepo, AutorRepository autorRepo) {
        this.livroRepo = livroRepo;
        this.autorRepo = autorRepo;
    }

    public void buscarSalvar(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            System.out.println("Título inválido.");
            return;
        }

        String tituloCodificado = URLEncoder.encode(titulo.trim(), StandardCharsets.UTF_8);
        String url = "https://gutendex.com/books/?search=" + tituloCodificado;

        DTOresultado resposta = rest.getForObject(url, DTOresultado.class);

        if (resposta == null || resposta.getResults().isEmpty()) {
            System.out.println("Nenhum resultado encontrado para \"" + titulo + "\".");
            return;
        }

        LivroDTO dto = resposta.getResults().get(0);

        Optional<Livro> existente = livroRepo.findByTituloAndAutorNome(dto.getTitle(), dto.getAutorNome());
        if (existente.isPresent()) {
            System.out.println("Este livro já foi salvo anteriormente.");
            return;
        }

        Autor autor = autorRepo.findByNome(dto.getAutorNome())
                .orElseGet(() -> {
                    Autor novoAutor = new Autor(dto.getAutorNome(), dto.getAutorNasc(), dto.getAutorFalec());
                    return autorRepo.save(novoAutor);
                });

        Livro livro = new Livro(dto.getTitle(), dto.getIdioma(), dto.getDownloads(), autor);
        livroRepo.save(livro);

        System.out.println("Livro salvo com sucesso:");
        System.out.println(livro);
    }

    public void listarLivros() {
        List<Livro> lista = livroRepo.findAll();
        if (lista.isEmpty()) {
            System.out.println("Nenhum livro salvo ainda.");
        } else {
            System.out.println("=== Livros salvos ===");
            lista.forEach(System.out::println);
        }
    }

    public void listarAutores() {
        List<Autor> autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
        } else {
            System.out.println("=== Autores cadastrados ===");
            autores.forEach(System.out::println);
        }
    }

    public void listarAutoresPorAno(int ano) {
        // Assume-se que o autor estava vivo no ano pesquisado
        List<Autor> autores = autorRepo.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo em " + ano + ".");
        } else {
            System.out.println("=== Autores vivos no ano " + ano + " ===");
            autores.forEach(System.out::println);
        }
    }

    public void listarLivrosPorIdioma(String idioma) {
        List<Livro> livros = livroRepo.findByIdiomaIgnoreCase(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma \"" + idioma + "\".");
        } else {
            System.out.println("=== Livros no idioma \"" + idioma + "\" ===");
            livros.forEach(System.out::println);
        }
    }
}
