package modelos;

import java.util.Objects;

import DAO.AtraccionDAO;
import DAO.DAOFactory;
import modelosEnum.ENUMTIPO;

public class Atraccion implements Comparable<Atraccion> {
	private int id;
	private String nombre;
	private double costo;
	private final double duracion;
	private final int cupoMaximo;
	private int cupoActual;
	private ENUMTIPO tipo;
	private int posicionX;
	private int posicionY;

	// Constructor por defecto
	public Atraccion(String nombre, double costo, double duracion, int cupoActual, int cupoMaximo) {
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoActual = cupoActual;
		this.cupoMaximo = cupoMaximo;

		AtraccionDAO AttrDAO = DAOFactory.getAtraccionDAO();
		this.id = AttrDAO.findLastID() + 1;
	}

	// CONSTRUCTOR SOLO USADO POR DAO
	public Atraccion(int id, String nombre, double costo, double duracion, int cupoActual, int cupoMaximo) {
		this.id = id;
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoActual = cupoActual;
		this.cupoMaximo = cupoMaximo;
	}

	// DEPURAR
	public Atraccion(String nombre, int costo, double tiempo, int cupo, ENUMTIPO tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.duracion = tiempo;
		this.cupoMaximo = cupo;
		this.tipo = tipo;
		cupoActual = 0;
	}

////////////////////////////////////////////////////////////////////////////////

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getCupoActual() {
		return cupoActual;
	}

	public void setCupoActual(int cupoActual) {
		this.cupoActual = cupoActual;
	}

	public ENUMTIPO getTipo() {
		return tipo;
	}

	public void setTipo(ENUMTIPO tipo) {
		this.tipo = tipo;
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
				&& tipo == atraccion.tipo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, costo, duracion, cupoMaximo, tipo);
	}

	@Override
	public int compareTo(Atraccion atraccion) {
		if (this.costo == atraccion.getCosto()) {
			return Double.compare(this.duracion, atraccion.getDuracion());
		}
		return Double.compare(this.costo, atraccion.getCosto());
	}

}
