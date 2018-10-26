package alu0100951615_automata_pila;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Clase que realiza el algoritmo en sí del autómata de pila, además lee del fichero y lo procesa.
 *
 */
public class Aut_Pila {
	private boolean end;
	private String estadoInicial;	//Estado inicial
	private ArrayList<String> pila = new ArrayList<String>(); //Pila
	static Alfabeto alfabeto;	//Alfabeto de cadena
	private Alfabeto alfabetoPila;	//Alfabeto de pila
	private String simboloInicial;	//Elemento inicial de la pila
	private List<Estado> estado = new ArrayList<Estado>();	//Estados y transiciones del automata
	private ArrayList<Almacen> almacen = new ArrayList<Almacen>();	//Pila de memoria para recorrer todos los posibles caminos

	
	
	/**
	 * Constructor de la clase que va procesar todos los datos recogidos, se le pasa el fichero
	 * @param fichero : nombre del fichero
	 */
	public Aut_Pila(String fichero) {	
		try {			
			Scanner in = new Scanner(new File(fichero));
			String[] estados ;
			do {
				estados = in.nextLine().split(" ");		
			}while(estados[0].charAt(0) == '#');   //Para descartar los comentarios del principio
			
			
			for(String st : estados) {
				this.estado.add(new Estado(st));   //Primera linea que son los estados
				}
			
			alfabeto = new Alfabeto(in.nextLine().split(" "), "Cadena");				
			alfabetoPila = new Alfabeto(in.nextLine().split(" "), "Pila");	//Pillamos el alfabeto a analizar y el de la pila			
			estadoInicial = in.nextLine();	//Estado inicial			
			simboloInicial = in.nextLine();	//Inicio Pila			
			int iDtran = 1;	//IDtran
			
			while (in.hasNextLine()) {	
				
				String [] transiciones = in.nextLine().split(" ");          //Bucle para guardar las transiciones
																			//Se comprueban que pertenezcan los simbolos a los alfabetos
				for(Estado estado : estado) {								//Y despues se insertan en los estados el ID, y la informacion seguida 
					if(estado.getEstActual().equals(transiciones[0])) {		//de la Lista de la pila con pequeño bucle.
						List<String> outStack = new ArrayList<String>();	
						for(int i = 4; i < transiciones.length; i++) {
							alfabetoPila.pertenece(transiciones[i]);	
							outStack.add(transiciones[i]);				
						}
						alfabeto.pertenece(transiciones[1]);	
						alfabetoPila.pertenece(transiciones[2]);	
						estado.addTransition(iDtran, transiciones[1], transiciones[2], transiciones[3], new ArrayList<String>(outStack));	//Añado la transicion
						iDtran++;	
					}
							
				}
			}
			
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.exit(-1);
		}

	}


	public boolean compute(String cadena) {	
		
		pila.clear();				
		int transicion = 0;			
		String actualState = estadoInicial;			
		ArrayList<Integer> transiciones = new ArrayList<Integer>();

		
		for(Estado state : estado) {	//Buscar transiciones 
			if(state.getEstActual().equals(actualState)) {
				transiciones = state.buscarTran(actualState, cadena, simboloInicial);		
			}
		}
		
		pila.add(simboloInicial);	    //Primer elemento de la pila y al almacen todas las posibilidades encontradas.
		for(int i : transiciones) {
			almacen.add(new Almacen(actualState, cadena, new ArrayList<String>(pila), i));	
		}
		
		if(almacen.size() == 0) {
			System.out.println("No hay transiciones desde el estado incial ");
			return false;
		}
		transiciones.clear();	
						
		while(!end) {
			
			String output = " -- > {";  				//Imprimir el contenido del almacen por pantalla, aquellos elementos 
			for(int i = 0; i < almacen.size(); i++) {	//con ### indica que son los ultimos de la pila del almacen
				if(i != almacen.size()-1)
					output +=almacen.get(i).toString() + "}, {";
				else
					output += "###" + almacen.get(i).toString() + " }";
			}			
			System.out.println(output);	
			
			cadena = almacen.get(almacen.size()-1).getCadena();				//Obtenemos del top del almacen los elementos que nos interesan
			pila = almacen.get(almacen.size()-1).getPila();					//la cadena, el simbolo de la pila, el estado y el ID de la transicion,
			actualState = almacen.get(almacen.size()-1).getEstado();		//por ultimo lo borramos DEL ALMACEN.
			transicion = almacen.get(almacen.size()-1).getTransOpc();	
			almacen.remove(almacen.size()-1);							
			
			Transicion tran = null;											//Buscamos el ID de la transicion
			for (Estado state : estado) {
				for (Transicion trans : state.getTransiciones()) {	
					if (trans.getiDtran() == transicion) {
						tran = trans;						
					}
				}
			}
						
			if(!tran.getcadEntrada().equals(".") ) {  //Quitamos elemento de la cadena, a no ser "."
				cadena = cadena.substring(1);	
			}
			pila.remove(pila.size()-1);				  //Pop y cambiamos estado
			actualState = tran.sigEstado(); 
			
			for (int i = tran.getsimbPilaPush().size() - 1; i >= 0; i--) {  //Añadimos los simbolos de la pila a la transicion menos "."
				if (!tran.getsimbPilaPush().get(i).equals("."))	
					pila.add(tran.getsimbPilaPush().get(i));		
			}
			
	
			if(!pila.isEmpty()) {				//Si la pila no está vacía no hemos acabado, comprobamos que la cadena tampoco esté vacía
				for(Estado state : estado) {	//Buscamos nuevas transiciones con los nuevos datos, si la cadena está vacía hay que buscar transición con "."
					if(state.getEstActual().equals(actualState))	
						if(cadena.length() != 0)	
							transiciones = state.buscarTran(actualState, cadena, pila.get(pila.size()-1));	
						else
							transiciones = state.buscarTran(actualState, ".", pila.get(pila.size()-1));	
				}
				
				
				if(!transiciones.isEmpty()) {	//Si tiene transiciones se buscan más caminos y se añaden al almacén si no es así no sirve ese nodo del árbol.
					for(int i : transiciones) {
						almacen.add(new Almacen(actualState, cadena, new ArrayList<String>(pila), i));		
					}
					transiciones.clear();	
				}
			}	
			
			
				if(cadena.isEmpty())	//Condiciones para aceptar o no la cadena, Cadena vacia(T) y Pila(T) en otro caso cadena no aceptada.
					if(pila.isEmpty())	
						end = true;	
				if(almacen.isEmpty())	
					end = true;				
		}
		
		if(pila.isEmpty() && cadena.isEmpty()) {
			return true;	
		}		
		return false;	
	}		
}
