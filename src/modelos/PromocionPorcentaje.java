package modelos;

public class PromocionPorcentaje extends Promocion implements IPromocion {
    private final String porcentaje;

    public PromocionPorcentaje(int id,int tipo, Atraccion atraA, Atraccion atraB, int atraP, String porcentaje,String total) throws Exception {
    	super(id, 2, atraA, atraB, atraP, porcentaje, total);
        if (Double.valueOf(porcentaje)>= 100){
            throw new Exception("La promocion da un costo igual o superior a la suma del valor de las atracciones");
        }
        this.porcentaje = porcentaje;
    }

    @Override
    public Object retornarPromocion() {
        return porcentaje;
    }
}
