package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import model.Cliente;
import model.ProdutoVenda;
import model.Usuario;
import model.Venda;

public class VendaDao {

	public VendaDao(){}
	
	public boolean cadastroVenda(Venda venda, ArrayList<ProdutoVenda>listaProdutosVendidos) {
		Connection conexao = BD.getConexao();
		Cliente cliente = venda.getCliente();
		Usuario usuario = venda.getVendedor();

		try {
			PreparedStatement ps = conexao
					.prepareStatement("insert into venda (data_venda, comissao_vendedor, lucro, id_cliente, id_usuario)"
							+ "values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setDate(1,Date.valueOf(venda.getDataVenda()));
			ps.setFloat(2, venda.getComissaoVendedor());
			ps.setFloat(3, venda.getLucro());
			ps.setInt(4, cliente.getId());
			ps.setInt(5, usuario.getIdUsuario());
			
			System.out.println(ps);
			
			ps.execute();
			ResultSet generetedKeys = ps.getGeneratedKeys();
			
			if (generetedKeys.next()) {
				venda.setIdVenda(generetedKeys.getInt(1));
			}
			
			for (ProdutoVenda produtoVenda : listaProdutosVendidos) {
				produtoVenda.setQuantEstoque(produtoVenda.getQuantEstoque()-produtoVenda.getQuantidade());
				
				int idHistorico=0;
				ps = conexao
						.prepareStatement("select id_historico_produto from historico_produto where id_produto=? order by id_historico_produto desc");
				ps.setInt(1, produtoVenda.getId());
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					idHistorico = rs.getInt("id_historico_produto");
					System.out.println(idHistorico);
				}
				ps = conexao
						.prepareStatement("insert into venda_produtos (id_venda, quantidade_produto, id_historico_produto)"
								+ "values (?,?,?) ");
				ps.setInt(1, venda.getIdVenda());
				ps.setInt(2, produtoVenda.getQuantidade());
				ps.setInt(3, idHistorico);
				
				System.out.println(ps);
				
				ps.execute();
				
				
				ps = conexao
						.prepareStatement("update Produto "
								+ "set estoque = ? "
								+ "where id_produto = ?");
				ps.setInt(1, produtoVenda.getQuantEstoque());
				ps.setInt(2, produtoVenda.getId());
				ps.execute();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public ArrayList<Venda> resgatarVendas(){
		Connection conexao = BD.getConexao();
		ArrayList<Venda> listaVenda = new ArrayList<Venda>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from Venda");
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				do {
					Venda venda = new Venda();
					venda.setIdVenda(rs.getInt("codigoVenda"));
					venda.setDataVenda(rs.getDate("data_venda").toLocalDate());
					venda.setComissaoVendedor(rs.getFloat("comissaoVendedor"));
					venda.setLucro(rs.getFloat("lucro"));
					
					
					//falta fazer a parte de vendedor e de cliente
					
					listaVenda.add(venda);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaVenda;
	}
	public ArrayList<Venda> resgatarComissao(Date entrada, Date saida){
		Connection conexao = BD.getConexao();
		ArrayList<Venda> listaComissao = new ArrayList<Venda>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select usuarios.nome As nome_usuario, SUM(venda.comissao_vendedor) AS total, COUNT(venda.id_usuario) AS total2 from venda inner join usuarios on venda.id_usuario = usuarios.id_usuario where mydb.venda.data_venda between ? and ? group by venda.id_usuario");
			ps.setDate(1, entrada);
			ps.setDate(2, saida);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				do {
					Venda venda = new Venda();
					Usuario vendedor = new Usuario();
					venda.setComissaoVendedor(rs.getFloat("total"));
					vendedor.setNome(rs.getString("nome_usuario"));
					venda.setVendas_Vendedor(rs.getInt("total2"));
					
					venda.setVendedor(vendedor);
					listaComissao.add(venda);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaComissao;
	}
	
	/*
	public void atualizarVenda(Venda venda){		
		Connection conexao = BD.getConexao();
		try {
			int idCliente, idVendedor;
			
			PreparedStatement ps = conexao
					.prepareStatement("select id_cliente from cliente where nome = ?");
			ps.setString(1, venda.getNomeCliente());
			ResultSet rs = ps.executeQuery();
			do {
				idCliente = rs.getInt("id_cliente");
			} while (rs.next());
			
			ps = conexao.prepareStatement("select id_usuario from usuarios where nome = ?");
			ps.setString(1, venda.getNomeVendedor());
			
			do {
				idVendedor = rs.getInt("id_usuario");
			} while (rs.next());
			
			ps = conexao.prepareStatement("update venda  set data_venda=? , comissao_vendedor=?, lucro=?, id_cliente=?, id_usuario=?)"
					+ "values (? ,? , ?, ?, ?)");
			ps.setDate(1, Date.valueOf(venda.getDataVenda()));
			ps.setFloat(2, venda.getComissaoVendedor());
			ps.setFloat(3, venda.getLucro());
			ps.setInt(4, idCliente);
			ps.setInt(5, idVendedor);
			ps.execute();
			BD.fechaConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
