
package br.com.literalura.literalura.repository;

    import br.com.literalura.literalura.model.Autor;
    import br.com.literalura.literalura.model.Livro;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;
    import java.util.Optional;

    public interface LivroRepository extends JpaRepository<Livro, Long> { 
        Optional<Livro> findByTituloAndAutorNome(String titulo, String autorNome);
        List<Livro> findByIdiomaIgnoreCase (String idioma);
        List<Livro> findByAutor(Autor autor);
    }


