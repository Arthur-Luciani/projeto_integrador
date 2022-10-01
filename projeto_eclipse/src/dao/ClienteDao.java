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
					.prepareStatement("select cpf from cliente where cpf=?");
			ps.setString(1, cliente.getCpf());
			ResultSet rs = ps.executeQuery();	
			
			if (!rs.next()) {
				ps = conexao
						.prepareStatement("select id from estados where nome=?");
				ps.setString(1, cliente.getEstado());
				rs = ps.executeQuery();
				int idEstado=0;
				if (rs.next()) {
					idEstado =rs.getInt("id");
				}
				
				ps = conexao
						.prepareStatement("insert into endereco (bairro, rua, cidade, cep, id_estado)"
								+ "select ?,?,?,?, id "
								+ "from estados where nome = ?");
				ps.setString(1, cliente.getBairro());
				ps.setString(2, cliente.getRua());
				ps.setString(3, cliente.getCidade());
				ps.setString(4, cliente.getCep());
				ps.setInt(5, idEstado);
				ps.execute();
				
				
				ps = conexao
						.prepareStatement("select id_endereco from endereco");
				rs = ps.executeQuery();
				int idEndereco=0;
				while (rs.next()) {
					idEndereco = rs.getInt("id_endereco");
				}
				
				ps = conexao
						.prepareStatement("inset into cliente (nome, cpf, email, data_nasc, id_endereco)"
								+ "values (?,?,?,?,?)");
				ps.setString(1, cliente.getNome());
				ps.setString(2, cliente.getCpf());
				ps.setString(3, cliente.getEmail());
				ps.setDate(4, java.sql.Date.valueOf(cliente.getDataNascimento()));
				ps.setInt(5, idEndereco);
				ps.execute();
				BD.fechaConexao();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
