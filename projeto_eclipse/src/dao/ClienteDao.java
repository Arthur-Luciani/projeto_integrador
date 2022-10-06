package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Cliente;

public class ClienteDao {
	Connection conexao;
	
	public ClienteDao() {
		conexao = BD.getConexao();
	}
	
	public boolean cadastrarCliente(Cliente cliente) {
		PreparedStatement ps;
		try {
			ps = conexao
					.prepareStatement("insert into endereco (id_endereco, bairro, rua, cidade, cep, id_estado) values (?,?,?,?,?,?);");
			ps.setInt(1, cliente.getId());
			ps.setString(2, cliente.getBairro());
			ps.setString(3, cliente.getRua());
			ps.setString(4, cliente.getCidade());
			ps.setString(5, cliente.getCep());
			ps.setInt(6, cliente.getIdEstado());
			System.out.println(ps);
			ps.execute();
			
			/*
			ps = conexao
					.prepareStatement("insert into cliente (nome, cpf, email, data_de_nasc, id_endereco)"
							+ "values (?,?,?,?,?)");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());
			ps.setString(3, cliente.getEmail());
			ps.setDate(4, java.sql.Date.valueOf(cliente.getDataNascimento()));
			*/
			BD.fechaConexao();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return false;
	}

}
