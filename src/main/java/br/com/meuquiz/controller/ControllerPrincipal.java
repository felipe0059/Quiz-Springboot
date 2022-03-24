package br.com.meuquiz.controller;

import java.util.List;

import br.com.meuquiz.model.Formulario;
import br.com.meuquiz.model.Resultado;
import br.com.meuquiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ControllerPrincipal {
	
	@Autowired
	Resultado resultado;
	@Autowired
	QuizService quizService;
	
	Boolean registrada = false;
	
	@ModelAttribute("resultado")
	public Resultado getResultado() {
		return resultado;
	}
	
	@GetMapping("/")
	public String home() {
		return "index.html";
	}
	
	@PostMapping("/quiz")
	public String quiz(@RequestParam String nickname, Model m, RedirectAttributes redirectattributes) {
		if(nickname.equals("")) {
			redirectattributes.addFlashAttribute("aviso", "Você precisa digitar um nickname !");
			return "redirect:/";
		}

		registrada = false;
		resultado.setNickname(nickname);
		
		Formulario formulario = quizService.getQuestao();
		m.addAttribute("formulario", formulario);
		
		return "quiz.html";
	}
	
	@PostMapping("/submit")
	public String submit(@ModelAttribute Formulario formulario, Model m) {
		if(!registrada) {
			resultado.setTotalrespostascorretas(quizService.getResultado(formulario));
			quizService.salvaPontuacao(resultado);
			registrada = true;
		}
		
		return "resultado.html";
	}
	
	@GetMapping("/pontuacao")
	public String pontuacao(Model m) {
		List<Resultado> pontuacaoSalva = quizService.getMaiorPontuacao();
		m.addAttribute("pontuacaoSalva", pontuacaoSalva);
		
		return "ranking.html";
	}

}
