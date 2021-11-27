package DAO;

import java.util.List;

import modelos.Itinerario;

public interface ItinerarioDAO extends GenericDAO<Itinerario>{

	public abstract Itinerario findByID (int idItinerario);
	
	public abstract List<Itinerario> findAllAttractionsOfUser (int idUsuario);
	
	//public abstract Itinerario findByIDAttr (Integer idAttraccion);

}

