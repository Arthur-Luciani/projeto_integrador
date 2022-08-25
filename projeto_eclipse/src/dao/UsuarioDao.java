package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;

public class UsuarioDao extends BD {

	public UsuarioDao() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario verificacao(Usuario usuario) throws SQLException {
		PreparedStatement ps = conexao.prepareStatement("select * from Usuarios where login=? and senha=?");
		ps.setString(1, usuario.getLogin());
		ps.setString(2, usuario.getSenha());

		ResultSet rs = ps.executeQuery();
		if (rs.isFirst()) {
			Usuario usuarioLogado = null; // pegar os dados da consulta
					
					int id = rs.getInt("id");
					String login = rs.getString("login");
					
					String senha = rs.getString("senha");
					
					usuarioLogado.setPermissao(rs.getBoolean("permissao"));
			return usuarioLogado;
		} else {
			return null;
		}

	}

}
