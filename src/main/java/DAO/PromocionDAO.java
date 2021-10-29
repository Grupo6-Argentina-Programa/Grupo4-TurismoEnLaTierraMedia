package main.java.DAO;
import main.java.sugeribles.Atraccion;
import main.java.sugeribles.promociones.*;


public interface PromocionDAO extends GenericDAO<Promocion>{
	
	public abstract Promocion findByID (Integer id);
	
}



