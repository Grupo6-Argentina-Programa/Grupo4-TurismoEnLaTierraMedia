package main.java.usuarios;

import main.java.sugeribles.Sugerencia;
import main.java.sugeribles.Atraccion;
import main.java.enumeradores.ENUMTIPO;

import java.util.*;

public class Usuario {
	
	private final int DNI;
	private String usuario;
	private String contraseña;
	private double dineroInicial;	//combinar
	private double dineroDisponible;
	private double tiempoInicial;	//combinar
	private double tiempoDisponible;
	
	private int posicionX;
	private int posicionY;
	
	private int costoTotal = 0;
	private ENUMTIPO tipoFavorito = ENUMTIPO.SinDefinir;
	private final List<Atraccion> atracciones = new ArrayList<>();

	public Usuario(int ID_Usuario, String usuario, String contraseña, double dineroDisponible, double tiempoDisponible, int posicionX, int posicionY) {
		this.DNI = ID_Usuario;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.dineroInicial = dineroDisponible;
		this.dineroDisponible = dineroDisponible;
		this.tiempoInicial = tiempoDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	
	public Usuario(int DNI, ENUMTIPO tipoFavorito, int dineroInicial, double tiempoDisponible) {
		this.DNI = DNI;
		this.tipoFavorito = tipoFavorito;
		this.dineroInicial = dineroInicial;
		this.dineroDisponible = dineroInicial;
		this.tiempoInicial = tiempoDisponible;
		this.tiempoDisponible = tiempoDisponible;	
	}
	
	public Usuario(int ID_Usuario, int dineroInicial, double tiempoDisponible) {
		this.DNI = ID_Usuario;
		this.dineroInicial = dineroInicial;
		this.dineroDisponible = dineroInicial;
		this.tiempoInicial = tiempoDisponible;
		this.tiempoDisponible = tiempoDisponible;
	}

	public int getDNI() {
		return DNI;
	}

	public ENUMTIPO getTipoFavorito() {
		return tipoFavorito;
	}

	public double getDineroDisponible() {
		return dineroDisponible;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	// Nuevos añadidos //
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public void setTipoFavorito(ENUMTIPO tipoFavorito) {
		this.tipoFavorito = tipoFavorito;
	}

	// fin Nuevos añadididos //
	
	

	public void recibirSugerencia(Sugerencia sugerencia) {
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
					"\nÂ¿Desea aceptar la sugerencia?\nIngrese \"Si\" para aceptar, de lo contrario ingrese \"No\" para rechazarlo.");
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



	private int calcularDifereciaMonedas(Sugerencia sugerencia) {
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
		return DNI == usuario.DNI;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DNI);
	}

	public int getCostoTotal() {
		return costoTotal;
	}

	public double getDineroInicial() {
		return dineroInicial;
	}

	public double getTiempoInicial() {
		return tiempoInicial;
	}

	private static String ingresarDatoStr() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	
	@Override
	public String toString() {
		return "Usuario [DNI=" + DNI + ", usuario=" + usuario + ", contraseña=" + contraseña + ", dineroInicial="
				+ dineroInicial + ", dineroDisponible=" + dineroDisponible + ", tiempoInicial=" + tiempoInicial
				+ ", tiempoDisponible=" + tiempoDisponible + ", posicionX=" + posicionX + ", posicionY=" + posicionY
				+ ", costoTotal=" + costoTotal + ", tipoFavorito=" + tipoFavorito + ", atracciones=" + atracciones
				+ "]";
	}
}
