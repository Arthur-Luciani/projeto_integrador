package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AtualizacaoProduto;
import model.Produto;

public class ProdutoDao {

	private Connection conexao = BD.getConexao();

	public ProdutoDao() throws SQLException {}
	
	public void cadastroProduto(Produto produto) {
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"insert into produto (id_produto, nome_produto, preco_produto, estoque, nome_empresa)"
							+ "values ( ? , ? , ? , ? , ? )");
			ps.setInt(1, produto.getId());
			ps.setString(2, produto.getNome());
			ps.setFloat(3, produto.getPreco());
			ps.setInt(4, produto.getQuantEstoque());
			ps.setString(5, produto.getNomeFornecedor());
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public ArrayList<Produto> resgatarProdutos() {
		ArrayList<Produto> listaProduto = new ArrayList<Produto>();
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from produto");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					Produto produto = new Produto();
					produto.setId(rs.getInt("id_produto"));
					produto.setPreco(rs.getFloat("preco_produto"));
					produto.setNome(rs.getString("nome_produto"));
					produto.setQuantEstoque(rs.getInt("estoque"));
					produto.setNomeFornecedor("nome_empresa");

					listaProduto.add(produto);
				} while (rs.next());
				BD.fechaConexao();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaProduto;
	}

	public void atualizarProduto(Produto produto) {
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"update Produto set nome_produto=?, preco_produto=?, estoque=?, nome_empresa=? where id_produto=?");

			ps.setString(1, produto.getNome());
			ps.setFloat(2, produto.getPreco());
			ps.setInt(3, produto.getQuantEstoque());
			ps.setString(4, produto.getNomeFornecedor());
			ps.setInt(5, produto.getId());
			ps.executeUpdate();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void deletarProduto(int id) {
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement("delete from produto where id_produto=?");
			ps.setInt(1, id);
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ArrayList<AtualizacaoProduto> historicoPreco(int id) {
		ArrayList<AtualizacaoProduto> listaAtualizacoes = new ArrayList<AtualizacaoProduto>();
		PreparedStatement ps;
		try {
			ps = conexao
					.prepareStatement("select * from historico_produto where id_produto=? order by id_historico_produto desc");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next()) {
				do {
					AtualizacaoProduto a= new AtualizacaoProduto();
					a.setDataAtualizacao(rs.getDate("data_atualizacao"));
					a.setPreco(rs.getFloat("preco_novo"));
					listaAtualizacoes.add(a);
				} while (rs.next());
			}
			return listaAtualizacoes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
