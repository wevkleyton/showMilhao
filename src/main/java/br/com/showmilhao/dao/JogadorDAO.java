package br.com.showmilhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.showmilhao.connection.ConnectionFactory;
import br.com.showmilhao.model.Jogador;
import br.com.showmilhao.util.LogUtil;

public class JogadorDAO {

	private Connection connection;

	private final String QUERY_INSERT_JOGADOR = "INSERT INTO jogador (id, nome, pontuacao) VALUES ($next_id, ?,?)";
	private final String QUERY_UPDATE_JOGADOR = "UPDATE jogador set nome =?, pontuacao= ? WHERE id = ?";
	private final String QUERY_CONSULTAR_TODOS = "SELECT *  FROM jogador ORDER BY pontuacao DESC LIMIT 10";
	private final String QUERY_CUNSULTA_RANKING = "SELECT *  FROM jogador";
	private final String QUERY_ZERAR_RANKING = "DELETE FROM jogador";

	public JogadorDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public boolean adicionar(Jogador jogador) {
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_INSERT_JOGADOR)) {
			stmt.setString(2, jogador.getNome());
			stmt.setInt(3, jogador.getPontuacao());
			stmt.executeUpdate();
			connection.commit();
			return true;
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
			return false;
		}
	}

	@SuppressWarnings("unlikely-arg-type")
	public void atualizar(Jogador jogador) {
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_UPDATE_JOGADOR)) {
			stmt.setString(1, jogador.getNome());
			stmt.setInt(2, jogador.getPontuacao());
			stmt.setInt(3, jogador.getId());
			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
		}
	}

	private List<Jogador> buscar(String sql) {
		List<Jogador> jogadores = new ArrayList<>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Jogador jogador = new Jogador();
					jogador.setId(rs.getInt("id"));
					jogador.setLinha(rs.getRow());
					jogador.setNome(rs.getString("nome"));
					jogador.setPontuacao(rs.getInt("pontuacao"));
					jogadores.add(jogador);
				}
			}
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
		}

		return jogadores;
	}

	public List<Jogador> listar() {
		return buscar(QUERY_CUNSULTA_RANKING);
	}

	public List<Jogador> listarranking() {
		return buscar(QUERY_CONSULTAR_TODOS);
	}

	public void zerarranking() {
		try (PreparedStatement stmt = connection.prepareStatement(QUERY_ZERAR_RANKING)) {
			stmt.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			LogUtil.getLogger(JogadorDAO.class).error(e.getCause().toString());
		}
	}
}
