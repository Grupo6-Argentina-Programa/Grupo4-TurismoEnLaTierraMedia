package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import modelos.TipoDeAtraccion;
import jdbc.ConnectionProvider;

public class TipoDeAtraccionDAOImpl implements TipoDeAtraccionDAO {

	@Override
	public int insert(TipoDeAtraccion t) {
		try {
			String sql = "INSERT INTO TipoAtraccion (idReferencia, tipoDelObjeto, tipoFavorito) VALUES (?,?,?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, t.getIdReferencia());
			statement.setString(2, t.getTipoDelObjeto());
			statement.setString(3, t.getTipoFavorito());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(TipoDeAtraccion t) {
		// No se utiliza
		return 0;
	}

	@Override
	public int delete(TipoDeAtraccion t) {
		try {
			String sql = "DELETE FROM TipoAtraccion WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1, t.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM TipoAtraccion";
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

	@Override
	public List<TipoDeAtraccion> findAll() {
		try {
			String sql = "SELECT * FROM TipoAtraccion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<TipoDeAtraccion> atraccionesT = new LinkedList<TipoDeAtraccion>();
			while (resultados.next()) {
				atraccionesT.add(toTypeA(resultados));
			}

			return atraccionesT;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public TipoDeAtraccion findByID(int IdTipoAtraccion) {
		try {
			String sql = "SELECT * FROM TipoAtraccion WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, IdTipoAtraccion);
			ResultSet resultados = statement.executeQuery();

			TipoDeAtraccion atraccionT = null;

			if (resultados.next()) {
				atraccionT = toTypeA(resultados);
			}

			return atraccionT;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public TipoDeAtraccion findByReferenceAndType(int reference, String type) {
		try {
			String sql = "SELECT * FROM TipoAtraccion WHERE idReferencia = ? AND tipoDelObjeto = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, reference);
			statement.setString(2, type);
			ResultSet resultados = statement.executeQuery();

			TipoDeAtraccion atraccionT = null;

			if (resultados.next()) {
				atraccionT = toTypeA(resultados);
			}

			return atraccionT;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private TipoDeAtraccion toTypeA(ResultSet resultados) throws SQLException {
		return new TipoDeAtraccion(resultados.getInt(1), resultados.getInt(2), resultados.getString(3),
				resultados.getString(4));

	}
	
////////////////////////////////////////////////////////////////////////////////

	@Override
	public int countOnlyObjectsOfOneType(String objctType) {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM TipoAtraccion WHERE tipoDelObjeto = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, objctType);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<TipoDeAtraccion> findOnlyObjectsOfOneType(String objctType) {
		try {
			String sql = "SELECT * FROM TipoAtraccion WHERE tipoDelObjeto = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, objctType);
			ResultSet resultados = statement.executeQuery();

			List<TipoDeAtraccion> atraccionesT = new LinkedList<TipoDeAtraccion>();
			while (resultados.next()) {
				atraccionesT.add(toTypeA(resultados));
			}

			return atraccionesT;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}
