package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Fornecedores;
import model.Produto;

public class FornecedorDao {

	private Connection conexao = BD.getConexao();

	public FornecedorDao() {}
	
	public void cadastroFornecedor(Fornecedores fornecedores) {
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"insert into fornecedores (nome_empresa, telefone,email,cnpj)"
							+ "values ( ? , ? , ? , ? )");
			
			ps.setString(1, fornecedores.getCnpj());
			ps.setString(2, fornecedores.getEmail());
			ps.setString(3, fornecedores.getNomeEmpresa());
			ps.setString(4, fornecedores.getTelefone());
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}
	

	public ArrayList<Fornecedores> resgatarFornecedores()  {
		ArrayList<Fornecedores> listaFornecedores = new ArrayList<Fornecedores>();
		try {
			PreparedStatement ps = conexao.prepareStatement("select nome_empresa from fornecedor");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				do {
					Fornecedores fornecedores = new Fornecedores();
					fornecedores.setCnpj(rs.getString("cnpj"));
					fornecedores.setEmail(rs.getString("email"));
					fornecedores.setNomeEmpresa(rs.getString("nome_empresa"));
					fornecedores.setTelefone(rs.getString("telefone"));
					
					listaFornecedores.add(fornecedores);

				} while (rs.next());
				BD.fechaConexao();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaFornecedores;
	}
	
	public void atualizarFornecedor(Fornecedores fornecedores) {
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"update fornecedor set cnpj=?, email=?, telefone=?, nome_empresa=? where cnjp=?");

			ps.setString(1, fornecedores.getCnpj() );
			ps.setString(2, fornecedores.getEmail());
			ps.setString(3, fornecedores.getNomeEmpresa());
			ps.setString(4, fornecedores.getTelefone());
			ps.executeUpdate();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void deletarFornecedor(String cnpj) {
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
