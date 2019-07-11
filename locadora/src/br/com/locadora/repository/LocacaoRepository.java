package br.com.locadora.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.locadora.config.ConnectionConfig;
import br.com.locadora.model.Locacao;

public class LocacaoRepository {

	public List<Locacao> listarLocacoes() throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;
		sql = "SELECT idLocacao, dataLocacao, idCliLocacao, dataDevLocacao, finalizadoLocacao "
				+ " FROM LOCACAO ORDER BY idLocacao";

		ResultSet res = stmt.executeQuery(sql);

		List<Locacao> locacoes;
		locacoes = new ArrayList<Locacao>();

		while (res.next()) {
			Locacao l = new Locacao();

			l.setId(res.getInt("idLocacao"));
			l.setDataDevolucao(res.getDate("dataLocacao"));
			l.setIdCliente(res.getInt("idCliLocacao"));
			l.setDataDevolucao(res.getDate("dataDevLocacao"));
			l.setFinalizado(res.getString("finalizadoLocacao"));

			locacoes.add(l);
		}
		con.close();

		return locacoes;

	}

	public void cadastrarLocacao(Locacao locacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		String insertTableSQL = "INSERT INTO LOCACAO (DATALOCACAO, IDCLILOCACAO, DATADEVLOCACAO, FINALIZADOLOCACAO)"
				+ " VALUES (?,?,?,?)";

		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		java.util.Date dtLocacao = locacao.getDataLocacao();
		java.sql.Date dtLocacaoSQL = new java.sql.Date(dtLocacao.getTime());

		java.util.Date dtDevolucao = locacao.getDataDevolucao();
		java.sql.Date dtDevolucaoSQL = new java.sql.Date(dtDevolucao.getTime());

		preparedStatement.setDate(1, dtLocacaoSQL);
		preparedStatement.setInt(2, locacao.getIdCliente());
		preparedStatement.setDate(3, dtDevolucaoSQL);
		preparedStatement.setString(4, locacao.getFinalizado());

		preparedStatement.execute();
		preparedStatement.close();

	}

	public void deletarLocacao(Integer idLocacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection();

		String deleteSQL = "DELETE FROM LOCACAO WHERE idLocacao = (?) ";
		PreparedStatement preparedStatement = con.prepareStatement(deleteSQL);
		preparedStatement.setInt(1, idLocacao);
		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();

	}

	public boolean existePeloMenosUmFilme(Integer idLocacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT IDFILMEITLOCACAO, IDLOCACAOITLOCACAO FROM ITEM_LOCACAO " + "WHERE IDLOCACAOITLOCACAO = "
				+ idLocacao;

		ResultSet res = stmt.executeQuery(sql);
		if (res.next()) {
			con.close();
			return true;

		} else {
			con.close();
			return false;
		}

	}

	public Locacao listarLocacaoId(Integer idLocacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT IDLOCACAO, DATALOCACAO, IDCLILOCACAO, DATADEVLOCACAO, FINALIZADOLOCACAO "
				+ " FROM LOCACAO WHERE idLocacao = " + idLocacao;

		ResultSet res = stmt.executeQuery(sql);

		Locacao l = new Locacao();

		res.next();

		l.setId(res.getInt("idLocacao"));
		l.setDataDevolucao(res.getDate("dataDevLocacao"));
		l.setDataLocacao(res.getDate("dataLocacao"));
		l.setIdCliente(res.getInt("idCliLocacao"));
		l.setFinalizado(res.getString("finalizadoLocacao"));

		con.close();

		return l;
	}

	public void atualizarLocacao(Locacao locacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection(); //

		String insertTableSQL = "UPDATE LOCACAO SET DATALOCACAO = ?, IDCLILOCACAO = ?, DATADEVLOCACAO = ?,  "
				+ " FINALIZADOLOCACAO = ?  WHERE IDLOCACAO = ?";

		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		java.util.Date dtLocacao = locacao.getDataDevolucao();
		java.util.Date dtDevolucao = locacao.getDataDevolucao();

		java.sql.Date dtLoc = new java.sql.Date(dtLocacao.getTime());
		java.sql.Date dtDev = new java.sql.Date(dtDevolucao.getTime());

		preparedStatement.setDate(1, dtLoc);
		preparedStatement.setInt(2, locacao.getIdCliente());
		preparedStatement.setDate(3, dtDev);
		preparedStatement.setString(4, locacao.getFinalizado());

		preparedStatement.setInt(5, locacao.getId());

		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();

	}

	public boolean existeLocacao(Integer idLocacao) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT idLocacao FROM LOCACAO WHERE idLocacao = " + idLocacao;

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
