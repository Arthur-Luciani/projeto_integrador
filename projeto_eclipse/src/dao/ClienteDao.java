package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Cliente;
import model.Produto;

public class ClienteDao {
	private Connection conexao;
	
	public ClienteDao() {
		
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		conexao = BD.getConexao();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement("insert into endereco ( bairro, rua, cidade, cep, id_estado) values (?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, cliente.getBairro());
			ps.setString(2, cliente.getRua());
			ps.setString(3, cliente.getCidade());
			ps.setString(4, cliente.getCep());
			ps.setInt(5, cliente.getIdEstado());
			System.out.println(ps);
			ps.execute();
			ResultSet generetedKeys = ps.getGeneratedKeys();
			generetedKeys.next();
			cliente.setId_endereco(generetedKeys.getInt(1));
			
			ps = conexao
					.prepareStatement("insert into cliente (nome, cpf, email, data_de_nasc, id_endereco)"
							+ "values (?,?,?,?,?)");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setDate(4, java.sql.Date.valueOf(cliente.getDataNascimento()));
			ps.setInt(5, cliente.getId_endereco());
			ps.execute();
			System.out.println(ps);
			BD.fechaConexao();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return false;
	}
	public LinkedList<Cliente> resgatarCliente() {
		LinkedList<Cliente> listaCliente = new LinkedList<Cliente>();
		conexao=BD.getConexao();
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from cliente inner join endereco on (cliente.id_endereco = endereco.id_endereco)");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					Cliente cliente = new Cliente();
					cliente.setNome(rs.getString("nome"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setEmail(rs.getString("email"));
					cliente.setDataNascimento(rs.getDate("data_de_nasc").toLocalDate());
					cliente.setId(rs.getInt("id_cliente"));
					cliente.setId_endereco(rs.getInt("id_endereco"));
					cliente.setBairro(rs.getString("bairro"));
					cliente.setCep(rs.getString("cep"));
					cliente.setCidade(rs.getString("cidade"));
					cliente.setRua(rs.getString("rua"));
					cliente.setEstado(rs.getInt("id_estado"));
					
					listaCliente.add(cliente);
				} while (rs.next());
				BD.fechaConexao();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCliente;
	}
	public void deletarCliente(int id) {
		conexao = BD.getConexao();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement("delete from cliente where id_cliente=?");
			ps.setInt(1, id);
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarCliente(Cliente cliente) {
		conexao = BD.getConexao();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("update cliente, endereco set nome=?, cpf=?, email=?, data_de_nasc=?, bairro=?, rua=?, cidade=?, cep=?, id_estado=? "
							+ "where cliente.id_endereco = endereco.id_endereco "
							+ "and cliente.id_cliente=?");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setDate(4, Date.valueOf(cliente.getDataNascimento()));
			ps.setString(5, cliente.getBairro());
			ps.setString(6, cliente.getRua());
			ps.setString(7, cliente.getCidade());
			ps.setString(8, cliente.getCep());
			ps.setInt(9, cliente.getIdEstado());
			ps.setInt(10, cliente.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

}
