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
	
	ArrayList<Produto> listaProduto = new ArrayList<Produto>();
	public ArrayList resgatarProdutos() {		
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from Produto ");

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("codigoProduto");
				String nome = rs.getString("nome");
				float preco = rs.getFloat("precoProduto");
				int quantEstoque = rs.getInt("quant_no_estoque");
				
				Produto produtoCadastrado = new Produto(null, 0, 0);
				
				produtoCadastrado.setId(id);
				produtoCadastrado.setPreco(preco);
				produtoCadastrado.setNome(nome);
				produtoCadastrado.setQuantEstoque(quantEstoque);
				
				listaProduto.add(produtoCadastrado);
				
				System.out.println(listaProduto.size());
				
				return listaProduto;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return null;

	}
}
