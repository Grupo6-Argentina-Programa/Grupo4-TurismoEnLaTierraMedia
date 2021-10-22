package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import main.java.enumeradores.ENUMTIPO;
import main.java.jdbc.ConnectionProvider;
import main.java.sugeribles.Atraccion;


public class AtraccionDAOImpl implements AtraccionDAO {
	
	@Override
	public int insert(Atraccion atraccion) {
		try {
			String sql = "INSERT INTO Atraccion (nombre, costo, tiempo, cupo, puestosOcupados, tipo) "
					+ "VALUES (?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setDouble(2, atraccion.getCosto());
			statement.setDouble(3, atraccion.getTiempo());
			statement.setInt(4, atraccion.getCupo());
			statement.setInt(5, atraccion.getPuestosOcupados());
			statement.setString(6, atraccion.getTipo().toString());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public List<Atraccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int update(Atraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Atraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Atraccion findByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
