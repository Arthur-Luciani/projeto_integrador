package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.AtualizacaoProduto;
import model.Fornecedores;
import model.Produto;

public class ProdutoDao {

	private Connection conexao;

	public ProdutoDao() {}
	
	public void cadastroProduto(Produto produto) {
		conexao = BD.getConexao();
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"insert into produto (nome_produto, preco_produto, estoque, cnpj_fornecedor) "
							+ "values ( ? , ? , ? , ? )");
			//ps.setInt(1, produto.getId());
			ps.setString(1, produto.getNome());
			ps.setFloat(2, produto.getPreco());
			ps.setInt(3, produto.getQuantEstoque());
			ps.setString(4, produto.getFornecedor().getCnpj());
			
			System.out.println(ps);
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public ArrayList<Produto> resgatarProdutos() {
		conexao = BD.getConexao();
		ArrayList<Produto> listaProduto = new ArrayList<Produto>();
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from produto "
					+ "inner join fornecedor on produto.cnpj_fornecedor = fornecedor.cnpj "
					+ "inner join endereco on (fornecedor.id_endereco=endereco.id_endereco) "
					+ "order by id_produto asc");
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					Produto produto = new Produto();
					Fornecedores fornecedores = new Fornecedores();
					produto.setId(rs.getInt("id_produto"));
					produto.setPreco(rs.getFloat("preco_produto"));
					produto.setNome(rs.getString("nome_produto"));
					produto.setQuantEstoque(rs.getInt("estoque"));				
					
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
					produto.setFornecedor(fornecedores);
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
		conexao = BD.getConexao();
		try {
			PreparedStatement ps = conexao.prepareStatement(
					"update Produto set nome_produto=?, preco_produto=?, estoque=?, cnpj_fornecedor=? where id_produto=?");

			ps.setString(1, produto.getNome());
			ps.setFloat(2, produto.getPreco());
			ps.setInt(3, produto.getQuantEstoque());
			ps.setString(4, produto.getFornecedor().getCnpj());
			ps.setInt(5, produto.getId());
			
			System.out.println(ps);
			ps.executeUpdate();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void deletarProduto(int id) {
		conexao = BD.getConexao();
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
		conexao = BD.getConexao();
		ArrayList<AtualizacaoProduto> listaAtualizacoes = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = conexao
					.prepareStatement("select data_atualizacao, preco_novo, preco_antigo, (((preco_novo/preco_antigo)*100)-100) as diferenca "
							+ "from historico_produto "
							+ "where id_produto=? "
							+ "order by id_historico_produto desc");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next()) {
				do {
					AtualizacaoProduto a= new AtualizacaoProduto();
					a.setDataAtualizacao(rs.getDate("data_atualizacao"));
					a.setPreco(rs.getFloat("preco_novo"));
					a.setDiferencaPorcent(rs.getDouble("diferenca"));
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
	public ArrayList<Produto> pesquisaProduto(String nome) {
		conexao = BD.getConexao();
		ArrayList<Produto> listaPesquisa = new ArrayList<>();
		
		try {
			PreparedStatement ps;
			if (!nome.equals("")) {
				ps = conexao.prepareStatement("select * from produto "
						+ "inner join fornecedor on produto.cnpj_fornecedor = fornecedor.cnpj "
						+ "inner join endereco on (fornecedor.id_endereco=endereco.id_endereco) "
						+ "where produto.nome_produto like '%' ? '%' "
						+ "order by id_produto asc");
				ps.setString(1, nome);
				System.out.println(ps);
			} else {
				ps = conexao.prepareStatement("select * from produto "
						+ "inner join fornecedor on produto.cnpj_fornecedor = fornecedor.cnpj "
						+ "inner join endereco on (fornecedor.id_endereco=endereco.id_endereco) "
						+ "order by id_produto asc");
			}
			
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				do {
					Produto produto = new Produto();
					Fornecedores fornecedores = new Fornecedores();
					produto.setId(rs.getInt("id_produto"));
					produto.setPreco(rs.getFloat("preco_produto"));
					produto.setNome(rs.getString("nome_produto"));
					produto.setQuantEstoque(rs.getInt("estoque"));				
					
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
					produto.setFornecedor(fornecedores);
					listaPesquisa.add(produto);
					
				} while (rs.next());
				BD.fechaConexao();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPesquisa;
	}
	

}
