package br.com.locadora.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.locadora.config.ConnectionConfig;
import br.com.locadora.model.Cliente;

public class ClienteRepository {

	public List<Cliente> listarClientes() throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;
		sql = "SELECT idCliente, nomeCliente, cpfCliente, tpLogCliente, logCliente, numeroCliente, compCliente, "
				+ "bairroCliente, cidadeCliente, cepCliente, ufCliente, telefoneCliente, emailCliente FROM CLIENTE";

		ResultSet res = stmt.executeQuery(sql);

		List<Cliente> clientes;
		clientes = new ArrayList<Cliente>();

		while (res.next()) {
			Cliente c = new Cliente();

			c.setId(res.getInt("idCliente"));
			c.setNome(res.getString("nomeCliente"));
			c.setCpf(res.getString("cpfCliente"));
			c.setTipoLogradouro(res.getString("tpLogCliente"));
			c.setLogradouro(res.getString("logCliente"));
			c.setNumero(res.getInt("numeroCliente"));
			c.setComplemento(res.getString("compCliente"));
			c.setBairro(res.getString("bairroCliente"));
			c.setCidade(res.getString("cidadeCliente"));
			c.setCep(res.getString("cepCliente"));
			c.setUf(res.getString("ufCliente"));
			c.setTelefone(res.getString("telefoneCliente"));
			c.setEmail(res.getString("emailCliente"));

			clientes.add(c);
		}
		con.close();

		return clientes;

	}

	public void cadastrar(Cliente cliente) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		String insertTableSQL = "INSERT INTO CLIENTE (NOMECLIENTE, CPFCLIENTE, TPLOGCLIENTE, LOGCLIENTE, NUMEROCLIENTE, "
				+ "COMPCLIENTE, BAIRROCLIENTE, CIDADECLIENTE, CEPCLIENTE, UFCLIENTE, TELEFONECLIENTE, EMAILCLIENTE) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		if(cliente.getNumero() == null) {
			cliente.setNumero(0);
		}
		
		preparedStatement.setString(1, cliente.getNome());
		preparedStatement.setString(2, cliente.getCpf());
		preparedStatement.setString(3, cliente.getTipoLogradouro());
		preparedStatement.setString(4, cliente.getLogradouro());
		preparedStatement.setInt(5, cliente.getNumero());
		preparedStatement.setString(6, cliente.getComplemento());
		preparedStatement.setString(7, cliente.getBairro());
		preparedStatement.setString(8, cliente.getCidade());
		preparedStatement.setString(9, cliente.getCep());
		preparedStatement.setString(10, cliente.getUf());
		preparedStatement.setString(11, cliente.getTelefone());
		preparedStatement.setString(12, cliente.getEmail());

		preparedStatement.execute();
		preparedStatement.close();

	}

	public void deletar(Integer idCliente) throws SQLException {

		Connection con = ConnectionConfig.getConnection();

		String deleteFilmeSQL = "DELETE FROM CLIENTE WHERE idCliente = (?) ";
		PreparedStatement preparedStatement = con.prepareStatement(deleteFilmeSQL);
		preparedStatement.setInt(1, idCliente);
		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();
	}

	public boolean existeLocacao(Integer idCliente) throws SQLException {
		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT IDCLILOCACAO FROM LOCACAO WHERE FINALIZADOLOCACAO = 'N' AND IDCLILOCACAO = " + idCliente;

		ResultSet res = stmt.executeQuery(sql);

		if (res.next()) {
			con.close();
			return true;

		} else {
			con.close();
			return false;
		}

	}
	
	public Cliente listarClienteId(Integer idCliente) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT idCliente, nomeCliente, cpfCliente, tpLogCliente, logCliente, numeroCliente, compCliente, "
				+ "bairroCliente, cidadeCliente, cepCliente, ufCliente, telefoneCliente, emailCliente "
				+ " FROM CLIENTE WHERE idCliente = " + idCliente;

		ResultSet res = stmt.executeQuery(sql);

		Cliente c = new Cliente();

		res.next();

		c.setId(res.getInt("idCliente"));
		c.setNome(res.getString("nomeCliente"));
		c.setCpf(res.getString("cpfCliente"));
		c.setTipoLogradouro(res.getString("tpLogCliente"));
		c.setLogradouro(res.getString("logCliente"));
		c.setNumero(res.getInt("numeroCliente"));
		c.setComplemento(res.getString("compCliente"));
		c.setBairro(res.getString("bairroCliente"));
		c.setCidade(res.getString("cidadeCliente"));
		c.setCep(res.getString("cepCliente"));
		c.setUf(res.getString("ufCliente"));
		c.setTelefone(res.getString("telefoneCliente"));
		c.setEmail(res.getString("emailCliente"));

		con.close();

		return c;

	}

	public void atualizar(Cliente cliente) throws SQLException {

		Connection con = ConnectionConfig.getConnection();

		String insertTableSQL = "UPDATE CLIENTE SET NOMECLIENTE = ?, CPFCLIENTE = ?, TPLOGCLIENTE = ?,  "
				+ " LOGCLIENTE = ?, NUMEROCLIENTE = ?, COMPCLIENTE = ?, BAIRROCLIENTE = ?, CIDADECLIENTE = ?, "
				+ " CEPCLIENTE = ?, UFCLIENTE = ?, TELEFONECLIENTE = ?, EMAILCLIENTE = ?  WHERE IDCLIENTE = ?";

		PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);

		preparedStatement.setString(1, cliente.getNome());
		preparedStatement.setString(2, cliente.getCpf());
		preparedStatement.setString(3, cliente.getTipoLogradouro());
		preparedStatement.setString(4, cliente.getLogradouro());
		preparedStatement.setInt(5, cliente.getNumero());
		preparedStatement.setString(6, cliente.getComplemento());
		preparedStatement.setString(7, cliente.getBairro());
		preparedStatement.setString(8, cliente.getCidade());
		preparedStatement.setString(9, cliente.getCep());
		preparedStatement.setString(10, cliente.getUf());
		preparedStatement.setString(11, cliente.getTelefone());
		preparedStatement.setString(12, cliente.getEmail());

		preparedStatement.setInt(13, cliente.getId());

		preparedStatement.executeUpdate();
		preparedStatement.close();

		con.close();

	}

	public boolean existeCliente(Integer idCliente) throws SQLException {

		Connection con = ConnectionConfig.getConnection();
		Statement stmt = con.createStatement();

		String sql;

		sql = "SELECT idCliente FROM CLIENTE WHERE idCliente = " + idCliente;

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
