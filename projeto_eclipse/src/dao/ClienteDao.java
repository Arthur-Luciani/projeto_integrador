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
					.prepareStatement("insert into mydb.endereco (bairro, rua, cidade, cep, id_estado) "
							+ "values ('fortaleza','cacilda','blumenau','23432', (select id from mydb.estados where nome like 'Acre') );");
			
			
			/*
			ps.setString(1, cliente.getBairro());
			ps.setString(2, cliente.getRua());
			ps.setString(3, cliente.getCidade());
			ps.setString(4, cliente.getCep());
			ps.setString(5, cliente.getEstado());
			
			*/
			ps.execute();
			
			
			
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
