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

		int opc = 5;  // 1 = adiciona , 2 = altera, 3 = lista, 4 = remove

		Pergunta pergunta = new Pergunta();
		if (opc == 1) {
			pergunta.setNivel("Facil");
			pergunta.setEnunciado("HHHHHHH?");
			pergunta.setAlternativa1("cacac");
			pergunta.setAlternativa2("vsvdfb");
			pergunta.setAlternativa3("acgs");
			pergunta.setResposta("wegtrbtreb");
			perguntaDAO.adicionar(pergunta);
		}else if (opc == 2 ) {
			pergunta.setId(5);
			pergunta.setNivel("Dificil");
			pergunta.setEnunciado("DDDDD");
			pergunta.setAlternativa1("ananc");
			pergunta.setAlternativa2("slkcnlvfd");
			pergunta.setAlternativa3("asnava");
			pergunta.setResposta("1341152");
			perguntaDAO.atualizar(pergunta);
		}else if (opc == 3) {
			perguntaDAO.listar().forEach(System.out::println);;
		}else if (opc == 4) {
			perguntaDAO.listar("Facil").forEach(System.out::println);
		}else if (opc == 5) {
			perguntaDAO.listar("(2)", "Facil").forEach(System.out::println);
		}else if (opc == 6) {
			perguntaDAO.remover(1);
			
		}




	}
}
