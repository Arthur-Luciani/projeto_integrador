package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import model.Fornecedores;
import model.Produto;

public class FornecedorDao {

	private Connection conexao;

	public FornecedorDao() {}
	
	public void cadastroFornecedor(Fornecedores fornecedores) {
		conexao = BD.getConexao();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement("insert into endereco ( bairro, rua, cidade, cep, id_estado) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, fornecedores.getBairro());
			ps.setString(2, fornecedores.getRua());
			ps.setString(3, fornecedores.getCidade());
			ps.setString(4, fornecedores.getCep());
			ps.setInt(5, fornecedores.getIdEstado());
			System.out.println(ps);
			ps.execute();
			ResultSet generetedKeys = ps.getGeneratedKeys();
			generetedKeys.next();
			fornecedores.setIdEndereco(generetedKeys.getInt(1));		
			
			ps = conexao.prepareStatement(
					"insert into fornecedor (nome_empresa, telefone,email, id_endereco,cnpj)"
							+ "values ( ? , ? , ? , ? , ? )");
			
			ps.setString(1, fornecedores.getNome());
			ps.setString(2, fornecedores.getTelefone());
			ps.setString(3, fornecedores.getEmail());
			ps.setInt(4, fornecedores.getIdEndereco());
			ps.setString(5, fornecedores.getCnpj());
			System.out.println(ps);
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}	

	public ArrayList<Fornecedores> resgatarFornecedores()  {
		conexao = BD.getConexao();
		ArrayList<Fornecedores> listaFornecedores = new ArrayList<Fornecedores>();
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from fornecedor inner join endereco on (fornecedor.id_endereco=endereco.id_endereco)");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					Fornecedores fornecedores = new Fornecedores();
					fornecedores.setNome(rs.getString("nome_empresa"));
					fornecedores.setTelefone(rs.getString("telefone"));
					fornecedores.setEmail(rs.getString("email"));
					fornecedores.setIdEndereco(rs.getInt("id_endereco"));
					fornecedores.setCnpj(rs.getString("cnpj"));
					fornecedores.setBairro(rs.getString("bairro"));
					fornecedores.setRua(rs.getString("rua"));
					fornecedores.setCidade(rs.getString("cidade"));
					fornecedores.setCep(rs.getString("cep"));
					fornecedores.setEstado(rs.getInt("id_estado"));
					listaFornecedores.add(fornecedores);

				} while (rs.next());
				BD.fechaConexao();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return listaFornecedores;
	}
	
	public void atualizarFornecedor(Fornecedores fornecedores) {
		
		conexao = BD.getConexao();
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"update fornecedor, endereco set "
					+ "nome_empresa=?, telefone=?,email=?, bairro=?, rua=?, cidade=?, cep=?, id_estado=? "
					+ "where fornecedor.cnpj=? and fornecedor.id_endereco=?");

			ps.setString(1, fornecedores.getNome());
			ps.setString(2, fornecedores.getTelefone());
			ps.setString(3, fornecedores.getEmail());
			ps.setString(4, fornecedores.getBairro());
			ps.setString(5, fornecedores.getRua());
			ps.setString(6, fornecedores.getCidade());
			ps.setString(7, fornecedores.getCep());
			ps.setInt(8, fornecedores.getIdEstado());
			ps.setString(9, fornecedores.getCnpj());
			ps.setInt(10, fornecedores.getIdEndereco());
			
			System.out.println(ps);
			ps.executeUpdate();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void deletarFornecedor(String cnpj) {
		conexao = BD.getConexao();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement("delete from fornecedor where cnpj=?");
			ps.setString(1, cnpj);
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
