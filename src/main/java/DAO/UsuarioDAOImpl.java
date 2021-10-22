package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import jdbc.ConnectionProvider;
import main.java.usuarios.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO Usuario (DNI, tipoFavorito, dineroInicial, dineroDisponible, tiempoInicial, "
					+ "tiempoDisponible, atracciones, costoTotal) VALUES (?,?,?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, user.getDNI());
			statement.setString(2, user.getTipoFavorito().toString());
			statement.setDouble(3, user.getDineroInicial());
			statement.setDouble(4, user.getDineroDisponible());
			statement.setDouble(5, user.getTiempoInicial());
			statement.setDouble(6, user.getTiempoDisponible());
			statement.setString(7, user.getAtracciones().toString());
			statement.setDouble(8, user.getCostoTotal());
			
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
	public Usuario findByDNI(Long dni) {
		// TODO Auto-generated method stub
		return null;
	}

}
