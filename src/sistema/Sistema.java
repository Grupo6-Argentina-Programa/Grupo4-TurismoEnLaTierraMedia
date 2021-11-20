package sistema;

import modelos.Atraccion;
import modelos.AtraccionSugerida;
import modelos.Promocion;
import modelos.PromocionInterface;
import modelos.PromocionAbsoluta;
import modelos.PromocionAxB;
import modelos.PromocionPorcentaje;
import modelos.Usuario;
import modelosCmp.AtraccionComparador;
import modelosCmp.PromocionComparador;
import modelosEnum.ENUMTIPO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.AtraccionDAO;
import DAO.DAOFactory;
import DAO.PromocionDAO;
import DAO.UsuarioDAO;

public class Sistema {
	private static List<Usuario> usuarios;
	private static List<Atraccion> atracciones;
	private static  List<Promocion> promociones;

	@SuppressWarnings("resource")
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


 public void cargarUsuario (){
	UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
	usuarios = usuarioDAO.findAll();
}
 public void cargarAtracciones (){
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atracciones = atraccionDAO.findAll();
	}
 
 public void cargarPromociones (){
		PromocionDAO promocionesDAO = DAOFactory.getPromocionDAO();
		promociones = promocionesDAO.findAll();
	}
	
	

//----------------------------------------------------------------------------//

