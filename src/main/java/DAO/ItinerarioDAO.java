package main.java.DAO;

import Itinerario.Itinerario;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	public abstract Itinerario findByID (Integer id);

}

