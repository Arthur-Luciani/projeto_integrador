package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Produto;

public class ProdutoDao extends BD{

	public ProdutoDao() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Produto lista(Produto produto) {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from Produto where codigoProduto like ? and nome like ? and precoProduto like ? and quant_no_estoque like ?");

			ps.setString(1, produto.getNome());

			ps.setFloat(2, produto.getPreco());
			
			ps.setInt(3, produto.getQuantEstoque());

			ResultSet rs = ps.executeQuery();
			Produto produtoCadastrado = new Produto(null, 0, 0); // pegar os dados da consulta

			//
			rs.next();
			
			int id = rs.getInt("id_produto");
			String nome = rs.getString("nome");
			float preco = rs.getFloat("preco");
			int quantEstoque = rs.getInt("quant_no_estoque");
			
			produtoCadastrado.setId(id);
			produtoCadastrado.setPreco(preco);;
			produtoCadastrado.setNome(nome);
			produtoCadastrado.setQuantEstoque(quantEstoque);;
			return produtoCadastrado;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
}
