package dao;

import java.sql.Date;
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
	
	public void cadastro(Usuario novoUsuario) {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("insert into Usuarios (login, nome, senha, idUsuario, data_de_nascUsuario, cpfUsuario, idade)"
							+ "values ( ? , ? , ? , ? , ? , ? , ?)");
			ps.setString(1, novoUsuario.getLogin());
			ps.setString(2, novoUsuario.getNome());
			ps.setString(3, novoUsuario.getSenha());
			ps.setInt(4, 1);
			ps.setDate(5, novoUsuario.getDatanascimentoDB());
			ps.setString(6, novoUsuario.getCpfUsuario());
			ps.setInt(7, novoUsuario.getIdade());
			ps.execute();
			conexao.close();
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
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
				
				String login = rs.getString("login");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				boolean permissao = rs.getBoolean("permissao");
				int idUsuario = rs.getInt("id_usuario");
				LocalDate dataNascimento = rs.getDate("data_de_nascUsuario").toLocalDate();
				String cpf = rs.getString("cpfUsuario");
				int idade = rs.getInt("idade");
				
				usuarioLogado.setLogin(login);
				usuarioLogado.setNome(nome);
				usuarioLogado.setSenha(senha);
				usuarioLogado.setPermissao(permissao);
				usuarioLogado.setIdUsuario(idUsuario);
				usuarioLogado.setDataNascimento(dataNascimento);
				usuarioLogado.setCpfUsuario(cpf);
				usuarioLogado.setIdade(idade);
				
				return usuarioLogado;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
