package DAO;

import modelos.TipoAtraccion;

public interface TipoDeAtraccionDAO extends GenericDAO<TIpoAtraccion> {

	public abstract TipoAtraccion findByID(int IdTipoAtraccion);
}
