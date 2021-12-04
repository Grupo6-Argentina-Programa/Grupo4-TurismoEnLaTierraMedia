package sistema;

import java.util.*;

import modelos.Atraccion;
import modelos.Itinerario;
import modelos.Promocion;
import modelos.TipoDeAtraccion;
import modelos.Usuario;
import modelosEnum.ENUMTIPO;

import DAO.AtraccionDAO;
import DAO.DAOFactory;
import DAO.ItinerarioDAO;
import DAO.PromocionDAO;
import DAO.TipoDeAtraccionDAO;
import DAO.UsuarioDAO;

public class Sistema {
	private static List<Usuario> usuarios;
	private static List<Atraccion> atracciones;
	private static List<Promocion> promociones;
	@SuppressWarnings("unused")
	private static Object swicth;

	@SuppressWarnings({ "resource", "unused" })
	private static String ingresarDatoStr() {
		Scanner scan = new Scanner(System.in);
		String datoStr = scan.nextLine();
		return datoStr;
	}

	@SuppressWarnings("resource")
	private static int ingresarDatoInt() {
		Scanner scan = new Scanner(System.in);
		int datoInt = scan.nextInt();
		return datoInt;
	}

	public void cargarAtracciones() {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atracciones = atraccionDAO.findAll();
		for (Atraccion i : atracciones) {
			actualizarAtraccionTDA(i);
		}
	}

	public void cargarPromociones() {
		PromocionDAO promocionesDAO = DAOFactory.getPromocionDAO();
		promociones = promocionesDAO.findAll();
		for (Promocion i : promociones) {
			actualizarPromocionTDA(i);
		}
	}

	public void cargarUsuarios() {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarios = usuarioDAO.findAll();
		for (Usuario i : usuarios) {
			actualizarUsuarioTDA(i);
			actualizarUsuarioItinerario(i);
		}
	}

////////////////////////////////////////////////////////////////////////////////

	private void actualizarAtraccionTDA(Atraccion atraccion) {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion auxTDA = tdaDAO.findByReferenceAndType(atraccion.getId(), "Atraccion");
		atraccion.setPreferencia(auxTDA.getPreferencia());
	}

	private void actualizarPromocionTDA(Promocion promocion) {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion auxTDA = tdaDAO.findByReferenceAndType(promocion.getId(), "Promocion");
		promocion.setPreferencia(auxTDA.getPreferencia());
	}

	private void actualizarUsuarioTDA(Usuario usuario) {
		TipoDeAtraccionDAO tdaDAO = DAOFactory.getTipoDeAtraccionDAO();
		TipoDeAtraccion auxTDA = tdaDAO.findByReferenceAndType(usuario.getId(), "Usuario");
		usuario.setPreferencia(auxTDA.getPreferencia());
	}

	private void actualizarUsuarioItinerario(Usuario usuario) {
		int IdDelUsuario = usuario.getId();
		List<Atraccion> atraccionesDelUsuarios = buscarAtraccionesDelItinerario(IdDelUsuario);
		usuario.agregarAtracciones(atraccionesDelUsuarios);
	}

	private List<Atraccion> buscarAtraccionesDelItinerario(int IdDelUsuario) {
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();

		List<Atraccion> atraccionesBuscadas = new ArrayList<>();
		List<Itinerario> itinerariosBuscados = itinerarioDAO.findAllAttractionsOfUser(IdDelUsuario);

		for (Itinerario i : itinerariosBuscados) {
			int IdDeLaAtraccion = i.getIdAtraccion();
			atraccionesBuscadas.add(atraccionDAO.findByID(IdDeLaAtraccion));
		}
		return atraccionesBuscadas;
	}

////////////////////////////////////////////////////////////////////////////////

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
		Sistema sistema = new Sistema();
		sistema.cargarUsuarios();
		sistema.cargarAtracciones();
		sistema.cargarPromociones();
		System.out.println(usuarios);
		System.out.println(atracciones);
		System.out.println(promociones);

		Usuario hobbit3 = new Usuario(10, ENUMTIPO.PAISAJE, 15, 6);

		System.out.println("Ingrese un Numero");
		System.out.println("1:Sugerir Atracciones");
		System.out.println("2:Sugerir Promociones");

		int entradaI = 0;

		entradaI = ingresarDatoInt();

		switch (entradaI) {
		case 1:
			break;
		case 2:
			break;
		case 3:

			break;
		case 4:

		case 9:

			break;
		}
	}

}
