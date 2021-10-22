package main.java.sugeribles.promociones;

import main.java.sugeribles.Atraccion;

public interface IPromocion extends Comparable<IPromocion> {
    Object retornarPromocion();
    Atraccion getAtraccionA();
    Atraccion getAtraccionB();
}
