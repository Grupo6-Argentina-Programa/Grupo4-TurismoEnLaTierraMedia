package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import modelos.Atraccion;

public class AtraccionDAOImpl implements AtraccionDAO {

	@Override
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO Attractions (attractionName, costInCoins, timeHs, capacityMax, capacity, positionX, positionY) "
					+ "VALUES (?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupo());
			statement.setInt(5, atraccion.getPuestosOcupados());
			statement.setInt(6, atraccion.getPosicionX());
			statement.setInt(7, atraccion.getPosicionY());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Atraccion attaction) {
		try {
			String sql = "UPDATE Atttractions SET attractioName = ?, costInCoins = ?,"
					+ " costTimeInHs = ?, positionX = ?, positionY = ? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, attaction.getNombre());
			statement.setDouble(3, attaction.getCosto());
			statement.setDouble(4, attaction.getTiempo());
			statement.setInt(5, attaction.getPosicionX());
			statement.setInt(6, attaction.getPosicionY());
			statement.setInt(7, attaction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Atraccion attraction) {
		try {
			String sql = "DELETE FROM Attractions WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, attraction.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Atraccion findByID(Integer id) {
		try {
			String sql = "SELECT * FROM Attractions WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			Atraccion attraction = null;

			if (resultados.next()) {
				attraction = toAtracciones(resultados);
			}

			return attraction;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int findMaxID() {
		try {
			String sql = "SELECT MAX(id) AS id FROM Attractions";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();
			System.out.println(resultados.getInt("id"));
			return resultados.getInt("id");
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM Attractions";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> attractions = new LinkedList<Atraccion>();
			while (resultados.next()) {
				attractions.add(toAtracciones(resultados));
			}

			return attractions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Attractios";
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

	private Atraccion toAtracciones(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt(1), resultados.getString(2), resultados.getDouble(3),
				resultados.getDouble(4), resultados.getInt(5), resultados.getInt(6));
	}
}
