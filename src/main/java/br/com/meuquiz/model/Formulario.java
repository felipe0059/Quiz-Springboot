package br.com.meuquiz.model;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Formulario {

	private List<Questao> questoes;

}
