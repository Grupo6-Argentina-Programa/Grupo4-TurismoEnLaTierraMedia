package DAO;

import java.util.List;

import modelos.TipoAtraccion;

public interface TipoDeAtraccionDAO extends GenericDAO<TipoAtraccion> {

	public abstract TipoAtraccion findByID(int IdTipoAtraccion);

	public abstract TipoAtraccion findByReferenceAndType(int reference, String type);

	public abstract int countOnlyObjectsOfOneType(String objctType);
	// ejemplo, cuenta la cantidad de usuarios/ atracciones/ promociones.
	
	public abstract List<TipoAtraccion> findOnlyObjectsOfOneType(String objctType);

}
