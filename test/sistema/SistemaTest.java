package sistema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import modelos.Usuario;

public class SistemaTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void cargarUsuariosTest() {
		Sistema sistema = new Sistema();
		List<Usuario> usuarios = new ArrayList<>();

		int cantidadDeUsuariosInicial = cantidadDeUsuarios(usuarios);

		sistema.cargarUsuarios();
		usuarios = sistema.getUsuarios();

		int cantidadDeUsuariosActual = cantidadDeUsuarios(usuarios);
		assertNotEquals(cantidadDeUsuariosInicial, cantidadDeUsuariosActual);

		for (Usuario i : usuarios) {
			assertNotNull(i);
			System.out.println(i.toString());
		}
	}

////////////////////////////////////////////////////////////////////////////////

	private int cantidadDeUsuarios(List<Usuario> usuarios) {
		int cantidad = 0;
		for (Usuario i : usuarios) {
			cantidad++;
		}
		return cantidad;
	}

}
