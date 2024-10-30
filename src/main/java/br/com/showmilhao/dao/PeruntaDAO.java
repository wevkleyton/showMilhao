package br.com.showmilhao.dao;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.ls.LSInput;

import br.com.showmilhao.connection.ConnectionFactory;
import br.com.showmilhao.model.Pergunta;
import br.com.showmilhao.util.LogUtil;

public class PeruntaDAO {

	private Connection connection;

	private static final String ORDER_BY_LIMIT = "ORDER BY RANDOM() LIMIT 1";
	private static final String QUERY_INSERT_PERGUNTA = "INSERT INTO perguntas(id, nivel, enunciado, alternativa1, alternativa2, alternativa3, resposta) VALUES ($nest_id, ?, ?, ?, ?, ?, ?)";
	private static final String QUERY_UPDATE_PERGUNTA = "UPDATE perguntas SET nivel = ?, enunciado = ?, alternativa1= ?, alternativa2 = ?, alternativa3 =?, resposta =? WHERE id = ?";
	private static final String QUERY_DELETE = "DELETE FROM perguntas WHERE id = ?";
	private static final String QUERY_SELECT_ALL = "SELECT * FROM perguntas";
	private static final String QUERY_SELECT_NIVEL = "SELECT * FROM perguntas WHERE nivel = ? ";
	private static final String QUERY_SELECT_NIVEL_RANDOM_LIMIT = "SELECT * FROM perguntas WHERE nivel = ? ";
	private static final String QUERY_SELECT_NIVEL_RANDOM_LIMIT_PERUNTAS_FEITAS = "SELECT * FROM perguntas WHERE nivel = ? AND perguntas.id NOT IN ";
	private static final String OK = "Processo cocluido!";
	private static final int MESSAGE_TYPE = JOptionPane.INFORMATION_MESSAGE;

	public PeruntaDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public void adicionar(Pergunta pergunta) {
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_INSERT_PERGUNTA)) {
			stmt.setString(2, pergunta.getNivel());
			stmt.setString(3, pergunta.getEnunciado());
			stmt.setString(4, pergunta.getAlternativa1());
			stmt.setString(5, pergunta.getAlternativa2());
			stmt.setString(6, pergunta.getAlternativa3());
			stmt.setString(7, pergunta.getResposta());
			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			LogUtil.getLogger(PeruntaDAO.class).error(e.getCause().toString());
		}
		JOptionPane.showMessageDialog(new JFrame(), "Pergunta Adicionada com Sucesso!", OK, MESSAGE_TYPE);
	}

	public void atualizar(Pergunta pergunta) {
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_UPDATE_PERGUNTA)) {
			stmt.setString(1, pergunta.getNivel());
			stmt.setString(2, pergunta.getEnunciado());
			stmt.setString(3, pergunta.getAlternativa1());
			stmt.setString(4, pergunta.getAlternativa2());
			stmt.setString(5, pergunta.getAlternativa3());
			stmt.setString(6, pergunta.getResposta());
			stmt.setInt(7, pergunta.getId());
			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			LogUtil.getLogger(PeruntaDAO.class).error(e.getCause().toString());
		}
		JOptionPane.showMessageDialog(new Frame(), "Pergunta Alterada com Sucesso!", OK, MESSAGE_TYPE);

	}

	public void remover(Integer idPergunta) {
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_DELETE)) {
			stmt.setInt(1, idPergunta);
			stmt.execute();
			connection.commit();
		} catch (Exception e) {
			LogUtil.getLogger(PeruntaDAO.class).error(e.getCause().toString());
		}
		JOptionPane.showMessageDialog(new Frame(), "Pergunta Removida com Sucesso!", OK, MESSAGE_TYPE);

	}

	private List<Pergunta> buscar(String sql, String nivel) {
		List<Pergunta> perguntas = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			if (Objects.nonNull(nivel))
				stmt.setString(1, nivel);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Pergunta pergunta = new Pergunta();
					pergunta.setId(rs.getInt("id"));
					pergunta.setNivel(rs.getString("nivel"));
					pergunta.setEnunciado(rs.getString("enunciado"));
					pergunta.setAlternativa1(rs.getString("alternativa1"));
					pergunta.setAlternativa2(rs.getString("alternativa2"));
					pergunta.setAlternativa3(rs.getString("alternativa3"));
					pergunta.setResposta(rs.getString("resposta"));
					perguntas.add(pergunta);

				}
			}
		} catch (Exception e) {
			LogUtil.getLogger(PeruntaDAO.class).error(e.getCause().toString());
		}

		return perguntas;
	}

	public List<Pergunta> listar() {
		return buscar(QUERY_SELECT_ALL, null);

	}

	public List<Pergunta> listar(String nivel) {
		return buscar(QUERY_SELECT_NIVEL, nivel);
	}

	public List<Pergunta> listar(String idsPerguntasFeitas, String nivel) {
		String sql = idsPerguntasFeitas.isEmpty() ? QUERY_SELECT_NIVEL_RANDOM_LIMIT + ORDER_BY_LIMIT
				: QUERY_SELECT_NIVEL_RANDOM_LIMIT_PERUNTAS_FEITAS + idsPerguntasFeitas + ORDER_BY_LIMIT;
		return buscar(sql, nivel);
	}

}
