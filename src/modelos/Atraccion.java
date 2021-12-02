package modelos;

import java.util.Objects;

import modelosEnum.ENUMTIPO;

public class Atraccion implements Comparable<Atraccion> {

	private int id;
	private final String nombre;
	private final double costo;
	private final double duracion;

	private int cupoActual = 0;
	private final int cupoMaximo;

	private final int posicionX;
	private final int posicionY;

	private ENUMTIPO preferencia = ENUMTIPO.SinDefinir;

	// CONSTRUCTOR SOLO USADO POR DAO
	public Atraccion(int id, String nombre, double costo, double duracion, int cupoActual, int cupoMaximo,
			int posicionX, int posicionY) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoActual = cupoActual;
		this.cupoMaximo = cupoMaximo;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	// Constructor por defecto
	public Atraccion(String nombre, double costo, double duracion, int cupoMaximo, int posicionX, int posicionY) {
		this.id = 0;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoMaximo = cupoMaximo;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}

	// DEPURAR
	public Atraccion(String nombre, int costo, double tiempo, int cupo, ENUMTIPO tipo) {
		this.id = 0;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = tiempo;
		this.cupoMaximo = cupo;
		this.preferencia = tipo;
		this.posicionX = 0;
		this.posicionY = 0;
		cupoActual = 0;
	}

////////////////////////////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public double getCosto() {
		return costo;
	}

	public int getCupoActual() {
		return cupoActual;
	}

	public void setCupoActual(int cupoActual) {
		this.cupoActual = cupoActual;
	}

	public ENUMTIPO getTipo() {
		return preferencia;
	}

	public void setPreferencia(ENUMTIPO tipoFavorito) {
		this.preferencia = tipoFavorito;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public double getDuracion() {
		return duracion;
	}

	public int getCupoMaximo() {
		return cupoMaximo;
	}

////////////////////////////////////////////////////////////////////////////////

	public boolean hayEspacio() {
		return cupoActual < cupoMaximo;
	}

	public void ocuparUnLugar() {
		cupoActual++;
	}

	public void liberarUnLugar() {
		if (cupoActual > 0) {
			cupoActual--;
		}
	}

////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Atraccion atraccion = (Atraccion) o;
		return costo == atraccion.costo && Double.compare(atraccion.duracion, duracion) == 0
				&& cupoMaximo == atraccion.cupoMaximo && Objects.equals(nombre, atraccion.nombre)
				&& preferencia == atraccion.preferencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, costo, duracion, cupoMaximo, preferencia);
	}

	@Override
	public int compareTo(Atraccion atraccion) {
		if (this.costo == atraccion.getCosto()) {
			return Double.compare(this.duracion, atraccion.getDuracion());
		}
		return Double.compare(this.costo, atraccion.getCosto());
	}

}
