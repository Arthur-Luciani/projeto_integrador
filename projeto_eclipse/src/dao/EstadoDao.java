package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.cj.protocol.Message;

import model.Estado;

public class EstadoDao {
	public EstadoDao() {
	}
	public LinkedList<Estado> resgatarEstados(){
		Connection conexao = BD.getConexao();
		LinkedList<Estado>listaEstados = new LinkedList<>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from estados");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					Estado estado = new Estado();
					
					estado.setIdEstado(rs.getInt("id"));
					estado.setNomeEstado(rs.getString("nome"));
					estado.setUf(rs.getString("uf"));
					estado.setIbge(rs.getInt("ibge"));
					estado.setPais(rs.getInt("pais"));
					estado.setDdd(rs.getString("ddd"));
					listaEstados.add(estado);
				} while (rs.next());
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaEstados;
	}
}
