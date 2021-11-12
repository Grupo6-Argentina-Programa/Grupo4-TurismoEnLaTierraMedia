package modelos;

public class AtraccionS {
    private final Atraccion[] atracciones;
    private final IPromocion promocion;
    private final int total;

    public AtraccionS(Atraccion[] atracciones, IPromocion promocion, int total) {
        this.atracciones = atracciones;
        this.promocion = promocion;
        this.total = total;
    }

    public Atraccion[] getAtracciones() {
        return atracciones;
    }

    public IPromocion getPromocion() {
        return promocion;
    }

    public int getTotal() {
        return total;
    }
}