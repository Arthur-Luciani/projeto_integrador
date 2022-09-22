package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Produto;
import model.Venda;

public class VendaDao extends BD {

	public VendaDao() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void cadastroVenda(Venda venda) {
		try {
			PreparedStatement ps = conexao
					.prepareStatement("insert into Venda (codigoVenda, data, comissaoVendedor, lucro, idCliente, idUsuario)"
							+ "values ( ? , ? , ? , ? , ?, ? )");
			ps.setInt(1, venda.getId());
			ps.setString(2, venda.getData());
			ps.setFloat(3, venda.getComissao());
			ps.setFloat(4, venda.getLucro());
			ps.setInt(5, venda.getIdCliente());
			ps.setInt(6, venda.getIdUsuario());
			ps.execute();
			conexao.close();
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Venda> resgatarVenda(){
		ArrayList<Venda> listaVenda = new ArrayList<Venda>();
		try {
			PreparedStatement ps = conexao
					.prepareStatement("select * from Venda");
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				do {
					Venda venda = new Venda();
					venda.setId(rs.getInt("codigoVenda"));
					venda.setData(rs.getString("data"));
					venda.setComissao(rs.getFloat("comissaoVendedor"));
					venda.setLucro(rs.getFloat("lucro"));
					venda.setIdCliente(rs.getInt("idCliente"));
					venda.setIdUsuario(rs.getInt("idUsuario"));
					
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
		try {
			PreparedStatement ps = conexao
					.prepareStatement("update Venda set data=?, comissaoVendedor=?, lucro=?, idCliente=?, idUsuario=? where codigoVenda=?");
			
			ps.setInt(1, venda.getId());
			ps.setString(2, venda.getData());
			ps.setFloat(3, venda.getComissao());
			ps.setFloat(4, venda.getLucro());
			ps.setInt(5, venda.getIdCliente());
			ps.setInt(6, venda.getIdUsuario());
			ps.execute();
			conexao.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	//falta update para "deletar"

}
