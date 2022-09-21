package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;

public class ProdutoDao extends BD{

	public ProdutoDao() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void cadastroProduto(Produto produto) {
		
		try {
			PreparedStatement ps = conexao
					.prepareStatement("insert into Produto (codigoProduto, nome, precoProduto, quant_no_estoque, Fornecedor_nome)"
							+ "values ( ? , ? , ? , ? , ? )");
			ps.setInt(1, produto.getId());
			ps.setString(2, produto.getNome());
			ps.setFloat(3, produto.getPreco());
			ps.setInt(4, produto.getQuantEstoque());
			ps.setString(5, produto.getNomeFornecedor());
			ps.execute();
			conexao.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}
	
	public ArrayList<Produto> resgatarProdutos(){
		ArrayList<Produto> listaProduto = new ArrayList<Produto>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from Produto");
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				do {
					Produto produto = new Produto();
					produto.setId(rs.getInt("codigoProduto"));
					produto.setPreco(rs.getFloat("precoProduto"));
					produto.setNome(rs.getString("nome"));
					produto.setQuantEstoque(rs.getInt("quant_no_estoque"));
					
					listaProduto.add(produto);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProduto;
	}
	
	public void atualizarProduto(Produto produto) {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("update Produto set nome=?, precoProduto=?, quant_no_estoque=?, Fornecedor_nome=? where codigoProduto=?");
			
			ps.setString(1, produto.getNome());
			ps.setFloat(2, produto.getPreco());
			ps.setInt(3, produto.getQuantEstoque());
			ps.setString(4, produto.getNomeFornecedor());
			ps.setInt(5, produto.getId());
			ps.executeUpdate();
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	public void deletarProduto(int id) throws SQLException {
		PreparedStatement ps = conexao
				.prepareStatement("delete from Produto where codigoProduto=?");
		ps.setInt(1, id);
		ps.execute();
		conexao.close();
	}
}
	
