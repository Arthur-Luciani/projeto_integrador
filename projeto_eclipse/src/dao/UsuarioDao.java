package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Usuario;

public class UsuarioDao {

	

	public UsuarioDao() {}

	public boolean cadastro(Usuario novoUsuario) {
		try {
			Connection conexao = BD.getConexao();
			PreparedStatement ps = conexao.prepareStatement("select login from usuarios where login like ? ");
			ps.setString(1, novoUsuario.getLogin());
			ResultSet rs = ps.executeQuery();

			if (rs.next() != true) {
				ps = conexao.prepareStatement(
						"insert into Usuarios (login, nome, senha, data_nascimento, cpf, idade, permissao)"
								+ "values ( ? , ? , ? , ? , ? , ? , ? )");
				ps.setString(1, novoUsuario.getLogin());
				ps.setString(2, novoUsuario.getNome());
				ps.setString(3, novoUsuario.getSenha());
				ps.setDate(4, Date.valueOf(novoUsuario.getDataNascimento()));
				ps.setString(5, novoUsuario.getCpfUsuario());
				ps.setInt(6, novoUsuario.getIdade());
				ps.setBoolean(7, novoUsuario.isPermissao());
				
				System.out.println(ps);
				
				ps.execute();
				BD.fechaConexao();
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return false;
	}

	public Usuario verificacao(Usuario usuario) {
		try {
			Connection conexao = BD.getConexao();
			PreparedStatement ps = conexao
					.prepareStatement("select * from usuarios where login like ? and senha like ?");
			ps.setString(1, usuario.getLogin());
			ps.setString(2, usuario.getSenha());

			ResultSet rs = ps.executeQuery();
			Usuario usuarioLogado = new Usuario();

			while (rs.next()) {
				String login = rs.getString("login");
				String nome = rs.getString("nome");
				String senha = rs.getString("senha");
				boolean permissao = rs.getBoolean("permissao");
				int idUsuario = rs.getInt("id_usuario");
				LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
				String cpf = rs.getString("cpf");
				int idade = rs.getInt("idade");

				usuarioLogado.setLogin(login);
				usuarioLogado.setNome(nome);
				usuarioLogado.setSenha(senha);
				usuarioLogado.setPermissao(permissao);
				usuarioLogado.setIdUsuario(idUsuario);
				usuarioLogado.setDataNascimento(dataNascimento);
				usuarioLogado.setCpfUsuario(cpf);
				usuarioLogado.setIdade(idade);
				BD.fechaConexao();
				return usuarioLogado;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public LinkedList<Usuario> resgatarUsuarios() {
		LinkedList<Usuario> listaUsuarios = new LinkedList<>();
		Connection conexao = BD.getConexao();
		try {
			
			PreparedStatement ps = conexao.prepareStatement("select * from usuarios");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					
					String login = rs.getString("login");
					String nome = rs.getString("nome");
					String senha = rs.getString("senha");
					boolean permissao = rs.getBoolean("permissao");
					int idUsuario = rs.getInt("id_usuario");
					LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
					String cpf = rs.getString("cpf");
					int idade = rs.getInt("idade");
					Usuario usuario = new Usuario(login, nome, senha, dataNascimento, cpf);

					
					listaUsuarios.add(usuario);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return listaUsuarios;
	}
	
	public void deletarUsuario(int id) {
		Connection connection = BD.getConexao();
		try {
			PreparedStatement ps= connection
					.prepareStatement("delete from usuarios where id_usuario = ?");
			ps.setInt(1, id);
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public boolean atualizarUsuario(Usuario usuario) {
		Connection connection = BD.getConexao();
		try {
			PreparedStatement ps = connection
					.prepareStatement("select login from usuarios where login like ? ");
			ps.setString(1, usuario.getLogin());
			ResultSet rs = ps.executeQuery();
			
			if (rs.next() != true) {
				ps = connection
						.prepareStatement("update usuarios "
								+ "set login=?, nome=?, senha=?, data_nascimento=?, cpf=?, idade=? , permissao=? "
								+ "where id = ?");
				ps.setString(1, usuario.getLogin());
				ps.setString(2, usuario.getNome());
				ps.setString(3, usuario.getSenha());
				ps.setDate(4, Date.valueOf(usuario.getDataNascimento()));
				ps.setString(5, usuario.getCpfUsuario());
				ps.setInt(6, usuario.getIdade());
				ps.setBoolean(7, usuario.isPermissao());
				ps.setInt(8, usuario.getIdUsuario());
				ps.execute();
				BD.fechaConexao();
				return true;
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		BD.fechaConexao();
		return false;
	}
	
}
