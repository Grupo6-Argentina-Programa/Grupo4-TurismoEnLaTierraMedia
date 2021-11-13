package main.java.DAO;

import java.util.List;

import main.java.sugeribles.promociones.Promocion;
import modelo.Pelicula;

public interface PromocionDAO extends GenericDAO<Promocion>{
	public abstract Promocion findByID (Integer id);
	
	public List<Promocion> findAll(); 
	
	
	
	
	public abstract List<Promocion> BuscarPromocion() throws Exception;

}
