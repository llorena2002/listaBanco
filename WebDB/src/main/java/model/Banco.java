package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Banco {
	
	private static Connection connection;
	
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/web","postgres","postgres");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
