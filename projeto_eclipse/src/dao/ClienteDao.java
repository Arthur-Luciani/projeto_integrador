package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDao {
	private Connection conexao = BD.getConexao();

	public ClienteDao() {}
	
	public ArrayList<String> nomesClientes() {
		ArrayList<String>listaClientes= new ArrayList<>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select nome from cliente");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					String nome = rs.getString("nome");
					listaClientes.add(nome);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaClientes;
	}
}
