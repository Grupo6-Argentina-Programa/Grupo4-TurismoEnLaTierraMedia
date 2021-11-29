package modelos;
import modelosEnum.ENUMTIPO;
public class TIpoAtraccion {
private int Id=0;
private int IdReferencia;
private String Tipo;
private ENUMTIPO TipoFavorito = ENUMTIPO.SinDefinir;




public void TipoAtraccion(int Id, int IdReferencia, String Tipo, ENUMTIPO TipoFavorito) {
	this.Id = Id;
	this.IdReferencia = IdReferencia;
	this.Tipo = Tipo;
	this.TipoFavorito=TipoFavorito;

}
public int getId() {
	return this.Id;
}

public void setId(int Id) {
	this.Id = Id;
}
public int getIdReferencia() {
	return IdReferencia;
}
public String getTipo() {
	return Tipo;
}

public ENUMTIPO getTipoFavorito() {
	return TipoFavorito;
	
	
	
}















}
