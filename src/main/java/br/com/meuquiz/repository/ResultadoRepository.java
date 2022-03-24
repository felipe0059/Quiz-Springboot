package br.com.meuquiz.repository;

import br.com.meuquiz.model.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Integer> {
	
}
