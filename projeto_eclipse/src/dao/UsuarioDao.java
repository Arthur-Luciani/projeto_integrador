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

	public Usuario verificacao(Usuario usuario) {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from usuarios where login like ? and senha like ?");

			ps.setString(1, usuario.getLogin());

			ps.setString(2, usuario.getSenha());

			ResultSet rs = ps.executeQuery();
			Usuario usuarioLogado = new Usuario(); // pegar os dados da consulta

			//
			rs.next();
			
			int id = rs.getInt("id_usuario");
			String login = rs.getString("login");
			String nome = rs.getString("nome");
			String senha = rs.getString("senha");
			
			usuarioLogado.setId(id);
			usuarioLogado.setLogin(login);
			usuarioLogado.setNome(nome);
			usuarioLogado.setSenha(senha);
			usuarioLogado.setPermissao(rs.getBoolean("permissao"));
			return usuarioLogado;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
