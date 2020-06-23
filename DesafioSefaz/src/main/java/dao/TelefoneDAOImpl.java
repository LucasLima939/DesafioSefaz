package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import entidade.Telefone;
import util.JdbcUtil;

public class TelefoneDAOImpl implements TelefoneDAO {	

	@Override
	public void incluir(Telefone telefone) {
		
		String sql = "INSERT INTO telefone(numero, ddd, tipo, usuario_email) " + 
				"  values(?, ?, ?, ?)";

		Connection conexao;
		try {
			conexao = JdbcUtil.getConnection();
			
			PreparedStatement ps = conexao.prepareStatement(sql);
			
			ps.setString(1, telefone.getNumero());
			ps.setInt(2, telefone.getDdd());
			ps.setString(3, telefone.getTipo());
			ps.setString(4, telefone.getUsuario().getEmail());

			ps.execute();
			
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void alterar(Telefone telefone) {

		String sql = "UPDATE TELEFONE SET ddd = ?, tipo = ? where numero = ?";

		Connection connection;
		try {
			connection = JdbcUtil.getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(3, telefone.getNumero());
			ps.setInt(1, telefone.getDdd());
			ps.setString(2, telefone.getTipo());


			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remover(Telefone telefone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Telefone> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
