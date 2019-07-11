package br.com.locadora.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.locadora.config.ConnectionConfig;
import br.com.locadora.model.ItemLocacao;

public class ItemLocacaoRepository {

	public List<ItemLocacao> listarItLocacoes() throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;
		sql = "SELECT idItLocacao, idFilmeItLocacao, idLocacaoItLocacao FROM ITEM_LOCACAO ORDER BY idItLocacao";

		ResultSet res = stmt.executeQuery(sql);

		List<ItemLocacao> itemLocacoes;
		itemLocacoes = new ArrayList<ItemLocacao>();

		while (res.next()) {
			ItemLocacao i = new ItemLocacao();

			i.setId(res.getInt("idItLocacao"));
			i.setIdFilme(res.getInt("idFilmeItLocacao"));
			i.setIdLocacao(res.getInt("idLocacaoItLocacao"));

			itemLocacoes.add(i);
		}
		con.close();

		return itemLocacoes;

	}

	public void cadastrarItLocacao(ItemLocacao itLocacao) throws SQLException {
		Connection con = ConnectionConfig.getConnection();
		String insertTableSQL = "INSERT INTO ITEM_LOCACAO ( idFilmeItLocacao, idLocacaoItLocacao)" + " VALUES (?,?)";

		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		preparedStatement.setInt(1, itLocacao.getIdFilme());
		preparedStatement.setInt(2, itLocacao.getIdLocacao());

		preparedStatement.execute();
		preparedStatement.close();
	}

	public void deletarItLocacao(Integer idItLocacao) throws SQLException {
		Connection con = ConnectionConfig.getConnection();

		String deleteSQL = "DELETE FROM ITEM_LOCACAO WHERE idItLocacao = (?) ";
		PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
		preparedStatement.setInt(1, idItLocacao);
		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();
	}

	public ItemLocacao listarItLocacaoId(Integer idItLocacao) throws SQLException {
		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT idItLocacao, idFilmeItLocacao, idLocacaoItLocacao  FROM ITEM_LOCACAO WHERE idItLocacao = "
				+ idItLocacao;

		ResultSet res = stmt.executeQuery(sql);

		ItemLocacao i = new ItemLocacao();

		res.next();

		i.setId(res.getInt("idItLocacao"));
		i.setIdFilme(res.getInt("idFilmeItLocacao"));
		i.setIdLocacao(res.getInt("idLocacaoItLocacao"));

		con.close();

		return i;
	}

	public void atualizarItLocacao(ItemLocacao itemLocacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection(); //

		String insertTableSQL = "UPDATE ITEM_LOCACAO SET idFilmeItLocacao = ?, idLocacaoItLocacao = ?  WHERE idItLocacao = ?";

		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		preparedStatement.setInt(1, itemLocacao.getIdFilme());
		preparedStatement.setInt(2, itemLocacao.getIdLocacao());
		preparedStatement.setInt(3, itemLocacao.getId());

		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();

	}

	public boolean existeItLocacao(Integer idItLocacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement(); //

		String sql;

		sql = "SELECT idItLocacao FROM ITEM_LOCACAO WHERE idItLocacao = " + idItLocacao;

		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {
			con.close();
			return true;

		} else {
			con.close();
			return false;
		}

	}
}
