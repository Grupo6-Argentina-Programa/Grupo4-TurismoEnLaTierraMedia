package modelosComp;

import java.util.Comparator;

import modelos.Atraccion;

public class ComparadorAtraccion implements Comparator<Atraccion> {

    @Override
    public int compare(Atraccion atraccionA, Atraccion atraccionB) {
        return (int) Math.signum(atraccionB.compareTo(atraccionA));
    }
}
