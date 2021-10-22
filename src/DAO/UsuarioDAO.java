package DAO;

import main.java.usuarios.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByDNI(Long dni);
	
}
