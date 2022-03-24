package br.com.meuquiz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "questoes")
@Getter
@Setter
@ToString
public class Questao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo;
	private String opcao_A;
	private String opcao_B;
	private String opcao_C;
	private int respostacorreta;
	private int escolha;

	public Questao() {
		super();
	}


}
