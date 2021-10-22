package DAO;

import main.java.sugeribles.Atraccion;

public interface AtraccionDAO extends GenericDAO<Atraccion>{
	public abstract Atraccion findByID (Integer id);
	
}
