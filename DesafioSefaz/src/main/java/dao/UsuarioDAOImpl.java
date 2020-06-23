package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidade.Telefone;
import entidade.Usuario;
import util.JdbcUtil;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public void incluir(Usuario usuario) {

		String sql = "insert into USUARIO (email, nome, senha) values (?, ?, ?)";

		Connection connection;
		try {
			connection = JdbcUtil.getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setString(1, usuario.getEmail());
			ps.setString(2, usuario.getNome());
			ps.setString(3, usuario.getSenha());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alterar(Usuario usuario) {

		String sql = "UPDATE USUARIO SET nome = ?, senha = ? where email = ?";

		Connection connection;
		try {
			connection = JdbcUtil.getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(3, usuario.getEmail());
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public void remover(Usuario usuario) {

		String sql = "DELETE FROM USUARIO WHERE email = ?";

		Connection connection;
		try {
			connection = JdbcUtil.getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, usuario.getEmail());

			ps.execute();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Usuario> listarTodos() {

		String sql = "select U.EMAIL, U.NOME, U.SENHA, T.DDD, T.TIPO, T.NUMERO" +
				" from USUARIO U, TELEFONE T" +
				" where T.USUARIO_EMAIL = U.EMAIL";

		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		List<Telefone> listaTelefones = new ArrayList<Telefone>();

		Connection connection;
		try {
			connection = JdbcUtil.getConnection();

			PreparedStatement ps = connection.prepareStatement(sql);

			ResultSet res = ps.executeQuery();

			while (res.next()) {

				Usuario usuario = new Usuario();
				usuario.setEmail(res.getString("EMAIL"));
				usuario.setNome(res.getString("NOME"));
				usuario.setSenha(res.getString("SENHA"));
				
				Telefone telefone = new Telefone();
				telefone.setDdd(res.getInt("DDD"));
				telefone.setNumero(res.getString("NUMERO"));
				telefone.setTipo(res.getString("TIPO"));
				
				listaTelefones.add(telefone);
				usuario.setTelefones(listaTelefones);

				listaUsuarios.add(usuario);
			}

			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUsuarios;

	}

}