	private void sugerirPromociones(Usuario user) {
		List<Atraccion> listaAtracciones = new ArrayList<>();
		int valorTemporal;

		for (PromocionInterface promocion : promociones) {
			if (promocion.getAtraccionA().hayEspacio() && promocion.getAtraccionB().hayEspacio()) {
				int costoActual = 0;
				double tiempoActual = 0;

				if (promocion.getAtraccionA().getTipo() == user.getTipoFavorito()) {
					if (promocion.getClass() == PromocionAbsoluta.class) {
						if ((costoActual + (int) promocion.retornarPromocion() <= user.getDineroDisponible())
								&& (tiempoActual + promocion.getAtraccionA().getTiempo()
										+ promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
							listaAtracciones.add(promocion.getAtraccionA());
							listaAtracciones.add(promocion.getAtraccionB());
							costoActual += (int) promocion.retornarPromocion();
							user.recibirSugerencia(new AtraccionSugerida(listaAtracciones.toArray(new Atraccion[0]),
									promocion, costoActual));
						}
					}
					if (promocion.getClass() == PromocionPorcentaje.class) {
						valorTemporal = (int) (promocion.getAtraccionA().getCosto()
								+ promocion.getAtraccionB().getCosto()
								- ((promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto())
										* ((double) promocion.retornarPromocion() / 100)));
						if ((costoActual + valorTemporal <= user.getDineroDisponible())
								&& (tiempoActual + promocion.getAtraccionA().getTiempo()
										+ promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
							listaAtracciones.add(promocion.getAtraccionA());
							listaAtracciones.add(promocion.getAtraccionB());
							costoActual += valorTemporal;
							user.recibirSugerencia(new AtraccionSugerida(listaAtracciones.toArray(new Atraccion[0]),
									promocion, costoActual));
						}
					}
					if (promocion.getClass() == PromocionAxB.class) {
						Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
						if (atraccionAux.hayEspacio()) {
							valorTemporal = (int) (promocion.getAtraccionA().getCosto()
									+ promocion.getAtraccionB().getCosto());

							if ((costoActual + valorTemporal <= user.getDineroDisponible()) && (tiempoActual
									+ promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo()
									+ atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
								listaAtracciones.add(promocion.getAtraccionA());
								listaAtracciones.add(promocion.getAtraccionB());
								listaAtracciones.add(atraccionAux);
								costoActual += valorTemporal;
								user.recibirSugerencia(new AtraccionSugerida(listaAtracciones.toArray(new Atraccion[0]),
										promocion, costoActual));
							}
						}
					}
				}
			}
			listaAtracciones.clear();
		}

		// Ofrezco promociones pero que no son del mismo tipo.

		for (PromocionInterface promocion : promociones) {
			int costoActual = 0;
			double tiempoActual = 0;

			if (promocion.getAtraccionA().getTipo() != user.getTipoFavorito()) {
				if (promocion.getClass() == PromocionAbsoluta.class) {
					if ((costoActual + (int) promocion.retornarPromocion() <= user.getDineroDisponible())
							&& (tiempoActual + promocion.getAtraccionA().getTiempo()
									+ promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
						listaAtracciones.add(promocion.getAtraccionA());
						listaAtracciones.add(promocion.getAtraccionB());
						costoActual += (int) promocion.retornarPromocion();
						user.recibirSugerencia(new AtraccionSugerida(listaAtracciones.toArray(new Atraccion[0]),
								promocion, costoActual));
					}
				}
				if (promocion.getClass() == PromocionPorcentaje.class) {
					valorTemporal = (int) (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto()
							- ((promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto())
									* ((double) promocion.retornarPromocion() / 100)));
					if ((costoActual + valorTemporal <= user.getDineroDisponible())
							&& (tiempoActual + promocion.getAtraccionA().getTiempo()
									+ promocion.getAtraccionB().getTiempo() <= user.getTiempoDisponible())) {
						listaAtracciones.add(promocion.getAtraccionA());
						listaAtracciones.add(promocion.getAtraccionB());
						costoActual += valorTemporal;
						user.recibirSugerencia(new AtraccionSugerida(listaAtracciones.toArray(new Atraccion[0]),
								promocion, costoActual));
					}
				}
				if (promocion.getClass() == PromocionAxB.class) {
					valorTemporal = (int) (promocion.getAtraccionA().getCosto() + promocion.getAtraccionB().getCosto());
					Atraccion atraccionAux = (Atraccion) promocion.retornarPromocion();
					if ((costoActual + valorTemporal <= user.getDineroDisponible()) && (tiempoActual
							+ promocion.getAtraccionA().getTiempo() + promocion.getAtraccionB().getTiempo()
							+ atraccionAux.getTiempo() <= user.getTiempoDisponible())) {
						listaAtracciones.add(promocion.getAtraccionA());
						listaAtracciones.add(promocion.getAtraccionB());
						listaAtracciones.add(atraccionAux);
						costoActual += valorTemporal;
						user.recibirSugerencia(new AtraccionSugerida(listaAtracciones.toArray(new Atraccion[0]),
								promocion, costoActual));
					}
				}
			}
			listaAtracciones.clear();
		}
	}

//----------------------------------------------------------------------------//

	private void sugerirAtracciones(Usuario user) {
		int costoActual = 0;
		double tiempoActual = 0;
		Atraccion[] atraccionesTipo = obtenerAtraccionesTipo(user.getTipoFavorito());

		for (Atraccion atraccion : atraccionesTipo) {
			if ((atraccion != null) && !user.getAtracciones().contains(atraccion) && atraccion.hayEspacio()
					&& (costoActual + atraccion.getCosto() <= user.getDineroDisponible())
					&& (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
				user.recibirSugerencia(
						new AtraccionSugerida(new Atraccion[] { atraccion }, null, (int) atraccion.getCosto()));
				// user.recibirSugerencia(new Sugerencia(new Atraccion[] { atraccion }, null,
				// atraccion.getCosto())); //este es el antiguo
			}
		}

		// Incluyo las atracciones que no son del mismo tipo
		for (Atraccion atraccion : atracciones) {
			if ((atraccion != null) && !user.getAtracciones().contains(atraccion) && atraccion.hayEspacio()
					&& (costoActual + atraccion.getCosto() <= user.getDineroDisponible())
					&& (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
				user.recibirSugerencia(
						new AtraccionSugerida(new Atraccion[] { atraccion }, null, (int) atraccion.getCosto()));
				// user.recibirSugerencia(new Sugerencia(new Atraccion[] { atraccion }, null,
				// atraccion.getCosto())); //este es el antiguo
			}
		}
	}

//----------------------------------------------------------------------------//

	public boolean agregarUsuario(Usuario user) {
		if (!usuarios.contains(user)) {
			usuarios.add(user);
			System.out.println("\n" + "----------------------------------------------------"
					+ "----------------------------------------------------"
					+ "----------------------------------------------------");
			System.out.println("Usuario DNI numero: " + user.getId());
			sugerirPromociones(user);
			sugerirAtracciones(user);
			return true;
		}
		return false;
	}

//----------------------------------------------------------------------------//

	public boolean removerUsuario(int DNI) {
		if (usuarios.contains(new Usuario(DNI, ENUMTIPO.AVENTURA, 0, 0))) {
			Usuario aux = usuarios.remove(usuarios.indexOf(new Usuario(DNI, ENUMTIPO.AVENTURA, 0, 0)));
			for (Atraccion atraccion : aux.getAtracciones()) {
				atraccion.liberarUnLugar();
			}
			return true;
		}
		return false;
	}

	private Atraccion[] obtenerAtraccionesTipo(ENUMTIPO tipo) {
		List<Atraccion> listaAtraccion = new ArrayList<>();
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getTipo() == tipo) {
				listaAtraccion.add(atraccion);
			}
		}
		return listaAtraccion.toArray(new Atraccion[0]);
	}

	
	public static void main(String[] args)  {
		Sistema sistema = new Sistema();
		sistema.cargarUsuario();
		sistema.cargarAtracciones();
		sistema.cargarPromociones();
		System.out.println(usuarios);
		System.out.println(atracciones);
		System.out.println(promociones);
		
		Usuario hobbit1 = new Usuario(10, ENUMTIPO.PAISAJE, 15, 6);
		sistema.sugerirAtracciones(hobbit1);
		
		
		
	}


	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}
}
