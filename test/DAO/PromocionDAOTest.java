package DAO;

import static org.junit.Assert.*;

import java.util.List;


import org.junit.Test;

import modelos.Atraccion;

import modelos.Promocion;
import modelos.PromocionAbsoluta;
import modelos.PromocionAxB;

import modelos.PromocionPorcentaje;


public class PromocionDAOTest {

	@Test
	public void comprobarSiExistePromocionDaoTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		int cantidad = promocionDAO.countAll();
		assertNotNull(cantidad);
		
	}
	
	@Test 
	public void comprobarSiExisteLasPromociones() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		List<Promocion> promociones = promocionDAO.findAll();
		
		for (@SuppressWarnings("unused")
		Promocion i : promociones) {
			assertNotNull(promociones);
		}
		
	}
	
	@Test
	public void comprobarLaCantidadDePromocionesTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

		int cantidadObtenida = promocionDAO.countAll();
		List<Promocion> promociones = promocionDAO.findAll();

		int cantidadEsperada = 0;
		for (@SuppressWarnings("unused")
		Promocion i : promociones) {
			cantidadEsperada++;
		}

		assertEquals(cantidadEsperada, cantidadObtenida);
	}

	@Test
	public void comprobarLosValoresDeLasPromocionesTest() {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

	Promocion promocion1 = promocionDAO.findByID(1);

		int idAtraccionAObtendio = promocion1.getAtraccionA().getId();
		int idAtraccionBObtendio = promocion1.getAtraccionB().getId();

		int idAtraccionAEsperado = 1;
		int idAtraccionBEsperado = 2;

		assertEquals(idAtraccionAEsperado, idAtraccionAObtendio);
		assertEquals(idAtraccionBObtendio, idAtraccionBEsperado);
	}

	

		

	
	@Test
	public void insertarPromocionPorcentajeTest() throws Exception {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

		int cantidadDePromocionInicial = promocionDAO.countAll();

		Atraccion atraccinATest = null;
		Atraccion atraccionBTest = null;
		int atraccionPlus = 0;
		String porcentaje = null;
		String total = null;
		Promocion promocionlocal = new PromocionPorcentaje(3, 3, atraccinATest, atraccionBTest, atraccionPlus, porcentaje, total) ;
		promocionDAO.insert(promocionlocal);

		int cantidadDePromocionesActual = promocionDAO.countAll();

		assertNotEquals(cantidadDePromocionInicial, cantidadDePromocionesActual);
		assertEquals(cantidadDePromocionInicial + 1, cantidadDePromocionesActual);

		int idAtraccionA = 4;
		int idAtraccionB = 1;
		boolean seEncuentraEnLaBaseDeDatos = false;
		List<Promocion> promociones = promocionDAO.findAll();

		for (Promocion i : promociones) {
			if (i.getAtraccionA().getId() == idAtraccionA) {
				if (i.getAtraccionB().getId() == idAtraccionB) {
					seEncuentraEnLaBaseDeDatos = true;
					promocionDAO.delete(i);
				}
			}
		}

		assertTrue(seEncuentraEnLaBaseDeDatos);
	}

	@Test
	public void deletearPromocionTest() throws Exception {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

		int cantidadDePromocionInicial = promocionDAO.countAll();
		
		
		int idtest = 2;
		int tipotest = 3;
		Atraccion atraccionA = null;
		Atraccion atraccionB = null;
		int atraccionregalotest = 0;
		String porcentajeTest = null;
		String preciofinalTest = null;
		Promocion promocionlocal = new PromocionAbsoluta(idtest , tipotest , atraccionA, atraccionB, atraccionregalotest, porcentajeTest, preciofinalTest);
		promocionDAO.insert(promocionlocal);

		int cantidadDePromocionActual = promocionDAO.countAll();

		assertNotEquals(cantidadDePromocionInicial, cantidadDePromocionActual);
		assertEquals(cantidadDePromocionInicial + 1, cantidadDePromocionActual);

		int idAtraccionA= 3;
		int idAtraccionB = 4;
		boolean seEncuentraEnLaBaseDeDatos = false;
		List<Promocion> promociones = promocionDAO.findAll();

		for (Promocion i : promociones) {
			if (i.getAtraccionA().getId() == idAtraccionA) {
				if (i.getAtraccionB().getId() == idAtraccionB) {
					seEncuentraEnLaBaseDeDatos = true;
				}
			}
		}
		assertTrue(seEncuentraEnLaBaseDeDatos);

		promociones = promocionDAO.findAll();
		for (Promocion i : promociones) {
			if (i.getAtraccionA().getId() == idAtraccionA) {
				if (i.getAtraccionB().getId() == idAtraccionB) {
					seEncuentraEnLaBaseDeDatos = false;
					promocionDAO.delete(i);
				}
			}
		}
		assertFalse(seEncuentraEnLaBaseDeDatos);
	}
	
	
	@Test
	public void actualizarPromocionDAOTest() throws Exception {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();

		int valorLocal=5;
		int valorDataBase ;
		
		Atraccion atraccionA = null;
		Atraccion atraccionB = null;
		int atraccionRegalo = 0;
		String porcentaje = null;
		String precioFinal = null;
		Promocion promocionLocal = new PromocionAbsoluta(5, 1, atraccionA, atraccionB, atraccionRegalo, porcentaje, precioFinal);
		valorLocal = promocionLocal.getTipo(); //0
		promocionDAO.insert(promocionLocal);
		
		//hago esto para traer el id asignado en sql
		promocionLocal = promocionDAO.findByID(5);

		Promocion promocionDataBase = promocionDAO.findByID(5);
		valorDataBase = promocionDataBase.getId();
		
		assertEquals(valorLocal, valorDataBase);
		
		promocionDataBase.setTipo(valorDataBase+1);
		promocionDAO.update(promocionDataBase);
		
		Promocion promocionDataBase2 = promocionDAO.findByID(2);
		valorDataBase = promocionDataBase2.getTipo();
		
		assertNotEquals(valorLocal, valorDataBase);
		
		promocionDAO.delete(promocionDataBase);
	}
	
}


