package DAO;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import modelos.Atraccion;
import modelos.TIpoAtraccion;
import modelos.Usuario;

public class TipoAtraccionDAOTest {
	@Test
	public void insertarTipoAtraccionTest() {
		TIpoAtraccion Tipolocal=new TIpoAtraccion();
		TipoDeAtraccionDAO.insert(Tipolocal);
	}

}
