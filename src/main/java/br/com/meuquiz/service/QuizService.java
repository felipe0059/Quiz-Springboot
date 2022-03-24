package br.com.meuquiz.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.meuquiz.model.Questao;
import br.com.meuquiz.model.Formulario;
import br.com.meuquiz.model.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import br.com.meuquiz.repository.QuestaoRepository;
import br.com.meuquiz.repository.ResultadoRepository;

@Service
public class QuizService {

	@Autowired
	Formulario formulario;
	@Autowired
	QuestaoRepository questaoRepository;
	@Autowired
	ResultadoRepository resultadoRepository;

	public Formulario getQuestao() {
		List<Questao> todasquestoes = questaoRepository.findAll();
		List<Questao> questaoRepository = new ArrayList<Questao>();
		
		Random random = new Random();
		
		for(int i=0; i<5; i++) {
			int aleatorio = random.nextInt(todasquestoes.size());
			questaoRepository.add(todasquestoes.get(aleatorio));
			todasquestoes.remove(aleatorio);
		}

		formulario.setQuestoes(questaoRepository);
		
		return formulario;
	}
	
	public int getResultado(Formulario formulario) {
		int respostasCorretas = 0;
		
		for(Questao questao: formulario.getQuestoes())
			if(questao.getRespostacorreta() == questao.getEscolha())
				respostasCorretas++;
		
		return respostasCorretas;
	}
	
	public void salvaPontuacao(Resultado resultado) {
		Resultado saveResultado = new Resultado();
		saveResultado.setNickname(resultado.getNickname());
		saveResultado.setTotalrespostascorretas(resultado.getTotalrespostascorretas());
		resultadoRepository.save(saveResultado);
	}
	
	public List<Resultado> getMaiorPontuacao() {
		List<Resultado> pontuacaoSalva = resultadoRepository.findAll(Sort.by(Sort.Direction.DESC, "totalrespostascorretas"));
		
		return pontuacaoSalva;
	}
}
