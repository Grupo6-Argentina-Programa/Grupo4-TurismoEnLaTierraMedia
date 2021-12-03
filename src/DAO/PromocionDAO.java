package DAO;

import modelos.Promocion;

public interface PromocionDAO extends GenericDAO<Promocion> {
	
	public abstract Promocion findByID(Integer id);
	
	public abstract Promocion findByPromotionName(Integer id);

}
