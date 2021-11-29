package DAO;
import modelos.TIpoAtraccion;
public interface TipoDeAtraccionDAO extends GenericDAO<TIpoAtraccion> {
	public abstract TIpoAtraccion findByID(int IdTipoAtraccion);
	public abstract TIpoAtraccion insert();
}
