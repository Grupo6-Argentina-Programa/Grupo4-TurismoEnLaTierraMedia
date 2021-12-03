package sistema;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import modelos.Atraccion;
import modelos.Usuario;

public class SistemaTest {

	@Test
	public void cargarAtraccionesTest() {
		Sistema sistema = new Sistema();
		List<Atraccion> atracciones = new ArrayList<>();

		int cantidadDeAtraccionesInicial = cantidadDeAtracciones(atracciones);

		sistema.cargarAtracciones();
		atracciones = sistema.getAtracciones();

		int cantidadDeAtraccionesActual = cantidadDeAtracciones(atracciones);
		assertNotEquals(cantidadDeAtraccionesInicial, cantidadDeAtraccionesActual);

		for (Atraccion i : atracciones) {
			assertNotNull(i);
			System.out.println(i.toString());
		}
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

	private int cantidadDeAtracciones(List<Atraccion> atracciones) {
		int cantidad = 0;
		for (Atraccion i : atracciones) {
			cantidad++;
		}
		return cantidad;
	}

	private int cantidadDeUsuarios(List<Usuario> usuarios) {
		int cantidad = 0;
		for (Usuario i : usuarios) {
			cantidad++;
		}
		return cantidad;
	}

}
