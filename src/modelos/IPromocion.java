package modelos;

public interface IPromocion extends Comparable<IPromocion> {
    Object retornarPromocion();
    Atraccion getAtraccionA();
    Atraccion getAtraccionB();
}
