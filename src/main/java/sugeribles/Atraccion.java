package main.java.sugeribles;

import main.java.DAO.AtraccionDAO;
import main.java.DAO.DAOFactory;
import main.java.enumeradores.ENUMTIPO;

import java.util.Objects;

public class Atraccion implements Comparable<Atraccion> {
	private int id;
    private String nombre;
    private  double costo;
    private final double tiempo;
    private final int cupoMaximo;
    private int puestosOcupados;
    private ENUMTIPO tipo;
    private int posicionX;
    private int posicionY;

  //Constructor por defecto
    public Atraccion(String nombre, double costo, double tiempo, int puestosOcupados, int cupoMaximo) {
    	this.nombre = nombre;
        this.costo = costo;
        this.tiempo = tiempo;
        this.puestosOcupados = puestosOcupados;
		this.cupoMaximo = cupoMaximo;
		
		AtraccionDAO AttrDAO = DAOFactory.getAtraccionDAO();
		this.id = AttrDAO.findMaxID() + 1;
    }
    
  //CONSTRUCTOR SOLO USADO POR DAO
    public Atraccion(int id, String nombre, double costo, double tiempo, int puestosOcupados, int cupoMaximo) {
    	this.id = id;
    	this.nombre = nombre;
        this.costo = costo;
        this.tiempo = tiempo;
        this.puestosOcupados = puestosOcupados;
		this.cupoMaximo = cupoMaximo;
    }
    
    //DEPURAR
	public Atraccion(String nombre, int costo, double tiempo, int cupo, ENUMTIPO tipo) {
        this.nombre = nombre;
        this.costo = costo;
        this.tiempo = tiempo;
        this.cupoMaximo = cupo;
        this.tipo = tipo;
        puestosOcupados = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosto() {
        return costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public ENUMTIPO getTipo() {
        return tipo;
    }
    

    public int getId() {
        return id;
    }
    
    
    public int getPuestosOcupados() {
		return puestosOcupados;
	}

	public void setPuestosOcupados(int puestosOcupados) {
		this.puestosOcupados = puestosOcupados;
	}

	public int getCupo() {
		return cupoMaximo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCosto(double costo) {
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
        return "Nombre: " + nombre + ", costo: " + costo + ", tiempo: " + tiempo + ", cupo: " + cupoMaximo + ", tipo: " + tipo ;
    }

    @Override
    public int compareTo(Atraccion atraccion) {
        if (this.costo == atraccion.getCosto()){
            return Double.compare(this.tiempo, atraccion.getTiempo());
        }
        return Double.compare(this.costo, atraccion.getCosto());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atraccion atraccion = (Atraccion) o;
        return costo == atraccion.costo && Double.compare(atraccion.tiempo, tiempo) == 0 && cupoMaximo == atraccion.cupoMaximo && Objects.equals(nombre, atraccion.nombre) && tipo == atraccion.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, costo, tiempo, cupoMaximo, tipo);
    }

    public boolean hayEspacio(){
        return puestosOcupados < cupoMaximo;
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
