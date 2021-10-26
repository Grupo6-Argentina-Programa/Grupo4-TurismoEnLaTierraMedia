package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import main.java.DAO.MissingDataException;
import main.java.enumeradores.ENUMTIPO;
import main.java.jdbc.ConnectionProvider;
import main.java.usuarios.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM '01_Usuarios'";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			System.out.println("Cantidad de Usuarios cargados = "+total+".");
			
			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	@Override
	public List<Usuario> findAll() {
		try {
			String sql = "SELECT * FROM '01_Usuarios'";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();
			while (resultados.next()) {
				usuarios.add(toUsuarios(resultados));
			}
			
			//Muestra cada Usuario
			for (Usuario usuario : usuarios) {
				System.out.println(usuario.toString());
			}

			return usuarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Usuario toUsuarios(ResultSet resultados) throws SQLException {
		return new Usuario(resultados.getInt(1),resultados.getString(2),resultados.getString(3),
				resultados.getDouble(4),resultados.getDouble(5),resultados.getInt(6),resultados.getInt(7));
	}
	
////////////////////////////////////////////////////////////////////////////////	
	
	
	@Override
	public int insert(Usuario user) {
		try {
			String sql = "INSERT INTO Usuario (DNI, usuario, contrase�a, dinero, tiempo, costoTotal, "
					+ " posicionX, posicionY) VALUES (?,?,?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, user.getDNI());
			statement.setString(2, user.getUsuario().toString());
			statement.setString(3, user.getContrase�a().toString());
			statement.setDouble(4, user.getDineroDisponible());
			statement.setDouble(5, user.getTiempoDisponible());
			statement.setDouble(6, user.getCostoTotal());
			statement.setInt(7, user.getPosicionX());
			statement.setInt(8, user.getPosicionY());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}



	// solo esta haciendo un cambio de contrase�a
	@Override
	public int update(Usuario user) {
		try {
			String sql = "UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getContrase�a().toString());
			statement.setString(2, user.getUsuario().toString());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	// Elimina Segun DNI
	public int delete(Usuario user) {
		try {
			String sql = "DELETE FROM USERS WHERE DNI = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, user.getDNI());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Usuario findByDNI(int dni) {
			try {
				String sql = "SELECT * FROM USERS WHERE DNI = ?";
				Connection conn = ConnectionProvider.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, dni);
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

}
