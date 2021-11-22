package DAO;

import modelos.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findById(int id);
	
	public abstract Usuario findByUsername(String username);
	
	public abstract Usuario findByUsernameAndPassword (String username, String password);
	
	public abstract int findUserId (String username, String password);
	
}
