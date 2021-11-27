package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import modelos.Itinerario;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	@Override
	public int insert(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Itinerario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Itinerario";
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
	public List<Itinerario> findAll() {
		try {
			String sql = "SELECT * FROM Itinerario";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerarios = new LinkedList<Itinerario>();
			while (resultados.next()) {
				itinerarios.add(toItinerarios(resultados));
			}

			return itinerarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Itinerario findByID(int idItinerario) {
		try {
			String sql = "SELECT * FROM Itinerario WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idItinerario);
			ResultSet resultados = statement.executeQuery();

			Itinerario itinerario = null;

			if (resultados.next()) {
				itinerario = toItinerarios(resultados);
			}

			return itinerario;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Itinerario> findAllAttractionsOfUser(int idUsuario) {
		try {
			String sql = "SELECT * FROM Itinerario WHERE idUsuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, idUsuario);
			ResultSet resultados = statement.executeQuery();

			List<Itinerario> itinerarios = new LinkedList<Itinerario>();
			while (resultados.next()) {
				itinerarios.add(toItinerarios(resultados));
			}

			return itinerarios;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Itinerario toItinerarios(ResultSet resultados) throws SQLException {
		return new Itinerario(resultados.getInt(1), resultados.getInt(2), resultados.getInt(3));
	}

}
