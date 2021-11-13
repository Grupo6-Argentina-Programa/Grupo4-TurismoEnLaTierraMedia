package main.java.DAO;

import java.util.List;

import main.java.sugeribles.Atraccion;
import modelo.Pelicula;

public interface AtraccionDAO extends GenericDAO<Atraccion>{
	
	public abstract Atraccion findByID (Integer id);
	
	public abstract List<Atraccion> findAll();

	public abstract List<Atraccion> buscarAtraccion(int atra) throws Exception;

}
