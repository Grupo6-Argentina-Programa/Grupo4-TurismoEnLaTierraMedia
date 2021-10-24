package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import main.java.jdbc.ConnectionProvider;
import main.java.usuarios.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO Usuario (usuario, contraseña, dinero, tiempo, costoTotal, "
					+" posicionX, posicionY) VALUES (?,?,?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, user.getDNI());
			statement.setString(2, user.getContraseña().toString());
			statement.setDouble(3, user.getDineroDisponible());
			statement.setDouble(4, user.getTiempoDisponible());
			statement.setDouble(5, user.getCostoTotal());
			statement.setInt(6, user.getPosicionX());
			statement.setInt(7, user.getPosicionY());
			
			
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Usuario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Usuario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Usuario findByDNI(int dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
