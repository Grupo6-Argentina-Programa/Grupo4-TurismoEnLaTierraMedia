package main.java.DAO;

import java.util.List;

import main.java.sugeribles.promociones.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion>{
	public abstract Promocion findByID (Integer id);
	
	public List<Promocion> findAll(); 

}
