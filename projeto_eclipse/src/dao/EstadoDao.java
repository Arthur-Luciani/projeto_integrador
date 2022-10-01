package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.cj.protocol.Message;

public class EstadoDao {
	Connection conexao;
	public EstadoDao() {
		conexao = BD.getConexao();
	}
	public LinkedList<String> nomeEstados(){
		LinkedList<String>listaEstados = new LinkedList<>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select nome from estados");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					String nome = rs.getString("nome");
					listaEstados.add(nome);
				} while (rs.next());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaEstados;
	}
}
