package main.java.sugeribles;

import main.java.enumeradores.ENUMTIPO;

import java.util.Objects;

public class Atraccion implements Comparable<Atraccion> {
	private int id;
    private String nombre;
    private int costo;
    private final double tiempo;
    private final int cupo;
    private int puestosOcupados;
    private ENUMTIPO tipo;
    private int posicionX;
    private int posicionY;

    

	public Atraccion(String nombre, int costo, double tiempo, int cupo, ENUMTIPO tipo) {
        this.nombre = nombre;
        this.costo = costo;
        this.tiempo = tiempo;
        this.cupo = cupo;
        this.tipo = tipo;
        puestosOcupados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCosto() {
        return costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public ENUMTIPO getTipo() {
        return tipo;
    }
    

    public double getId() {
        return id;
    }
    
    
    public int getPuestosOcupados() {
		return puestosOcupados;
	}

	public void setPuestosOcupados(int puestosOcupados) {
		this.puestosOcupados = puestosOcupados;
	}

	public int getCupo() {
		return cupo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public void setTipo(ENUMTIPO tipo) {
		this.tipo = tipo;
	}
	public int getPosicionX() {
		return posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	@Override
    public String toString() {
        return "Nombre: " + nombre + ", costo: " + costo + ", tiempo: " + tiempo + ", cupo: " + cupo + ", tipo: " + tipo ;
    }

    @Override
    public int compareTo(Atraccion atraccion) {
        if (this.costo == atraccion.getCosto()){
            return Double.compare(this.tiempo, atraccion.getTiempo());
        }
        return Integer.compare(this.costo, atraccion.getCosto());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atraccion atraccion = (Atraccion) o;
        return costo == atraccion.costo && Double.compare(atraccion.tiempo, tiempo) == 0 && cupo == atraccion.cupo && Objects.equals(nombre, atraccion.nombre) && tipo == atraccion.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, costo, tiempo, cupo, tipo);
    }

    public boolean hayEspacio(){
        return puestosOcupados < cupo;
    }

    public void ocuparUnLugar(){
        puestosOcupados++;
    }

    public void liberarUnLugar(){
        if (puestosOcupados > 0){
            puestosOcupados--;
        }
    }
}
