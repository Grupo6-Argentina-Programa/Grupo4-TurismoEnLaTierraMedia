package testeos;

import static org.junit.Assert.*;

import org.junit.Test;

import modelos.TipoAtraccion;
import modelosEnum.ENUMTIPO;

public class TipoDeAtraccionTest {

	@Test
	public void creacionTest() {
		TipoAtraccion tda = new TipoAtraccion(1, 2, "aaaa", "bbbb");
		assertNotNull(tda);
		TipoAtraccion tda2 = new TipoAtraccion(2, "aaaa", "bbbb");
		assertNotNull(tda2);
	}
	
	@Test
	public void ComprobarAsignarValoresTest() {
		TipoAtraccion tda = new TipoAtraccion( 2, "Usuario", "Degustacion");
		
		int valor1Esperado = 2;
		int valor1Obtenido = tda.getIdReferencia();
		assertEquals(valor1Esperado, valor1Obtenido);
		
		String valor2Esperado = "Usuario";
		String valor2Obtenido = tda.getTipoDelObjeto();
		assertEquals(valor2Esperado, valor2Obtenido);
		
		String valor3Esperado = "Degustacion";
		String valor3Obtenido = tda.getTipoFavorito();
		assertEquals(valor3Esperado, valor3Obtenido);
	}
	
	@Test
	public void asignarPreferenciaTest() {
		TipoAtraccion tda = new TipoAtraccion(1, 2, "test1", "nada");
		assertEquals(ENUMTIPO.SinDefinir, tda.getPreferencia());
		
		TipoAtraccion tda1 = new TipoAtraccion(1, 2, "test2", "Degustacion");
		assertEquals(ENUMTIPO.DEGUSTACION, tda1.getPreferencia());
		
		TipoAtraccion tda2 = new TipoAtraccion(1, 2, "test3", "Paisaje");
		assertEquals(ENUMTIPO.PAISAJE, tda2.getPreferencia());
		
		TipoAtraccion tda3 = new TipoAtraccion(1, 2, "test4", "Aventura");
		assertEquals(ENUMTIPO.AVENTURA, tda3.getPreferencia());
	}

}
