package br.com.showmilhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import br.com.showmilhao.connection.ConnectionFactory;
import br.com.showmilhao.model.Pergunta;
import br.com.showmilhao.util.LogUtil;

public class PeruntaDAO {
	
	private Connection connection;
	
	private static final String QUERY_INSERT_PERGUNTA =  "INSERT INTO perguntas(id, nivel, enunciado, alternativa1, alternativa2, alternativa3, resposta) VALUES ($nest_id, ?, ?, ?, ?, ?, ?)";
	private static final String OK = "Processo cocluido!";
	private static final int MESSAGE_TYPE = JOptionPane.INFORMATION_MESSAGE;
	
	public PeruntaDAO() {
		connection = ConnectionFactory.getConnection();
	}
	
	public void adicionar(Pergunta pergunta) {
		try {
			try(PreparedStatement stmt = connection.prepareStatement(QUERY_INSERT_PERGUNTA)){
				stmt.setString(2, pergunta.getNivel());
				stmt.setString(3, pergunta.getEnunciado());
				stmt.setString(4, pergunta.getAlternativa1());
				stmt.setString(5, pergunta.getAlternativa2());
				stmt.setString(6, pergunta.getAlternativa3());
				stmt.setString(7, pergunta.getResposta());
				stmt.executeUpdate();
				connection.commit();
			}
			JOptionPane.showMessageDialog(new JFrame(), "Pergunta Adicionada com Sucesso!", OK, MESSAGE_TYPE);
		} catch (Exception e) {
			LogUtil.getLogger(PeruntaDAO.class).error(e.getCause().toString());
		}
	}
	

	
}
