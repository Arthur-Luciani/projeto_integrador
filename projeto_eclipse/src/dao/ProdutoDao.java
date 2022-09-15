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
	
		ArrayList listaProduto = new ArrayList();
	
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
				
				return listaProduto;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
}
