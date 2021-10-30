package main.java.sistema;

import main.java.DAO.AtraccionDAO;
import main.java.DAO.DAOFactory;
import main.java.DAO.UsuarioDAO;
import main.java.comparadores.ComparadorAtraccion;
import main.java.comparadores.ComparadorPromocion;
import main.java.sugeribles.Atraccion;
import main.java.enumeradores.ENUMTIPO;
import main.java.sugeribles.Sugerencia;
import main.java.sugeribles.promociones.IPromocion;
import main.java.sugeribles.promociones.PromocionAbsoluta;
import main.java.sugeribles.promociones.PromocionAxB;
import main.java.sugeribles.promociones.PromocionPorcentaje;
import main.java.usuarios.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.sistema.*;
import Aplicacion.SugerenciaPorTiempo;

public class Sistema {
	private final List<Usuario> usuarios;
	private final List<Atraccion> atracciones;
	private final List<IPromocion> promociones;

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

	public static void main(String[] args) throws Exception {

		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		Pantalla consola = new Pantalla();

		int datoInt;
		int idBuscado;
		Usuario localUser;

		int estado = 1;

		boolean salir = false;

		System.out.println("Ingrese el Id de usuario correspondiente : ");
	
		System.out.println("Ingrese '0' para finalizar el programa. : ");

		do {

			datoInt = ingresarDatoInt();

			if (datoInt == 0)
				break;

			localUser = userDAO.findById(datoInt);
			idBuscado = localUser.getId();

			if (datoInt == idBuscado) {
				estado++;
			}

			switch (estado) {
			case 0:
				salir = true;
				System.out.println("Programa finalizado, garcias por Testear.");
				break;
			case 1:
				System.out.println("Id no encontrado, digite nuevamente, '0' para salir.");
				break;
			case 2:
				System.out.println("Bienvenido Usuario : " + localUser.getUsuario() + " .");
				System.out.println("Ingrese 1 -> Para Ver estado");
				System.out.println("Ingrese 2 -> Para Ver Itinerario");
				System.out.println("Ingrese 3 -> Para Suguerir promociones y atracciones");
				System.out.println("Ingrese 4 -> Para añdir mas dinero.");
				System.out.println("Ingrese 5 -> Para añdir mas tiempo.");
				System.out.println("Ingrese 0 -> Para salir");
				do {
					datoInt = ingresarDatoInt();
					switch (datoInt) {
					case 0:
						System.out.println("Ingrese el Id de usuario correspondiente : ");
						System.out.println("Ingrese '0' para finalizar el programa. : ");
						salir = true;
						break;
					case 1:
						
						
						System.out.println(userDAO.findById(datoInt));
						break;
					case 2:
						
					 break;
					case 3:
						consola.mostrarLasAtracciones();
						consola.mostrarLasPromociones();;
						break;
					case 4:
						consola.agregarDinero();
						break;
					case 5:
						break;
					default:
						System.out.println("Opcion Ingresada Incorrecta, digite neuvamnete.");
						break;
					}

				} while (!salir);
				salir = false;
				break;
			}

		} while (!salir);
		System.out.println("Programa finalizado, garcias por Testear.");
	}

	public Sistema(String directorioUsuariosCSV, String directorioAtraccionesCSV, String directorioPromocionesCSV)
			throws Exception {
		this.usuarios = new ArrayList<>();
		this.atracciones = new ArrayList<>();
		this.promociones = new ArrayList<>();
		agregarUsuariosCSV(new LectorCSV(directorioUsuariosCSV).getListaCSV());
		agregarAtraccionesCSV(new LectorCSV(directorioAtraccionesCSV).getListaCSV());
		agregarPromocionesCSV(new LectorCSV(directorioPromocionesCSV).getListaCSV());
		atracciones.sort(new ComparadorAtraccion());
		promociones.sort(new ComparadorPromocion());

		/*
		 * //Se Usa para sugerir de entrada for (Usuario usuario : usuarios) {
		 * System.out.println("\n"+
		 * "----------------------------------------------------"
		 * +"----------------------------------------------------"
		 * +"----------------------------------------------------");
		 * System.out.println("Usuario DNI numero: " + usuario.getDNI());
		 * sugerirPromociones(usuario); sugerirAtracciones(usuario); }
		 */
	}

//----------------------------------------------------------------------------//

	public Sistema(List<Usuario> usuarios, List<Atraccion> atracciones, List<IPromocion> promociones)
			throws IOException {
		this.usuarios = usuarios;
		this.atracciones = atracciones;
		this.promociones = promociones;
		this.atracciones.sort(new ComparadorAtraccion());
		this.promociones.sort(new ComparadorPromocion());

		for (Usuario usuario : usuarios) {
			System.out.println("\n" + "----------------------------------------------------"
					+ "----------------------------------------------------"
					+ "----------------------------------------------------");
			System.out.println("Usuario DNI numero: " + usuario.getId());
			sugerirPromociones(usuario);
			sugerirAtracciones(usuario);
		}

		exportarUsuarios();
	}

//----------------------------------------------------------------------------//

