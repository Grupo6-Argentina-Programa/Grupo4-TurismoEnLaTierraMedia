package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import modelos.Promocion;
import modelos.PromocionAbsoluta;
import modelos.PromocionAxB;
import modelos.PromocionPorcentaje;

public class PromocionDAOImpl implements PromocionDAO {

	private AtraccionDAOImpl atraccionDA0 = new AtraccionDAOImpl();

	@Override
	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO Promocion (tipo,atraccionA,atraccionB, atraccionP, porcentaje,totalCosto) VALUES (?, ?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getTipo());
			statement.setInt(2, promocion.getAtraccionA().getId());
			statement.setInt(3, promocion.getAtraccionB().getId());
			statement.setInt(4, promocion.getAtraP());
			statement.setString(5, promocion.getPorcentaje());
			statement.setString(6, promocion.getTotal());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion promocion) {

		try {
			String sql = "UPDATE Promocion SET tipo = ?, atraccionA = ?,"
					+ " atraccionB = ?, atraccionP = ?, porcentaje = ?,totalCosto=? WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getTipo());
			statement.setInt(2, promocion.getAtraccionA().getId());
			statement.setInt(3, promocion.getAtraccionB().getId());
			statement.setInt(4, promocion.getAtraP());
			statement.setString(5, promocion.getPorcentaje());
			statement.setString(6, promocion.getTotal());
			statement.setInt(7, promocion.getId());

			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion prom) {
		try {
			String sql = "DELETE FROM Promocion WHERE id = ?";
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
			String sql = "SELECT * FROM Promocion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promotions = new LinkedList<Promocion>();
			while (resultados.next()) {
				promotions.add(toPromociones(resultados));
			}

			return promotions;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	private Promocion toPromociones(ResultSet resultados) throws Exception {
		try {
			if (resultados.getInt(2) == 1) {
				return new PromocionAbsoluta(resultados.getInt(1), 1,
						atraccionDA0.findByID((Integer) resultados.getObject(3)),
						atraccionDA0.findByID((Integer) resultados.getObject(4)), resultados.getInt(5),
						resultados.getString(6), resultados.getString(7));
			} else if (resultados.getInt(2) == 2) {
				return new PromocionAxB(resultados.getInt(1), 2,
						atraccionDA0.findByID((Integer) resultados.getObject(3)),
						atraccionDA0.findByID((Integer) resultados.getObject(4)), resultados.getInt(5),
						resultados.getString(6), resultados.getString(7));
			}
			return new PromocionPorcentaje(resultados.getInt(1), 3,
					atraccionDA0.findByID((Integer) resultados.getObject(3)),
					atraccionDA0.findByID((Integer) resultados.getObject(4)), resultados.getInt(5),
					resultados.getString(6), resultados.getString(7));
		} catch (SQLException e) {
			throw new MissingDataException(e);
		}

	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM Promocion";
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

	public List<Promocion> BuscarPromocion() throws Exception {
		try {
			String sql = "SELECT p.tipo FROM Promocion p JOIN Atraccion a WHERE  ";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promocionn = new ArrayList<Promocion>();

			while (resultados.next()) {

				promocionn.add(toPromociones(resultados));
			}
			return promocionn;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}