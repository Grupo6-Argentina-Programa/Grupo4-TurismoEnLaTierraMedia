package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import modelos.Atraccion;
import modelos.Itinerario;
import modelos.PromocionDepurar;
import modelos.Promocion;
import modelos.PromocionAbsoluta;
import modelos.PromocionAxB;
import modelos.PromocionPorcentaje;
import modelos.TipoDeAtraccion;

public class PromocionDAOImpl implements PromocionDAO {

	@Override
	public int insert(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

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
	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM Promocion";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromotion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion findByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Promocion findByPromotionName(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

////////////////////////////////////////////////////////////////////////////////

	private Promocion toPromotion(ResultSet resultados) throws SQLException {
		int id = resultados.getInt(1);
		String nombre = resultados.getString(2);
		int tipoDeAtraccion = resultados.getInt(3);
		double costoTotal = resultados.getDouble(4);
		int descuentoPorcentual = resultados.getInt(5);

		Atraccion atraccion;
		List<Atraccion> atracciones = new ArrayList<>();

		int idAtraccionA = resultados.getInt(6);
		atraccion = buscarAtraccionSegunSuId(idAtraccionA);
		atracciones.add(atraccion);

		int idAtraccionB = resultados.getInt(7);
		atraccion = buscarAtraccionSegunSuId(idAtraccionB);
		atracciones.add(atraccion);

		int idAtraccionP = resultados.getInt(8);
		if (idAtraccionP > 0) {
			buscarAtraccionSegunSuId(idAtraccionP);
			atracciones.add(atraccion);
		}

		return new Promocion(id, nombre, tipoDeAtraccion, costoTotal, descuentoPorcentual, atracciones);
	}

	private Atraccion buscarAtraccionSegunSuId(int Id) {
		AtraccionDAO attrctionDAO = DAOFactory.getAtraccionDAO();
		Atraccion atraccion = attrctionDAO.findByID(Id);
		return atraccion;
	}

////////////////////////////////////////////////////////////////////////////////

}