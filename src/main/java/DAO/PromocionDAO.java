package main.java.DAO;

import main.java.sugeribles.promociones.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion>{
	public abstract Promocion findByID (Integer id);

}
