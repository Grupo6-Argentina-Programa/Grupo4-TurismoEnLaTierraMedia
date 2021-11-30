package DAO;

import java.util.List;
import org.junit.Test;

import modelos.TipoAtraccion;

import static org.junit.Assert.*;

public class TipoAtraccionDAOTest {

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
		List<TipoAtraccion> todos = buscarlosAtodos();
		for (TipoAtraccion i : todos) {
			assertNotNull(i);
		}
	}

	@Test
	public void contarlosAtodosTest() {

		int cantidadEsperada = contarlosAtodos();
		int cantidadObtenida = contarlosAtodosLosEncontrados();
		assertEquals(cantidadObtenida, cantidadEsperada);
	}

	@Test
	public void contarLaCantidadDeAtraccionesTest() {

		int cantidadEsperada = 0;
		int cantidadObtenida = contarAtodosLosObjetosDeUnMismoTipo("Atraccion");

		List<TipoAtraccion> todos = buscarlosAtodos();
		for (TipoAtraccion i : todos) {
			String aux = i.getTipoDelObjeto();
			if (aux.equals("Atraccion"))
				cantidadEsperada++;
		}
		assertEquals(cantidadEsperada, cantidadObtenida);
	}

	@Test
	public void buscarElPrimerIdTest() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoAtraccion objeto1 = tdaDAO.findByID(1);
		assertNotNull(objeto1);
	}

	@Test
	public void buscarPorTipoDeObjetoYsuIdReferenciaTest() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoAtraccion objeto1 = tdaDAO.findByReferenceAndType(1, "Atraccion");
		assertNotNull(objeto1);
	}

	@Test
	public void buscarTodosLosObjetosDeUnMismoTipoTest() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		List<TipoAtraccion> todasLasAtraaciones = tdaDAO.findOnlyObjectsOfOneType("Atraccion");

		int cantidadEsperada = contarAtodosLosObjetosDeUnMismoTipo("Atraccion");

		int cantidadObtenida = 0;
		for (TipoAtraccion i : todasLasAtraaciones) {
			cantidadObtenida++;
		}
		assertEquals(cantidadEsperada, cantidadObtenida);
	}

	@Test
	public void comprobarLosValoresDeLaPrimeraAtraccionTest() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();

		int referenciaEsperada = 1;
		String tipoFavoritoEsperado = "Paisaje";
		String tipoDelObjetoEsperado = "Atraccion";

		TipoAtraccion tdaImportado = tdaDAO.findByReferenceAndType(1, "Atraccion");
		int referenciaEObtenido = tdaImportado.getIdReferencia();
		String tipoFavoritoObtenido = tdaImportado.getTipoFavorito();
		String tipoDelObjetoObtenido = tdaImportado.getTipoDelObjeto();

		assertEquals(referenciaEsperada, referenciaEObtenido);
		assertEquals(tipoDelObjetoEsperado, tipoDelObjetoObtenido);
		assertEquals(tipoFavoritoEsperado, tipoFavoritoObtenido);

	}

	@Test
	public void insertarYdeletearUnObjetoTest() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();

		int cantidadObjetosIniciales = tdaDAO.countAll();

		int aux = ultimaIdReferenceDeUnObjeto("Atraccion");
		System.out.println(aux);
		aux++;
		System.out.println(aux);

		TipoAtraccion objeto = new TipoAtraccion(aux, "Atraccion", "Aventura");

		tdaDAO.insert(objeto);
		objeto = tdaDAO.findByReferenceAndType(aux, "Atraccion");

		int objetosActuales = tdaDAO.countAll();
		assertNotEquals(cantidadObjetosIniciales, objetosActuales);
		assertEquals(cantidadObjetosIniciales + 1, objetosActuales);

		tdaDAO.delete(objeto);
		objetosActuales = tdaDAO.countAll();
		assertEquals(cantidadObjetosIniciales, objetosActuales);
	}

////////////////////////////////////////////////////////////////////////////////

	private List<TipoAtraccion> buscarlosAtodos() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		List<TipoAtraccion> todos = tdaDAO.findAll();
		return todos;
	}

	private int contarlosAtodos() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		int cantidad = tdaDAO.countAll();
		return cantidad;
	}

	private int contarlosAtodosLosEncontrados() {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		List<TipoAtraccion> todos = tdaDAO.findAll();
		int cantidad = 0;
		for (TipoAtraccion i : todos)
			cantidad++;
		return cantidad;
	}

	private int contarAtodosLosObjetosDeUnMismoTipo(String nombreDelObjeto) {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		int cantidad = tdaDAO.countOnlyObjectsOfOneType(nombreDelObjeto);
		return cantidad;
	}

	private int ultimaIdReferenceDeUnObjeto(String objeto) {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		List<TipoAtraccion> resultados = tdaDAO.findOnlyObjectsOfOneType(objeto);
		int ultimoId = 0;
		for (TipoAtraccion i : resultados) {
			int id = i.getIdReferencia();
			if (id > ultimoId) {
				ultimoId = id;
			}
		}
		return ultimoId;
	}
}
