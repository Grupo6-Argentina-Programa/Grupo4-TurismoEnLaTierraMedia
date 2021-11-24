package modelos;

public class Itinerario {
	private int Id = 0;
	private int IdUsuario;
	private int IdAtraccion;

	//Constructor Standart
	public Itinerario(int IdUsuario, int IdAtraccion) {
		super();
		this.IdUsuario = IdUsuario;
		this.IdAtraccion = IdAtraccion;
	}
	
	//Constructor Utilizado por DAO
	public Itinerario(int Id, int IdUsuario, int IdAtraccion) {
		super();
		this.Id = Id;
		this.IdUsuario = IdUsuario;
		this.IdAtraccion = IdAtraccion;
	}

	public int getIdUsuario() {
		return IdUsuario;
	}

	public int getIdAtraccion() {
		return IdAtraccion;
	}

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	// En el Sql la Id inicial es 1, por lo tnato si alguna Id de Itinerario ...
	// ... es Igual a 0 quiere decir que no se actualizo.
}
