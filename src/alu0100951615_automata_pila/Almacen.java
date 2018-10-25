package alu0100951615_automata_pila;

import java.util.*;


/**
 * Clase Almac�n encargada de ir almacenando aquellas cadenas e �ndices de pila pendientes de ser examinados por el aut�mata,
 * es decir, todas las opciones posibles de las cadenas en el �rbol.
 *
 */
public class Almacen {
	
	
	private String estado; //Estado actual de la iteraci�n
	private String cadena; //Cadena a analizar
	private ArrayList<String> pila = new ArrayList<String>();	//ArrayList con los simbolos de la Pila
	private int transOpc; //para no trabajar directamente con 4-uplas en las transiciones usamos un indice obtenido del fichero segun su posici�n.

	
	/**
	 * Constructor de clase
	 * @param estado: Estado que recibe cada opci�n a evaluar
	 * @param cadena
	 * @param pila
	 * @param opciones: transiciones posibles
	 */
	public Almacen(String estado, String cadena, ArrayList<String> pila, int opciones) {
		this.estado = estado;
		this.cadena = cadena;
		this.pila = pila;
		this.transOpc = opciones;
		
	}
	
	/* 
	 * Para imprimir el almac�n de forma legible por el usuario.
	 */
	public String toString() {
		return " " + estado + " " + cadena + " " + pila.toString() + " IDtran " + transOpc;
	}
	
	//Getters y setters
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getCadena() {
		return cadena;
	}
	
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

	public ArrayList<String> getPila() {
		return pila;
	}

	public void setPila(ArrayList<String> pila) {
		this.pila = pila;
	}

	public int getTransOpc() {
		return transOpc;
	}
	
	public void setTransOpc(int transOpc) {
		this.transOpc = transOpc;
	}
	
	
}
