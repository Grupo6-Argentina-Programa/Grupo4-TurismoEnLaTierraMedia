package DAO;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelos.Itinerario;

public class ItinerarioDAOTest {

	@Test
	public void comprobarExistenciaDeItinerarioDAOTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		int cantidad = itinerarioDAO.countAll();
		assertNotNull(cantidad);
	}

	@Test
	public void comprobarLaExistenciaDeTodosLosItinerariosTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		List<Itinerario> itinerarios = itinerarioDAO.findAll();

		for (@SuppressWarnings("unused")
		Itinerario i : itinerarios) {
			assertNotNull(itinerarios);
		}
	}

	@Test
	public void comprobarLaCantidadDeItinerariosTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		int cantidadObtenida = itinerarioDAO.countAll();
		List<Itinerario> itinerarios = itinerarioDAO.findAll();

		int cantidadEsperada = 0;
		for (@SuppressWarnings("unused")
		Itinerario i : itinerarios) {
			cantidadEsperada++;
		}

		assertEquals(cantidadEsperada, cantidadObtenida);
	}

	@Test
	public void comprobarLosValoresDel1erItinerarioTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		Itinerario itinerario1 = itinerarioDAO.findByID(1);

		int idUsuarioObtendio = itinerario1.getIdUsuario();
		int idAtraccionObtendio = itinerario1.getIdAtraccion();

		int idUsuarioEsperado = 1;
		int idAtraccionEsperado = 1;

		assertEquals(idUsuarioEsperado, idUsuarioObtendio);
		assertEquals(idAtraccionObtendio, idAtraccionEsperado);
	}

	@Test
	public void comprobarTodasLasAtraccionesDel1erUsuarioTest() {
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		List<Itinerario> itinerarios = itinerarioDAO.findAll();
		List<Itinerario> itinerariosU1 = itinerarioDAO.findAllAttractionsOfUser(1);

		int cantidadItinerariosEsperados = 0;
		int cantidadItinerariosObtenidos = 0;

		for (Itinerario i : itinerarios) {
			int idUsuario = i.getIdUsuario();
			if (idUsuario == 1) {
				cantidadItinerariosEsperados++;
			}
		}

		for (@SuppressWarnings("unused")
		Itinerario i : itinerariosU1) {
			cantidadItinerariosObtenidos++;
		}

		assertEquals(cantidadItinerariosEsperados, cantidadItinerariosObtenidos);
	}

}
