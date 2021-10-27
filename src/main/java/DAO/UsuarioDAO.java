package main.java.DAO;

import main.java.usuarios.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario findByID_Usuario(int ID_Usuario);
	
	public abstract Usuario findByUsuario(String usuario);
	
}
