package main.java.DAO;

import main.java.usuarios.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findById(int id);
	
	public abstract Usuario findByUsername(String username);
	
	public abstract int findId (String username, String password);
	
}
