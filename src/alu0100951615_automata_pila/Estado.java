package alu0100951615_automata_pila;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase que representa cada uno de los estados del autómata, nos permite añadirle transiciones,buscarlas, y lógicamente crear el estado ^^.
 *
 */
public class Estado {
	

	private List<Transicion> transiciones = new ArrayList<Transicion>(); //Lista de todas las transiciones que pueden ir dentro de nuestro estado.
	
	private String estActual;
	
		
	
	/**
	 * Constructor de clase
	 * @param estado
	 */
	public Estado(String estado) {
		
		this.setEstActual(estado);		
	}
	
	
	/**
	 * Funcion para añadir transiciones al Estado
	 * @param transicion Id de la transicion
	 * @param simbEntrada Simbolo de la cadena a leer
	 * @param simbPila Simbolo de la Pila a Leer (POP)
	 * @param sigEstado Siguiente estado que, en caso de cumplirse las condiciones se avanzaría
	 * @param simbPilaPush Simbolos a meter en la pila (PUSH DE LISTA)
	 */
	public void addTransition(int transicion,String simbEntrada, String simbPila, String sigEstado, List<String> simbPilaPush) {
		transiciones.add(new Transicion(transicion,simbEntrada, simbPila, sigEstado, simbPilaPush));
		
	}
	

	/**
	 * Funcion que sirve para buscar los nodos posibles del árbol de posibilidades tal que si 
	 * el simbolo a leer de la pila coincide, el simbolo de la cadena también coincide o es igual a vacio "." se añade la nueva posibilidad al nodo
	 * mediante el ID.
	 * @param estado: Estado a comprobar
	 * @param cadenaEntrada:Simbolo de la cadena de lectura de la transicion
	 * @param simboloPila:Simbolo de la pila que se le acaba de hacer POP
	 * @return: Array con las transiciones validas para este Estado.
	 */
	public ArrayList<Integer> buscarTran(String estado, String cadenaEntrada, String simboloPila){
		ArrayList<Integer> transiciones = new ArrayList<Integer>();
		if(estado.equals( this.estActual)) {	
			for(Transicion tran: this.transiciones) {	
				if(tran.leerPila().equals( simboloPila))	
					if(tran.getcadEntrada().equals(Character.toString(cadenaEntrada.charAt(0))) || tran.getcadEntrada().equals(".")) {	
						transiciones.add(new Integer( tran.getiDtran() ));	
																			
				}			
			}
		}		
		return transiciones;		
	}


	public String getEstActual() {
		return estActual;
	}


	public void setEstActual(String estActual) {
		this.estActual = estActual;
	}
	

	public List<Transicion> getTransiciones() {
		return transiciones;
	}


	public void setTransiciones(List<Transicion> transiciones) {
		this.transiciones = transiciones;
	}
	
}
