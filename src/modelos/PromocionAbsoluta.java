package modelos;

public class PromocionAbsoluta extends PromocionDepurar implements PromocionInterface {
	private final String precioFinal;

	public PromocionAbsoluta(int id, int tipo, Atraccion atraccionA, Atraccion atraccionB, int atraccionRegalo,
			String porcentaje, String precioFinal) throws Exception {
		super(id, 1, atraccionA, atraccionB, atraccionRegalo, porcentaje, precioFinal);
		
		if (Double.valueOf(precioFinal) >= atraccionA.getCosto() + atraccionB.getCosto()) {
			throw new Exception("La promocion da un costo igual o superior a la suma del valor de las atracciones");
		}

		this.precioFinal = precioFinal;
	}

	@Override
	public Object retornarPromocion() {
		return precioFinal;
	}
}
