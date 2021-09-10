package general;

import java.util.Objects;

public class Atraccion implements Comparable<Atraccion>{
    private String nombre;
    private int costo;
    private double tiempo;
    private int cupo;
    private ENUMTIPO tipo;

    public Atraccion(String nombre, int costo, double tiempo, int cupo, ENUMTIPO tipo) {
        this.nombre = nombre;
        this.costo = costo;
        this.tiempo = tiempo;
        this.cupo = cupo;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public double getTiempo() {
        return tiempo;
    }

    public int getCupo() {
        return cupo;
    }

    public ENUMTIPO getTipo() {
        return tipo;
    }

    public void setTipo(ENUMTIPO tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Atraccion[" + "nombre: " + nombre + ", costo: " + costo + ", tiempo: " + tiempo + ", cupo: " + cupo + ", tipo: " + tipo + "]\n";
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
}
