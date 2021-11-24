package modelos;

import java.util.*;

import DAO.DAOFactory;
import DAO.UsuarioDAO;
import modelosEnum.ENUMTIPO;

public class Usuario {

	private final int id;
	private String usuario;
	private String contrasenia;
	private double dineroInicial; // combinar
	private double dineroDisponible;
	private double tiempoInicial; // combinar
	private double tiempoDisponible;

	private int posicionX;
	private int posicionY;

	private int costoTotal = 0;
	private ENUMTIPO tipoFavorito = ENUMTIPO.SinDefinir;
	private final List<Atraccion> atracciones = new ArrayList<>();

	//Constructor por defecto
	public Usuario(String usuario, String contrasenia, double dineroDisponible, double tiempoDisponible, int posicionX,
			int posicionY) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.dineroInicial = dineroDisponible;
		this.dineroDisponible = dineroDisponible;
		this.tiempoInicial = tiempoDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.posicionX = posicionX;
		this.posicionY = posicionY;

		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		this.id = userDAO.findLastID() + 1;
	}

	//CONSTRUCTOR SOLO USADO POR DAO
	public Usuario(int id, String usuario, String contrasenia, double dineroDisponible, double tiempoDisponible,
			int posicionX, int posicionY) {
		this.id = id;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.dineroInicial = dineroDisponible;
		this.dineroDisponible = dineroDisponible;
		this.tiempoInicial = tiempoDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	//DEPURAR
	public Usuario(int DNI, ENUMTIPO tipoFavorito, int dineroInicial, double tiempoDisponible) {
		this.id = DNI;
		this.tipoFavorito = tipoFavorito;
		this.dineroInicial = dineroInicial;
		this.dineroDisponible = dineroInicial;
		this.tiempoInicial = tiempoDisponible;
		this.tiempoDisponible = tiempoDisponible;
	}

////////////////////////////////////////////////////////////////////////////////
	public int getId() {
		return id;
	}

	public  String getUsuario() {
		return usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public double getDineroInicial() {
		return dineroInicial;
	}

	public double getDineroDisponible() {
		return dineroDisponible;
	}

	public double getTiempoInicial() {
		return tiempoInicial;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public int getCostoTotal() {
		return costoTotal;
	}

	public ENUMTIPO getTipoFavorito() {
		return tipoFavorito;
	}

////////////////////////////////////////////////////////////////////////////////

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public void setDineroDisponible(double dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public void setTipoFavorito(ENUMTIPO tipoFavorito) {
		this.tipoFavorito = tipoFavorito;
	}

////////////////////////////////////////////////////////////////////////////////

	public void recibirSugerencia(AtraccionSugerida sugerencia) {
		System.out.println("\n\nSe ha hecho la siguiente sugerencia:\n");
		if (sugerencia.getPromocion() == null) {
			System.out.println("La atraccion:\n" + sugerencia.getAtracciones()[0] + "\nTotal a pagar: "
					+ sugerencia.getTotal() + " monedas");
		} else {
			StringBuilder concatenado = new StringBuilder();
			for (Atraccion atraccion : sugerencia.getAtracciones()) {
				concatenado.append(atraccion).append("\n");
			}
			System.out.println(
					"La promocion es de tipo: " + sugerencia.getPromocion().getClass().toString().split("\\.")[2]
							+ "\nLas atracciones:\n" + concatenado + "\nTotal a pagar: " + sugerencia.getTotal()
							+ " monedas." + "\nSe ahorra " + calcularDifereciaMonedas(sugerencia) + " monedas.");
		}
		String aceptar;
		do {
			System.out.println(
					"\n¿Desea aceptar la sugerencia?\nIngrese \"Si\" para aceptar, de lo contrario ingrese \"No\" para rechazarlo.");
			aceptar = ingresarDatoStr();
			// aceptar = in.nextLine();
		} while (!aceptar.equalsIgnoreCase("Si") && !aceptar.equalsIgnoreCase("No"));
		if (aceptar.equalsIgnoreCase("Si")) {
			dineroDisponible -= sugerencia.getTotal();
			for (Atraccion atraccion : sugerencia.getAtracciones()) {
				tiempoDisponible -= atraccion.getTiempo();
				atraccion.ocuparUnLugar();
			}
			costoTotal += sugerencia.getTotal();
			Collections.addAll(atracciones, sugerencia.getAtracciones());
		}
	}

	private int calcularDifereciaMonedas(AtraccionSugerida sugerencia) {
		int valorOriginal = 0;
		for (int i = 0; i < sugerencia.getAtracciones().length; i++) {
			valorOriginal += sugerencia.getAtracciones()[i].getCosto();
		}
		return valorOriginal - sugerencia.getTotal();
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Usuario usuario = (Usuario) o;
		return id == usuario.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	private static String ingresarDatoStr() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}

	@Override
	public String toString() {
		return "Usuario [DNI=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", dineroInicial="
				+ dineroInicial + ", dineroDisponible=" + dineroDisponible + ", tiempoInicial=" + tiempoInicial
				+ ", tiempoDisponible=" + tiempoDisponible + ", posicionX=" + posicionX + ", posicionY=" + posicionY
				+ ", costoTotal=" + costoTotal + ", tipoFavorito=" + tipoFavorito + ", atracciones=" + atracciones
				+ "]";
	}
}
