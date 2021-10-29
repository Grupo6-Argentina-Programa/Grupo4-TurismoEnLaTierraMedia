package main.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import main.java.jdbc.ConnectionProvider;
import main.java.sugeribles.promociones.Promocion;
import main.java.usuarios.Usuario;

public class PromocionDAOImpl implements PromocionDAO{
	@Override
	public int insert(Promocion t) {
		// TODO Auto-generated method stub
		
		
		
		
		return 0;
	}

	@Override
	public int update(Promocion prom) {
		/*try {
			String sql = "UPDATE Promotions SET type = ?, numbreIdAttr1 = ?,"
					+ " numbreIdAttr2 = ?, numbreIdAttrPlus = ?, porcentageDisc = ?,totalCost=? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, prom.getTipo());
			statement.setAtraccion(2, prom.getAtraccionA());
			statement.setDouble(3, prom.getTiempo());
			statement.setInt(4, prom.getAtraP());
			statement.setInt(5, prom.getPorcentaje());
			statement.setString(6, prom.getTotal());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}*/
		return 0;
	}

	@Override
	public int delete(Promocion prom) {
		try {
			String sql = "DELETE FROM Promotions WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, prom.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int findMaxID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Promocion> findAll() {	
		try {
			String sql = "SELECT * FROM Promotions";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promotions = new LinkedList<Promocion>();
			while (resultados.next()) {
				promotions.add(toPromciones(resultados));
			}

			return promotions;
			} catch (Exception e) {
			throw new MissingDataException(e);
			}
		}

	private Promocion toPromciones(ResultSet resultados)throws SQLException {
		return new Promocion(resultados.getInt(1), resultados.getInt(2), resultados.getInt(3),
				resultados.getInt(4), resultados.getInt(5), resultados.getString(6), resultados.getString(7));
	}

	

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Promotions";
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
	public Promocion findByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
	
