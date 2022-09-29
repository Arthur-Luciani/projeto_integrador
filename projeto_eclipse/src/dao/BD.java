package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {

	private static Connection conexao;
	
	public static Connection getConexao() {
		try {
			return conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "aluno");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void fechaConexao() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
