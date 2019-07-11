package br.com.locadora.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

public class ConnectionConfig {

	static final String SERVER = "localhost"; // endereço do servidor
	static final String PORT = "1521"; // Porta TCP padrão do Oracle
	static final String DATABASE = "xe"; // nome da base de dados

	// Configuração dos parâmetros de autenticação0
	static final String USER = "SYSTEM";
	static final String PASSWD = "root";
	
	
	public static Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABASE;
			Connection con = DriverManager.getConnection(url, USER, PASSWD);
			
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
