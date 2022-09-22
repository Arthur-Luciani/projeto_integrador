package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FornecedorDao {

	private Connection conexao = BD.getConexao();

	public FornecedorDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<String> nomeFornecedores() throws SQLException {

		conexao = BD.getConexao();
		ArrayList<String> listaNomes = new ArrayList<String>();
		PreparedStatement ps = conexao.prepareStatement("select nome from fornecedor");
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
