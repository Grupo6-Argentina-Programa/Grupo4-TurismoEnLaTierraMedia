package modelos;

import java.util.List;
import java.util.Objects;

import java.util.ArrayList;
import modelosEnum.ENUMTIPO;

public class Promocion implements Comparable<Promocion> {

	private final int Id;
	private final String nombre;
	private final int tipoDePromocion;
	private final int descuentoPorcentual;

	private double costoTotal;
	private double duracionTotal;

	List<Atraccion> atracciones = new ArrayList<>();
	private ENUMTIPO preferencia = ENUMTIPO.SinDefinir;

	// TIPO DE PROMOCION 1)PAbsoluta, 2)PPorcentual, 3) PAxB

	// EL ID SOLO SE UTILIZA POR DAO
	public Promocion(int Id, String nombre, int tipoDePromocion, double costo, int descuentoPorcentual,
			List<Atraccion> atracciones) {

		this.Id = Id;
		this.nombre = nombre;
		this.tipoDePromocion = tipoDePromocion;
		this.descuentoPorcentual = descuentoPorcentual;

		this.atracciones = atracciones;

		calcularDuracion();
		buscarPreferencia();
		calcularCosto(costo);
	}

	public Promocion() {
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
		return costoTotal;
	}

	public void setCosto(double costo) {
		this.costoTotal = costo;
	}

	public double getDuracion() {
		return duracionTotal;
	}

	public void setDuracion(double duracion) {
		this.duracionTotal = duracion;
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
		calcularCosto(costoTotal);
	}

	private void calcularCosto(double costo) {

		double costoTotal = 0;
		double bonificacion = 0;

		switch (this.tipoDePromocion) {
		case 1:
			this.costoTotal = costo;
			break;

		case 2:
			for (Atraccion i : this.atracciones) {
				costoTotal += i.getCosto();
			}
			bonificacion = (this.descuentoPorcentual * costoTotal) / 100;
			this.costoTotal = costoTotal - bonificacion;
			break;

		case 3:
			for (Atraccion i : this.atracciones) {
				costoTotal += i.getCosto();
				bonificacion = i.getCosto();
			}
			this.costoTotal = costoTotal - bonificacion;
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
		this.duracionTotal = duracionTotal;
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
		return Objects.hash(Id, atracciones, costoTotal, descuentoPorcentual, duracionTotal, nombre, preferencia,
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
		Promocion other = (Promocion) obj;
		return Id == other.Id && Objects.equals(atracciones, other.atracciones)
				&& Double.doubleToLongBits(costoTotal) == Double.doubleToLongBits(other.costoTotal)
				&& descuentoPorcentual == other.descuentoPorcentual
				&& Double.doubleToLongBits(duracionTotal) == Double.doubleToLongBits(other.duracionTotal)
				&& Objects.equals(nombre, other.nombre) && preferencia == other.preferencia
				&& tipoDePromocion == other.tipoDePromocion;
	}

	@Override
	public String toString() {
		return "Promocion2 [Id=" + Id + ", nombre=" + nombre + ", tipoDePromocion=" + tipoDePromocion
				+ ", descuentoPorcentual=" + descuentoPorcentual + ", costo=" + costoTotal + ", duracion="
				+ duracionTotal + ", atracciones=" + atracciones + ", preferencia=" + preferencia + "]";
	}

	@Override
	public int compareTo(Promocion o) {
		if (this.costoTotal == o.getCosto()) {
			return Double.compare(this.duracionTotal, o.getDuracion());
		}
		return Double.compare(this.costoTotal, o.getCosto());
	}

}
