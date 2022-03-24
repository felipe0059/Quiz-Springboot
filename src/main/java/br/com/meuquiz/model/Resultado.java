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
@Table(name = "resultados")
@Getter
@Setter
@ToString
public class Resultado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nickname;
	private int totalrespostascorretas = 0;

	public Resultado() {
		super();
	}
}
