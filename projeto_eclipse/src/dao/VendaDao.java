package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Venda;

public class VendaDao {

	public VendaDao(){}
	
	public void cadastroVenda(Venda venda) {
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
			
			ps = conexao.prepareStatement("insert into venda (data_venda, comissao_vendedor, lucro, id_cliente, id_usuario)"
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
					venda.setIdCliente(rs.getInt("idCliente"));
					venda.setIdVendedor(rs.getInt("idUsuario"));
					
					listaVenda.add(venda);
				} while (rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaVenda;
	}
	
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
	}

}
