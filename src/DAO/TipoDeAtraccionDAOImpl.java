package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import modelos.TIpoAtraccion;
public class TipoDeAtraccionDAOImpl implements TipoDeAtraccionDAO {

	
	@Override
	public int insert(TIpoAtraccion t) {
		try {
			String sql = "INSERT INTO TipoAtraccion (IdReferencia , tipo , tipoFavorito) VALUES (?,?,?)";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, t.getIdReferencia());
			statement.setString(2, t.getTipo());
			statement.setString(3, t.getTipoFavorito().toString());

			 int rows= statement.executeUpdate();
			 return rows;
	}catch (Exception e) {
		throw new MissingDataException(e);
	}
		
		
	}
	@Override
	public int update(TIpoAtraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(TIpoAtraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TIpoAtraccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TIpoAtraccion findByID(int IdTipoAtraccion) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TIpoAtraccion insert() {
		// TODO Auto-generated method stub
		return null;
	}

}
