package modelos;

public class AtraccionSugerida {
    private final Atraccion[] atracciones;
    private final PromocionInterface promocion;
    private final int total;

    public AtraccionSugerida(Atraccion[] atracciones, PromocionInterface promocion, int total) {
        this.atracciones = atracciones;
        this.promocion = promocion;
        this.total = total;
    }

    public Atraccion[] getAtracciones() {
        return atracciones;
    }

    public PromocionInterface getPromocion() {
        return promocion;
    }

    public int getTotal() {
        return total;
    }
}