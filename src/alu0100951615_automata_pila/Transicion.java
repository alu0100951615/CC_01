package alu0100951615_automata_pila;

import java.util.*;



/**
 * Clase que sirve para almacenar las 4-uplas de las transiciones con un identificador que es un INT
 *
 */
public class Transicion {
	

	private String sigEstado; //estado de la transicion al que se va a transitar
	private String cadEntrada; //simbolo de la cadena que se va a leer
	private String leerPila;   //simbolo de la pila que se va a leer
	private int iDtran; 	   //identificador de la transicion
	private List<String> simbPilaPush = new ArrayList<String>();  //Simbolos de la pila a insertar, pueden ser varios acordarse de que es una lista.
	
	public String toString() {
		return cadEntrada + " " + leerPila + " " + sigEstado + " " + simbPilaPush.toString();
	}
	
	//Getters y setters
	

	public int getiDtran() {
		return iDtran;
	}


	public void setiDtran(int iDtran) {
		this.iDtran = iDtran;
	}


	public String sigEstado() {
		return sigEstado;
	}


	public void setsigEstado(String sigEstado) {
		this.sigEstado = sigEstado;
	}


	public String getcadEntrada() {
		return cadEntrada;
	}


	public void setcadEntrada(String cadEntrada) {
		this.cadEntrada = cadEntrada;
	}


	public String leerPila() {
		return leerPila;
	}


	public void setLeerPila(String leerPila) {
		this.leerPila = leerPila;
	}


	public List<String> getsimbPilaPush() {
		return simbPilaPush;
	}


	public void setSimbPilaPush(List<String> simbPilaPush) {
		this.simbPilaPush = simbPilaPush;
	}
	
	public Transicion(int iDtran, String cadEntrada, String leerPila ,String sigEstado, List<String> simbPilaPush) {
		this.iDtran = iDtran;
		this.sigEstado = sigEstado;
		this.cadEntrada = cadEntrada;
		this.leerPila = leerPila;
		this.simbPilaPush.addAll(simbPilaPush);
	}

}
