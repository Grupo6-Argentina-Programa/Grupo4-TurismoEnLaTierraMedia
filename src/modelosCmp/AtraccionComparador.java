package modelosCmp;

import java.util.Comparator;

import modelos.Atraccion;

public class AtraccionComparador implements Comparator<Atraccion> {

    @Override
    public int compare(Atraccion atraccionA, Atraccion atraccionB) {
        return (int) Math.signum(atraccionB.compareTo(atraccionA));
    }
}
