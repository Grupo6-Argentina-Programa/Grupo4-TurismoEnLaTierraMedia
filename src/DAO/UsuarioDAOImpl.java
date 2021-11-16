package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import DAO.MissingDataException;
import jdbc.ConnectionProvider;
import modelos.Usuario;

@SuppressWarnings("unused")
public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO Users (username, password, coins, "
					+ "timeHs, positionX, positionY) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsuario());
			statement.setString(2, user.getContrase�a());
			statement.setDouble(3, user.getDineroDisponible());
			statement.setDouble(4, user.getTiempoDisponible());
			statement.setInt(5, user.getPosicionX());
			statement.setInt(6, user.getPosicionY());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Usuario user) {
		try {
			String sql = "UPDATE Users SET username = ?, password = ?, coins = ?,"
					+ " timeHs = ?, positionX = ?, positionY = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getUsuario());
			statement.setString(2, user.getContrase�a());
			statement.setDouble(3, user.getDineroDisponible());
			statement.setDouble(4, user.getTiempoDisponible());
			statement.setInt(5, user.getPosicionX());
			statement.setInt(6, user.getPosicionY());
			statement.setInt(7, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM Users WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Usuario findById(int id) {
		try {
			String sql = "SELECT * FROM Users WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUsuarios(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Usuario findByUsername(String username) {
		try {
			String sql = "SELECT * FROM Users WHERE username = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUsuarios(resultados);
			}

			return user;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int findId(String username, String password) {
		try {
			String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUsuarios(resultados);
			}

			return user.getId();
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int findMaxID() {
		try {
			String sql = "SELECT MAX(id) AS id FROM Users";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			return resultados.getInt("id");
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM Users";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUsuarios(resultados));
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Users";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Usuario toUsuarios(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getInt(1), resultados.getString(2), resultados.getString(3),
				resultados.getDouble(4), resultados.getDouble(5), resultados.getInt(6), resultados.getInt(7));
	}

	@Override
	public char[] update(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}