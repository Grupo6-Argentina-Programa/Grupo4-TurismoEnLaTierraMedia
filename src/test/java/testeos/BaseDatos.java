package test.java.testeos;

import org.junit.Test;

import main.java.DAO.*;
import main.java.enumeradores.ENUMTIPO;
import main.java.sugeribles.Atraccion;
import main.java.usuarios.Usuario;

public class BaseDatos {
    @Test
    public void insertarUsuario(){
    	Usuario usuario = new Usuario(12345678, ENUMTIPO.PAISAJE, 5, 10);
    	UsuarioDAOImpl inyeccion = new UsuarioDAOImpl();
    	inyeccion.insert(usuario);
    	
    }
    
    @Test
    public void insertarAtraccion(){
        Atraccion atraccion = new Atraccion("Atraccion prueba 1",2,3,4, ENUMTIPO.PAISAJE);
        AtraccionDAOImpl inyeccion = new AtraccionDAOImpl();
        inyeccion.insert(atraccion);
        
    }
}
