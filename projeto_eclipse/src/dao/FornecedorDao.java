package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDao extends BD{

	public FornecedorDao() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<String> nomeFornecedores() throws SQLException {
		ArrayList<String> listaNomes = new ArrayList<String>();
		PreparedStatement ps = conexao
				.prepareStatement("select nome from fornecedor");
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			do {
				String nomeFornecedor = rs.getString("nome");
				listaNomes.add(nomeFornecedor);
			} while (rs.next());
			
		}
		return listaNomes;
	}

}
