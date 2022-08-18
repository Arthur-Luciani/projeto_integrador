package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Usuario;


public class UsuarioDao extends BD{
	
	public UsuarioDao() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verificacao(Usuario usuario) throws SQLException {
		PreparedStatement  ps = conexao.prepareStatement("select login from Usuarios where login=? and senha=?");
		ps.setString(1,usuario.getLogin());
		ps.setString(2,usuario.getSenha());
	    
	    ResultSet rs = ps.executeQuery();
	    if (rs.isFirst()) {
	    	return true;
		} else {
			return false;
		}
	    
	}
	
}
