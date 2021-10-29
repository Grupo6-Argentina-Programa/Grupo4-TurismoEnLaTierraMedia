package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import main.java.jdbc.ConnectionProvider;
import main.java.sugeribles.Atraccion;
import main.java.sugeribles.promociones.Promocion;

public class PromocionDAOImpl implements PromocionDAO{

	@Override
	public int insert(Promocion promocion) {
		ry {
			String sql = "INSERT INTO Attractions (attractionName, costInCoins, timeHs, capacityMax, capacity, positionX, positionY) "
					+ "VALUES (?,?,?,?,?,?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.);
			statement.setDouble(2, promocion.getCosto());
			statement.setDouble(3, promocion.getTiempo());
			statement.setInt(4, promocion.getCupo());
			statement.setInt(5, promocion.getPuestosOcupados());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	/*
	"type"	INTEGER NOT NULL DEFAULT 01,
	"numbreIdAttr1"	INTEGER NOT NULL DEFAULT 01,
	"numbreIdAttr2"	INTEGER NOT NULL DEFAULT 02,
	"numbreIdAttrPlus"	INTEGER,
	"porcentageDisc"	REAL,
	"totalCost"	REAL,*/

	@Override
	public int update(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findMaxID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Promocion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Promocion findByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
