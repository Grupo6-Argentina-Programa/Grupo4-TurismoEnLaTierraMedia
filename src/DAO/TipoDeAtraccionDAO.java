package DAO;

import modelos.TipoAtraccion;

public interface TipoDeAtraccionDAO extends GenericDAO<TipoAtraccion> {

	public abstract TipoAtraccion findByID(int IdTipoAtraccion);
}
