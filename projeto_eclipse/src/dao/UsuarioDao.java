package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

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

			while (rs.next()) {
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha =rs.getString("senha");
				String cep =rs.getString("cep");
				String cpf = rs.getString("cpf");
				LocalDate dataNascimento = rs.getDate("data_nasUsuario").toLocalDate() ;
				int id = rs.getInt("id_usuario");
				boolean permissao = rs.getBoolean("permissao");
				
				usuarioLogado.setNome(nome);
				usuarioLogado.setLogin(login);
				usuarioLogado.setSenha(senha);
				usuarioLogado.setCep(cep);
				usuarioLogado.setCpf(cpf);
				usuarioLogado.setDataNascimento(dataNascimento);
				usuarioLogado.setId(id);
				usuarioLogado.setPermissao(permissao);
				return usuarioLogado;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
