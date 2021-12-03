package modelos;

import java.util.List;
import java.util.Objects;

import java.util.ArrayList;
import modelosEnum.ENUMTIPO;

public class Promocion2 {

	private final int Id;
	private final String nombre;
	private final int tipoDePromocion;
	private final int descuentoPorcentual;

	private double costo;
	private double duracion;

	List<Atraccion> atracciones = new ArrayList<>();
	private ENUMTIPO preferencia = ENUMTIPO.SinDefinir;

	// TIPO DE PROMOCION 1)PAbsoluta, 2)PPorcentual, 3) PAxB

	// EL ID SOLO SE UTILIZA POR DAO
	public Promocion2(int Id, String nombre, int tipoDePromocion, double costo, int descuentoPorcentual,
			List<Atraccion> atracciones) {

		this.Id = Id;
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.descuentoPorcentual = descuentoPorcentual;

		this.atracciones = atracciones;
		System.out.println(atracciones);

		calcularDuracion();
		buscarPreferencia();
		calcularCosto(costo);
	}

	public Promocion2() {
		this.Id = 0;
		this.tipoDePromocion = 0;
		this.nombre = "SinNombre";
		this.descuentoPorcentual = 0;
	}

////////////////////////////////////////////////////////////////////////////////

	public int getId() {
		return Id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTipoDePromocion() {
		return tipoDePromocion;
	}

	public int getDescuentoPorcentual() {
		return descuentoPorcentual;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public ENUMTIPO getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(ENUMTIPO preferencia) {
		this.preferencia = preferencia;
	}

////////////////////////////////////////////////////////////////////////////////

	public void agregarAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
		calcularDuracion();
		buscarPreferencia();
		calcularCosto(costo);
	}

	private void calcularCosto(double costo) {

		double costoTotal = 0;
		double bonificacion = 0;

		switch (this.tipoDePromocion) {
		case 1:
			this.costo = costo;
			break;

		case 2:
			for (Atraccion i : this.atracciones) {
				costoTotal += i.getCosto();
			}
			bonificacion = (this.descuentoPorcentual * costoTotal) / 100;
			this.costo = costoTotal - bonificacion;
			break;

		case 3:
			for (Atraccion i : this.atracciones) {
				costoTotal += i.getCosto();
				bonificacion = i.getCosto();
			}
			this.costo = costoTotal - bonificacion;
			break;

		default:
			throw new Error("Entrada Invalida.");
		}
	}

	private void calcularDuracion() {
		double duracionTotal = 0;
		for (Atraccion atraccion : this.atracciones) {
			duracionTotal += atraccion.getDuracion();
		}
		this.duracion = duracionTotal;
	}

	private void buscarPreferencia() {
		boolean first = true;
		for (Atraccion atraccion : this.atracciones) {
			if (first) {
				this.preferencia = atraccion.getTipo();
				first = false;
			}
			if (this.preferencia != atraccion.getTipo()) {
				throw new Error("Los Tipos De las Atracciones no coinciden");
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////

	@Override
	public int hashCode() {
		return Objects.hash(Id, atracciones, costo, descuentoPorcentual, duracion, nombre, preferencia,
				tipoDePromocion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion2 other = (Promocion2) obj;
		return Id == other.Id && Objects.equals(atracciones, other.atracciones)
				&& Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& descuentoPorcentual == other.descuentoPorcentual
				&& Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& Objects.equals(nombre, other.nombre) && preferencia == other.preferencia
				&& tipoDePromocion == other.tipoDePromocion;
	}

	@Override
	public String toString() {
		return "Promocion2 [Id=" + Id + ", nombre=" + nombre + ", tipoDePromocion=" + tipoDePromocion
				+ ", descuentoPorcentual=" + descuentoPorcentual + ", costo=" + costo + ", duracion=" + duracion
				+ ", atracciones=" + atracciones + ", preferencia=" + preferencia + "]";
	}

}
