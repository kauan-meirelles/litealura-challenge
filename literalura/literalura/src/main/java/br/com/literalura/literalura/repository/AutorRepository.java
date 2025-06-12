package br.com.literalura.literalura.repository;


import br.com.literalura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> { 
      Optional<Autor> findByNome(String nome); 
      
      List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(int ano1, int ano2);
}

