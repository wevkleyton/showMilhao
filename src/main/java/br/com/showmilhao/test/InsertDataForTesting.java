package br.com.showmilhao.test;

import br.com.showmilhao.dao.JogadorDAO;
import br.com.showmilhao.dao.PeruntaDAO;
import br.com.showmilhao.model.Pergunta;

public class InsertDataForTesting {

	private static JogadorDAO jogadorDAO = new JogadorDAO();
	private static PeruntaDAO peruntaDAO = new PeruntaDAO();
	
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
		pergunta.setNivel("facil");
		pergunta.setEnunciado("Qual é a cor do cavalo branco de Napoleão?");
		pergunta.setAlternativa1("Preto");
		pergunta.setAlternativa2("vermelho");
		pergunta.setAlternativa3("Amarelo");
		pergunta.setResposta("Branco");
		peruntaDAO.adicionar(pergunta);
		
	

	}
}
