package DAO;

import modelos.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findById(int id);
	
	public abstract Usuario findByUsername(String username);
	
	public abstract int findId (String username, String password);
	
}
