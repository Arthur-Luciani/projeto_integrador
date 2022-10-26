package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Cliente;
import model.Produto;

public class ClienteDao {
	Connection conexao;
	
	public ClienteDao() {
		conexao = BD.getConexao();
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
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
	
	public LinkedList<Cliente> resgatarClientes() {
		LinkedList<Cliente> listaClientes = new LinkedList<>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from cliente");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					Cliente cliente = new Cliente();
					cliente.setNome(rs.getString("nome"));
					cliente.setCpf(rs.getString("cpf"));
					cliente.setEmail(rs.getString("email"));
					cliente.setId(rs.getInt("id_cliente"));

					listaClientes.add(cliente);
				} while (rs.next());
				BD.fechaConexao();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaClientes;
	}

}
