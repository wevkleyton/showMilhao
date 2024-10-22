package br.com.showmilhao.test;

import br.com.showmilhao.dao.JogadorDAO;
import br.com.showmilhao.dao.PeruntaDAO;
import br.com.showmilhao.model.Pergunta;

public class InsertDataForTesting {

	private static JogadorDAO jogadorDAO = new JogadorDAO();
	private static PeruntaDAO perguntaDAO = new PeruntaDAO();
	
	public static void main(String[] args) {
//		Jogador jogador = new Jogador("Pedro", 200);
//		System.out.println(jogadorDAO.adicionar(jogador));
		
//		Jogador jogador = new Jogador();
//		jogador.setId(1);
//		jogador.setNome("Tadeu espindola");
//		jogador.setPontuacao(1000);
//		jogadorDAO.atualizar(jogador);
		
//		jogadorDAO.listar().forEach(j -> System.out.println(j.getNome()));
//		jogadorDAO.listar().forEach(System.out::println);
//		jogadorDAO.listarranking().forEach(System.out::println);
		
//		jogadorDAO.zerarranking();
		
		Pergunta pergunta = new Pergunta();
//		pergunta.setNivel("Dificil");
//		pergunta.setEnunciado("Quem descobriu o Brasil?");
//		pergunta.setAlternativa1("Xuxa");
//		pergunta.setAlternativa2("Tadeu");
//		pergunta.setAlternativa3("Ana");
//		pergunta.setResposta("Pedro A. C.");
//		pergunta.setId(1);
//		peruntaDAO.atualizar(pergunta);
		perguntaDAO.remover(1);
		
		
	

	}
}
