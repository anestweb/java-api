package br.projetointegrador.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/AnestWeb";
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
