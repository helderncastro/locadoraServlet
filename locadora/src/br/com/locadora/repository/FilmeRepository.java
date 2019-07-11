package br.com.locadora.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.locadora.config.ConnectionConfig;
import br.com.locadora.model.Filme;

public class FilmeRepository {

	public void cadastrarFilme(Filme filme) throws SQLException {

		Connection con = ConnectionConfig.getConnection();

		String insertTableSQL = "INSERT INTO FILME (NOMEFILME, DATAFILME, QTDFILME, CLASSFILME, VALORFILME) VALUES (?,?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		java.util.Date d = filme.getData();
		java.sql.Date dt = new java.sql.Date(d.getTime());

		preparedStatement.setString(1, filme.getNome());
		preparedStatement.setDate(2, dt); // DATE DO JAVA.UTIL PARA SQL
		preparedStatement.setLong(3, filme.getQuantidade());
		preparedStatement.setString(4, filme.getClassificacao());
		preparedStatement.setLong(5, filme.getValor().longValue());

		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();

	}

	public void deletarFilme(Integer idFilme) throws SQLException {

		Connection con = ConnectionConfig.getConnection();

		String deleteFilmeSQL = "DELETE FROM FILME WHERE idFilme = (?) ";
		PreparedStatement preparedStatement = con.prepareStatement(deleteFilmeSQL);
		preparedStatement.setInt(1, idFilme);
		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();
	}

	public List<Filme> listarFilmes() throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql = "SELECT idFilme, nomeFilme, dataFilme, qtdFilme, classFilme, valorFilme FROM FILME";

		ResultSet res = stmt.executeQuery(sql);

		List<Filme> filmes = new ArrayList<Filme>();

		while (res.next()) {
			Filme f = new Filme();

			f.setId(res.getInt("idFilme"));
			f.setNome(res.getString("nomeFilme"));
			f.setData(res.getDate("dataFilme"));
			f.setQuantidade(res.getInt("qtdFilme"));
			f.setClassificacao(res.getString("classFilme"));
			f.setValor(res.getBigDecimal("valorFilme"));

			filmes.add(f);

		}
		con.close();

		return filmes;

	}

	public Filme listarFilmeId(Integer idFilme) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;
		sql = "SELECT idFilme, nomeFilme, dataFilme, qtdFilme, classFilme, valorFilme FROM FILME WHERE idFilme = "
				+ idFilme;

		ResultSet res = stmt.executeQuery(sql);

		Filme f = new Filme();

		res.next();
		f.setId(res.getInt("idFilme"));
		f.setNome(res.getString("nomeFilme"));
		f.setData(res.getDate("dataFilme"));
		f.setQuantidade(res.getInt("qtdFilme"));
		f.setClassificacao(res.getString("classFilme"));
		f.setValor(res.getBigDecimal("valorFilme"));

		con.close();

		return f;

	}

	public void atualizarFilme(Filme filme) throws SQLException {

		Connection con = ConnectionConfig.getConnection();

		String insertTableSQL = "UPDATE FILME SET NOMEFILME = ? , DATAFILME = ? ,  QTDFILME = ? ,  "
				+ " CLASSFILME = ? , " + " VALORFILME = ?  WHERE IDFILME = ?";

		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		java.util.Date d = filme.getData();
		java.sql.Date dt = new java.sql.Date(d.getTime());

		preparedStatement.setString(1, filme.getNome());
		preparedStatement.setDate(2, dt); // DATE DO JAVA.UTIL PARA SQL
		preparedStatement.setLong(3, filme.getQuantidade());
		preparedStatement.setString(4, filme.getClassificacao());
		preparedStatement.setLong(5, filme.getValor().longValue());

		preparedStatement.setInt(6, filme.getId());

		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();

	}

	public boolean existeFilme(Integer idFilme) throws SQLException {
		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT idFilme FROM FILME WHERE idFilme = " + idFilme;

		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {
			con.close();
			return true;

		} else {
			con.close();
			return false;
		}

	}

	public boolean existeFilmeLocado(Integer idFilme) throws SQLException {
		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT IDlOCACAO,FINALIZADOLOCACAO, item_locacao.idfilmeitlocacao FROM LOCACAO "
				+ "INNER JOIN ITEM_LOCACAO ON IDLOCACAO = item_locacao.idlocacaoitlocacao "
				+ "WHERE FINALIZADOLOCACAO = 'N' AND IDFILMEITLOCACAO = " + idFilme;

		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {
			con.close();
			return true;

		} else {
			con.close();
			return false;
		}
	}

	public int totalFilmesLocados(Integer idFilme) throws SQLException {
		int totalFilmes = 0;

		if (existeFilmeLocado(idFilme)) {
			Connection con = ConnectionConfig.getConnection();
			Statement stmt = con.createStatement();

			String sqlContaLocacoes = "SELECT IDFILMEITLOCACAO, COUNT( FINALIZADOLOCACAO) AS TTLOCADO FROM LOCACAO "
					+ " INNER JOIN ITEM_LOCACAO ON IDLOCACAO = item_locacao.idlocacaoitlocacao "
					+ " WHERE FINALIZADOLOCACAO = 'N' and IDFILMEITLOCACAO = " + idFilme
					+ " GROUP BY IDFILMEITLOCACAO ";

			ResultSet resFilmeDisp = stmt.executeQuery(sqlContaLocacoes);

			resFilmeDisp.next();
			totalFilmes = resFilmeDisp.getInt("TTLOCADO");

			con.close();
		}

		return totalFilmes;
	}

	public List<Filme> listarFilmesDisponiveis() throws SQLException {

		List<Filme> filmes = listarFilmes();
		List<Filme> filmesDisponiveis = new ArrayList<Filme>();

		for (int x = 0; x < filmes.size(); x++) {

			int totalFilmes = filmes.get(x).getQuantidade();
			int totalLocados = totalFilmesLocados(filmes.get(x).getId());

			if (totalFilmes > totalLocados) {
				filmesDisponiveis.add(filmes.get(x));
			}
		}

		return filmesDisponiveis;

	}

	public List<Filme> listarFilmesLocados() throws SQLException {
		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql = " SELECT FILME.* FROM FILME INNER JOIN ITEM_LOCACAO "
				+ " ON IDFILME = item_locacao.idfilmeitlocacao "
				+ " INNER JOIN LOCACAO ON item_locacao.idlocacaoitlocacao = locacao.idlocacao "
				+ " WHERE FINALIZADOLOCACAO = 'N' ";

		ResultSet res = stmt.executeQuery(sql);

		List<Filme> filmes = new ArrayList<Filme>();

		while (res.next()) {
			Filme f = new Filme();

			f.setId(res.getInt("idFilme"));
			f.setNome(res.getString("nomeFilme"));
			f.setData(res.getDate("dataFilme"));
			f.setQuantidade(res.getInt("qtdFilme"));
			f.setClassificacao(res.getString("classFilme"));
			f.setValor(res.getBigDecimal("valorFilme"));

			filmes.add(f);

		}
		con.close();

		return filmes;
	}

}
