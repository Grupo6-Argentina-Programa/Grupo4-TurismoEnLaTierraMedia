package modelos;

import java.util.Objects;

import modelosEnum.ENUMTIPO;

public class TipoAtraccion {
	private int Id = 0;
	private final int idReferencia;
	private final String tipoDelObjeto;
	private final String tipoFavorito;
	private ENUMTIPO preferencia = ENUMTIPO.SinDefinir;

	public TipoAtraccion(int Id, int IdReferencia, String TipoDelObjeto, String TipoFavorito) {
		this.Id = Id;
		this.idReferencia = IdReferencia;
		this.tipoDelObjeto = TipoDelObjeto;
		this.tipoFavorito = TipoFavorito;
		asignarPreferencia(TipoFavorito);
	}
	
	public TipoAtraccion(int IdReferencia, String TipoDelObjeto, String TipoFavorito) {
		this.idReferencia = IdReferencia;
		this.tipoDelObjeto = TipoDelObjeto;
		this.tipoFavorito = TipoFavorito;
		asignarPreferencia(TipoFavorito);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public ENUMTIPO getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(ENUMTIPO preferencia) {
		this.preferencia = preferencia;
	}

	public int getIdReferencia() {
		return idReferencia;
	}

	public String getTipoDelObjeto() {
		return tipoDelObjeto;
	}

	public String getTipoFavorito() {
		return tipoFavorito;
	}
////////////////////////////////////////////////////////////////////////////////

	public void asignarPreferencia(String preferencia) {

		if (preferencia.equals("Degustacion")) {
			this.preferencia = ENUMTIPO.DEGUSTACION;
		}

		if (preferencia.equals("Paisaje")) {
			this.preferencia = ENUMTIPO.PAISAJE;
		}

		if (preferencia.equals("Aventura")) {
			this.preferencia = ENUMTIPO.AVENTURA;
		}
	}

////////////////////////////////////////////////////////////////////////////////

	@Override
	public int hashCode() {
		return Objects.hash(Id, idReferencia, preferencia, tipoDelObjeto, tipoFavorito);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoAtraccion other = (TipoAtraccion) obj;
		return Id == other.Id && idReferencia == other.idReferencia && preferencia == other.preferencia
				&& Objects.equals(tipoDelObjeto, other.tipoDelObjeto)
				&& Objects.equals(tipoFavorito, other.tipoFavorito);
	}

////////////////////////////////////////////////////////////////////////////////

}
