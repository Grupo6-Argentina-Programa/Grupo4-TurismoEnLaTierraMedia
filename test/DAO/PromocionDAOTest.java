package DAO;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import modelos.Promocion;

public class PromocionDAOTest {

	@Test
	public void comprobarQueExisteAlmenos1Test() {
		boolean existen = false;
		int cantidad = contarlosAtodos();
		if (cantidad > 0)
			existen = true;
		assertTrue(existen);
	}
	
	@Test
	public void buscarlosATodosTest() {
		List<Promocion> todos = buscarlosAtodos();
		for (Promocion i : todos) {
			assertNotNull(i);
		}
	}
	
	@Test
	public void contarlosAtodosTest() {

		int cantidadEsperada = contarlosAtodos();
		int cantidadObtenida = contarlosAtodosLosEncontrados();
		assertEquals(cantidadObtenida, cantidadEsperada);
	}

////////////////////////////////////////////////////////////////////////////////

	private List<Promocion> buscarlosAtodos() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> todos = promocionDAO.findAll();
		return todos;
	}

	private int contarlosAtodos() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		int cantidad = promocionDAO.countAll();
		return cantidad;
	}

	private int contarlosAtodosLosEncontrados() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> todos = promocionDAO.findAll();
		int cantidad = 0;
		for (Promocion i : todos)
			cantidad++;
		return cantidad;
	}

}
