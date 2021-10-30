package main.java.sistema;

import java.io.PrintStream;
import java.util.List;

import main.java.DAO.AtraccionDAO;
import main.java.DAO.DAOFactory;
import main.java.DAO.PromocionDAO;
import main.java.DAO.UsuarioDAO;
import main.java.usuarios.Usuario;



public class Pantalla {
	
	public void inicio() {
		System.out.println("||=================================================||");
		System.out.println("||(digite el numero de la opcion correspondiente...||");
		System.out.println("||(luego presiones la tecla ENTER)                 ||");
		System.out.println("||=================================================||");
	}
	
	
	public void menu() {
		System.out.println();
		System.out.println("||=================================================||");
		System.out.println("|| MENU                                            ||");
		System.out.println("||=================================================||");
		System.out.println("||                                                 ||");
		System.out.println("|| 1 -> para mostrar los usuarios cargados.        ||");
		System.out.println("|| 2 -> para mostrar las atracciones cargadas.     ||");
		System.out.println("|| 3 -> para mostrar las promociones cargadas.     ||");
		System.out.println("|| 4 -> para mostrar las sugerencias.              ||");
		System.out.println("|| 5 -> generar Archivo con todos los datos.       ||");
		System.out.println("||                                                 ||");
		System.out.println("|| 9 -> para finalizar el programa.                ||");
		System.out.println("||=================================================||");
	}
	
	public void noDisponible() {
		System.out.println();
		System.out.println("||=================================================||");
		System.out.println("|| Entrada no valida.                              ||");
		System.out.println("|| Por favor, ingrese una opcion valida.           ||");
		System.out.println("||=================================================||");
	}
	
	public void salir() {
		System.out.println();
		System.out.println("||=================================================||");
		System.out.println("|| Programa finalizado... gracias por testear      ||");
		System.out.println("||=================================================||");
	}
	public void mostrarLosUsuarios() {
			
			
		UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();
		System.out.println(userDAO.findAll());
		}
		
		
		
		public void mostrarLasAtracciones() {
		
			AtraccionDAO atracctionDAO = DAOFactory.getAtraccionDAO();
			
			System.out.println(atracctionDAO.findAll());
			
		}
		
		
		public void mostrarLasPromociones() {
			PromocionDAO promotionDAO = DAOFactory.getPromocionDAO();
			
			System.out.println(promotionDAO.findAll());
			
			
		}
		
		/*public void cargarSugerencias(List<Usuario> usuario, List<Sugerencias> listadoSugerencias) {
			// TODO Auto-generated method stub
			
			System.out.println();
			System.out.println("||=================================================||");
			System.out.println("||=================================================||");

			System.out.println("|| Mostrando todas las Sugerencias para");

			for (Sugerencias x : listadoSugerencias) {

				mostrarSugerencias(x);
			}

			System.out.println("||=================================================||");
			System.out.println();

		}*/

		/*private void mostrarSugerencias(Sugerencias x) {
			// TODO Auto-generated method stub
			System.out.println("||=================================================||");
			System.out.println("|| Nombre ->  " + ((Atraccion) x).getNombre());
			System.out.println("|| Tipo   ->  " + ((Atraccion) x).getTipoAtraccion());
			System.out.println(
					"|| Costo  ->                                 $" + ((Atraccion) x).getCostoAtraccion() + " ||");
			System.out.println(
					"|| Duracion de la Atraccion ->           " + ((Atraccion) x).getTiempoNecesario() + " Horas ||");
			System.out.println("|| Cupo Disponible	    ->                  " + ((Atraccion) x).getCupoMaximo() + " ||");
		}*/


	}


	