	private void sugerirPromociones(Usuario user) {
		List<Atraccion> listaAtracciones = new ArrayList<>();
		int valorTemporal;

		for (IPromocion promocion : promociones) {
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
							user.recibirSugerencia(
									new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
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
							user.recibirSugerencia(
									new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
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
								user.recibirSugerencia(new Sugerencia(listaAtracciones.toArray(new Atraccion[0]),
										promocion, costoActual));
							}
						}
					}
				}
			}
			listaAtracciones.clear();
		}

		// Ofrezco promociones pero que no son del mismo tipo.

		for (IPromocion promocion : promociones) {
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
						user.recibirSugerencia(
								new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
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
						user.recibirSugerencia(
								new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
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
						user.recibirSugerencia(
								new Sugerencia(listaAtracciones.toArray(new Atraccion[0]), promocion, costoActual));
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
				user.recibirSugerencia(new Sugerencia(new Atraccion[] { atraccion }, null, (int) atraccion.getCosto()));
				// user.recibirSugerencia(new Sugerencia(new Atraccion[] { atraccion }, null,
				// atraccion.getCosto())); //este es el antiguo
			}
		}

		// Incluyo las atracciones que no son del mismo tipo
		for (Atraccion atraccion : atracciones) {
			if ((atraccion != null) && !user.getAtracciones().contains(atraccion) && atraccion.hayEspacio()
					&& (costoActual + atraccion.getCosto() <= user.getDineroDisponible())
					&& (tiempoActual + atraccion.getTiempo() <= user.getTiempoDisponible())) {
				user.recibirSugerencia(new Sugerencia(new Atraccion[] { atraccion }, null, (int) atraccion.getCosto()));
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

//----------------------------------------------------------------------------//

	public void agregarUsuariosCSV(List<String> listaCSV) throws Exception {
		Pattern patronRegex = Pattern.compile("(\\d*), ?(.*), ?(\\d*), ?(\\d*(?:.\\d*)?)");
		for (String fila : listaCSV) {
			Matcher matcher = patronRegex.matcher(fila);
			if (matcher.matches()) {
				if (1 > Integer.parseInt(matcher.group(3))) {
					throw new Exception("El presupuesto es menor a 1 moneda.");
				}

				if (1 > Double.parseDouble(matcher.group(4))) {
					throw new Exception("El tiempo disponible es menor a 1 hora.");
				}

				if (usuarios.contains(new Usuario(Integer.parseInt(matcher.group(1)),
						ENUMTIPO.valueOf(matcher.group(2).toUpperCase()), Integer.parseInt(matcher.group(3)),
						Double.parseDouble(matcher.group(4))))) {
					throw new Exception("El usuario ya existe.");
				}
				usuarios.add(new Usuario(Integer.parseInt(matcher.group(1)),
						ENUMTIPO.valueOf(matcher.group(2).toUpperCase()), Integer.parseInt(matcher.group(3)),
						Double.parseDouble(matcher.group(4))));
			}
		}
	}

//----------------------------------------------------------------------------//

	public void agregarAtraccionesCSV(List<String> listaCSV) throws Exception {
		Pattern patronRegex = Pattern.compile("(.*), ?(\\d*), ?(\\d*), ?(\\d*), ?(.*)");
		for (String fila : listaCSV) {
			Matcher matcher = patronRegex.matcher(fila);
			if (matcher.matches()) {
				for (Atraccion atraccion : atracciones) {
					if (atraccion.getNombre().equals(matcher.group(1))) {
						throw new Exception("Ya existe una atraccion con ese nombre.");
					}
				}
				atracciones.add(new Atraccion(matcher.group(1), Integer.parseInt(matcher.group(2)),
						Double.parseDouble(matcher.group(3)), Integer.parseInt(matcher.group(4)),
						ENUMTIPO.valueOf(matcher.group(5).toUpperCase())));
			}
		}
	}

//----------------------------------------------------------------------------//

	public void agregarPromocionesCSV(List<String> listaCSV) throws Exception {
		Pattern patronRegex = Pattern.compile("(.*), ?(.*), ?(.*), ?(\\d*), ?(\\d*)");
		for (String fila : listaCSV) {
			Matcher matcher = patronRegex.matcher(fila);
			if (matcher.matches()) {
				Atraccion atraccionA = null;
				Atraccion atraccionB = null;
				if (!matcher.group(4).equals("0")) {
					for (Atraccion atraccionTemp : atracciones) {
						if (matcher.group(1).equals(atraccionTemp.getNombre())) {
							atraccionA = atraccionTemp;
						}
						if (matcher.group(2).equals(atraccionTemp.getNombre())) {
							atraccionB = atraccionTemp;
						}
						if (atraccionA != null && atraccionB != null) {
							break;
						}
					}
					if (atraccionA == null || atraccionB == null) {
						throw new Exception("Una de las atracciones brindadas en el CSV no existe.");
					}
					promociones.add(new PromocionAbsoluta(atraccionA, atraccionB, Integer.parseInt(matcher.group(4))));
				} else {
					if (!matcher.group(5).equals("0")) {
						for (Atraccion atraccionTemp : atracciones) {
							if (matcher.group(1).equals(atraccionTemp.getNombre())) {
								atraccionA = atraccionTemp;
							}
							if (matcher.group(2).equals(atraccionTemp.getNombre())) {
								atraccionB = atraccionTemp;
							}
							if (atraccionA != null && atraccionB != null) {
								break;
							}
						}
						if (atraccionA == null || atraccionB == null) {
							throw new Exception("Una de las atracciones brindadas en el CSV no existe");
						}
						promociones.add(
								new PromocionPorcentaje(atraccionA, atraccionB, Integer.parseInt(matcher.group(5))));
					} else {
						Atraccion atraccionC = null;
						for (Atraccion atraccionTemp : atracciones) {
							if (matcher.group(1).equals(atraccionTemp.getNombre())) {
								atraccionA = atraccionTemp;
							}
							if (matcher.group(2).equals(atraccionTemp.getNombre())) {
								atraccionB = atraccionTemp;
							}
							if (matcher.group(3).equals(atraccionTemp.getNombre())) {
								atraccionC = atraccionTemp;
							}
							if (atraccionA != null && atraccionB != null && atraccionC != null) {
								break;
							}
						}
						if (atraccionA == null || atraccionB == null || atraccionC == null) {
							throw new Exception("Una de las atracciones brindadas en el CSV no existe");
						}
						promociones.add(new PromocionAxB(atraccionA, atraccionB, atraccionC));
					}
				}
			}
		}
	}

//----------------------------------------------------------------------------//

	public void exportarUsuarios() throws IOException {
		PrintWriter printWriter = new PrintWriter(new FileWriter("usuariosExportados.txt"));
		for (Usuario usuario : usuarios) {
			double tiempoOcupado = 0;
			for (Atraccion atraccion : usuario.getAtracciones()) {
				tiempoOcupado += atraccion.getTiempo();
			}
			printWriter.println("El usuario DNI: " + usuario.getId() + ", le gustan las atracciones del tipo de "
					+ usuario.getTipoFavorito().toString() + ", ingresÃ³ con " + usuario.getDineroInicial()
					+ " monedas y " + usuario.getTiempoInicial() + " horas inicialmente, se retirÃ³ con "
					+ usuario.getDineroDisponible() + " monedas disponibles y " + usuario.getTiempoDisponible()
					+ " horas disponibles. GastÃ³ " + usuario.getCostoTotal() + " monedas y estuvo " + tiempoOcupado
					+ " horas en atracciones.");
		}
		printWriter.close();
	}

	public static void main2(String[] args) throws Exception {

		Sistema sistema = new Sistema("src/resources/usuarios.csv", "src/resources/atracciones.csv",
				"src/resources/promociones.csv");
		boolean salir = false;
		do {
			int eleccion;
			do {
				System.out.println("\n" + "Introduzca una de las opciones a continuacion para continuar.\n"
						+ "1- Agregar un usuario.\n" + "2- Remover un usuario.\n" + "3- Exportar los usuarios.\n"
						+ "4- Salir del sistema.\n");

				eleccion = ingresarDatoInt();
			} while (eleccion < 1 || eleccion > 4);

			switch (eleccion) {
			case 1: {
				int tempDNI;
				int tempMonedas;
				double tempTiempo;

				do {
					System.out.print("Ingrese el DNI: ");
					tempDNI = ingresarDatoInt();
				} while (tempDNI < 0);

				if (sistema.getUsuarios().contains(new Usuario(tempDNI, ENUMTIPO.AVENTURA, 0, 0))) {
					System.out.println("Ya existe un usuario con este DNI");
					break;
				}

				do {
					System.out.print("\nIngrese las monedas del usuario: ");
					tempMonedas = ingresarDatoInt();
				} while (tempMonedas < 0);

				do {
					System.out.print("\nIngrese el tiempo disponible del usuario: ");
					tempTiempo = ingresarDatoInt();
				} while (tempTiempo < 0);

				String tempTipo;
				do {
					System.out.print("\nIngrese el tipo favorito del usuario (Aventura, Degustacion o Paisaje.): ");
					//
					tempTipo = ingresarDatoStr();
				} while (!tempTipo.equalsIgnoreCase("Adventura") && !tempTipo.equalsIgnoreCase("Degustacion")
						&& !tempTipo.equalsIgnoreCase("Paisaje"));

				sistema.agregarUsuario(
						new Usuario(tempDNI, ENUMTIPO.valueOf(tempTipo.toUpperCase()), tempMonedas, tempTiempo));
				break;
			}

			case 2: {
				System.out.print("Ingrese el DNI del usuario a remover: ");
				int entradaI = ingresarDatoInt();//
				if (sistema.removerUsuario(entradaI)) {//
					System.out.println("\nUsuario removido con exito.");
				} else {
					System.out.println("\nEl usuario con ese DNI no existe.");
				}
				break;
			}

			case 3: {
				sistema.exportarUsuarios();
				System.out.println("Usuarios exportados.");
				break;
			}

			case 4: {
				salir = true;
				break;
			}
			}
		} while (!salir);
	}

//----------------------------------------------------------------------------//

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<IPromocion> getPromociones() {
		return promociones;
	}
}
