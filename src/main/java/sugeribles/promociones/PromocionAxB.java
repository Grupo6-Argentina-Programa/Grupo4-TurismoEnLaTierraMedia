package main.java.sugeribles.promociones;

import main.java.sugeribles.Atraccion;

public class PromocionAxB extends Promocion implements IPromocion {
    private int atraccionRegalo;
    
    public PromocionAxB(int id, int tipo, Atraccion atraccionA, Atraccion atraccionB, int atraccionRegalo, String porcentaje, String total) throws Exception {
		super(id, 2, atraccionA, atraccionB, atraccionRegalo, porcentaje, total);
    }

    @Override
    public Object retornarPromocion() {
        return atraccionRegalo;
    }
}
