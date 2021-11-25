package testeos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import DAO.DAOFactory;
import DAO.UsuarioDAO;
import modelos.Usuario;

public class UsuarioDAOTest {

	@Test
	public void comprobarSiExistenUsuariosCargadosTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		int cantidad = userDAO.countAll();

		assertNotNull(cantidad);
	}

	@Test
	public void comprobarCantidadDeUsuariosCargadosTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		int cantidadObetenida = userDAO.countAll();

		List<Usuario> usuariosBD = userDAO.findAll();

		int cantidadEsperada = 0;
		for (@SuppressWarnings("unused")
		Usuario i : usuariosBD) {
			cantidadEsperada++;
		}

		assertEquals(cantidadObetenida, cantidadEsperada);
	}

	@Test
	public void comprobarExisteciaDelPrimerUsuarioPorIdTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		Usuario hobbit1 = userDAO.findById(1);

		assertNotNull(hobbit1);
	}

	@Test
	public void comprobarLosValoresCorrectosDelPrimerUsuarioTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		Usuario hobbitBD = userDAO.findById(1);
		Usuario hobbitLocal = new Usuario(1, "hobbit1", "aaaa", 12, 10, 1, 1);

		assertTrue(hobbitBD.equals(hobbitLocal));
	}

	@Test
	public void encontrarATodosLosUsuariosTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		List<Usuario> usuariosBD = userDAO.findAll();

		int cantidadUsuariosObtenidos = 0;
		for (@SuppressWarnings("unused")
		Usuario i : usuariosBD) {
			cantidadUsuariosObtenidos++;
		}

		int cantidadUsuariosEsperados = userDAO.countAll();

		assertEquals(cantidadUsuariosObtenidos, cantidadUsuariosEsperados);
	}

	@Test
	public void buscarIdPorMedioDeNombreDeUsuarioYContraseniaTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		String username = "hobbit1";
		String password = "aaaa";

		int idObtenida = userDAO.findUserId(username, password);
		System.out.println(idObtenida);
		int idEsperada = 1;
		System.out.println(idEsperada);

		assertEquals(idObtenida, idEsperada);
	}

	@Test
	public void insertarUsuarioDBTest() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

		int cantidadUsuariosBDInicial = userDAO.countAll();

		Usuario userLocal = new Usuario("UsuarioTest", "abcd", 0, 0, 0, 0);
		userDAO.insert(userLocal);

		int cantidadUsuariosBDActual = userDAO.countAll();

		assertNotEquals(cantidadUsuariosBDInicial, cantidadUsuariosBDActual);
		assertEquals(cantidadUsuariosBDInicial + 1, cantidadUsuariosBDActual);

		userLocal.setId(userDAO.findUserId("UsuarioTest", "abcd"));

		int idUsuarioLocal = userLocal.getId();
		int ultimaIdAniadida = userDAO.findLastID();

		assertEquals(idUsuarioLocal, ultimaIdAniadida);

		Usuario userBD = userDAO.findById(ultimaIdAniadida);
		
		assertTrue(userLocal.equals(userBD));

	}

	/*
	 * 
	 * 
	 * System.out.println(); System.out.
	 * println("Crear un Usuario locar y asignarle el valor del Usuario ID2");
	 * Usuario usuario1 = userDAO.findById(2); System.out.println(usuario1);
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * System.out.println();
	 * System.out.println("Busqueda del usuario de nombre hobbit3 : "); Usuario
	 * usuario2 = userDAO.findByUsername("hobbit3"); System.out.println(usuario2);
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * System.out.println(); System.out.println("Ingresar a DB el usuario Insert");
	 * Usuario usuario3 = new Usuario ("Insert", "bbbb", 36, 12, 32, 32);
	 * userDAO.insert(usuario3);
	 * System.out.println(userDAO.findByUsername("Insert"));
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * System.out.println(); int id = userDAO.findId("Insert", "bbbb");
	 * System.out.println("Busca el Id del Usuario Insert = " + id);
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * System.out.println();
	 * System.out.println("Setea el nombre, finalizando actualiza el dato.");
	 * usuario3.setUsuario("Insert2"); userDAO.update(usuario3);
	 * System.out.println(usuario3);
	 * System.out.println(userDAO.findById(usuario3.getId()));
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * System.out.println();
	 * System.out.println("Elimina el Usuario anteriormente creado.");
	 * userDAO.delete(usuario3);
	 * System.out.println(userDAO.findByUsername("Insert2"));
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * System.out.println("ultimo ID añadido :" + userDAO.findMaxID()); }
	 */

	/*
	 * @Test public void alCrearUnNuevoUsuarioLoPuedoBuscarPorSuId() { UsuarioDAO
	 * userDAO = DAOFactory.getUsuarioDAO();
	 * System.out.println("ultimo ID añadido :" + userDAO.findLastID());
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * Usuario u = new Usuario("usuario", "cccc", 36, 12, 32, 32);
	 * System.out.println("Crear Usuario local de ID" + u.getId());
	 * System.out.println(u);
	 * System.out.println("//---------------------------------------------//");
	 * 
	 * System.out.println("Se Inserta en base de datos"); userDAO.insert(u);
	 * System.out.println("//---------------------------------------------//");
	 * Usuario usuario = userDAO.findByUsername("usuario");
	 * Assert.assertEquals(usuario.getContrasenia(), "cmcmc");
	 * 
	 * }
	 */

}
