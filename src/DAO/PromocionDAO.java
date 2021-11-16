package DAO;

import java.util.List;

import modelos.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	public abstract Promocion findByID(Integer id);

	public List<Promocion> findAll();

	public abstract List<Promocion> BuscarPromocion() throws Exception;

}
