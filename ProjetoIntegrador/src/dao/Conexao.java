package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static final String URL = "jdbc:sqlite:./faculdade.db";
	
	public static Connection getConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			return DriverManager.getConnection(URL);
		} catch (Exception e) {
			System.out.print("Erro ao conectar no BD!" + e.getMessage());
			return null;
		}
	}
	
}
