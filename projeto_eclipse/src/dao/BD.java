package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {
	Connection conexao;
	public BD() throws SQLException {
		this.conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybd", "root", "aluno");
	}
}
