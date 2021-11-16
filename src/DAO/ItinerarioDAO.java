package DAO;

import modelos.Itinerario;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	public abstract Itinerario findByID (Integer id);

}

