package test.java.testeos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DAO.DAOFactory;
import DAO.UsuarioDAO;
import modelos.Usuario;

public class UsuarioDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		/*
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		
		//Cuenta cantidad de usuarios cargados
		System.out.println("Cantidad de Usuarios cargados = "+ userDAO.countAll()+".");
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		System.out.println("Encontrar a todos los Usuarios :");
		System.out.println(userDAO.findAll());
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		System.out.println("Usuario de ID 1 : ");
		System.out.println(userDAO.findById(1));
		System.out.println("Usuario de ID 5 : ");
		System.out.println(userDAO.findById(5));
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		System.out.println("Crear un Usuario locar y asignarle el valor del Usuario ID2");
		Usuario usuario1 = userDAO.findById(2);
		System.out.println(usuario1);
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		System.out.println("Busqueda del usuario de nombre hobbit3 : ");
		Usuario usuario2 = userDAO.findByUsername("hobbit3");
		System.out.println(usuario2);
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		System.out.println("Ingresar a DB el usuario Insert");
		Usuario usuario3 = new Usuario ("Insert", "bbbb", 36, 12, 32, 32);
		userDAO.insert(usuario3);
		System.out.println(userDAO.findByUsername("Insert"));
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		int id = userDAO.findId("Insert", "bbbb");
		System.out.println("Busca el Id del Usuario Insert = " + id);
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		System.out.println("Setea el nombre, finalizando actualiza el dato.");
		usuario3.setUsuario("Insert2");
		userDAO.update(usuario3);
		System.out.println(usuario3);
		System.out.println(userDAO.findById(usuario3.getId()));
		System.out.println("//---------------------------------------------//");
		
		System.out.println();
		System.out.println("Elimina el Usuario anteriormente creado.");
		userDAO.delete(usuario3);
		System.out.println(userDAO.findByUsername("Insert2"));
		System.out.println("//---------------------------------------------//");
		
		System.out.println("ultimo ID añadido :" + userDAO.findMaxID());
		*/
	}
	
	@Test
	public void colocacionCorrectaDeIdAlCrearUsuario() {
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		System.out.println("ultimo ID añadido :" + userDAO.findMaxID());
		System.out.println("//---------------------------------------------//");
		
		Usuario u = new Usuario("usuario", "cccc", 36, 12, 32, 32);
		System.out.println("Crear Usuario local de ID"+ u.getId());
		System.out.println(u);
		System.out.println("//---------------------------------------------//");
		
		System.out.println("Se Inserta en base de datos");
		userDAO.insert(u);
		System.out.println("//---------------------------------------------//");
		
	}

}
