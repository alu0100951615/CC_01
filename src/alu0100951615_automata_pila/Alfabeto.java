package alu0100951615_automata_pila;
import java.util.*;


/**
 * Clase que sirve para almacenar los alfabetos de la pila y la cadena de entrada y después contrastarlas
 * así con las de las transiciones para ver que todo esté correcto.
 *
 */
public class Alfabeto {
	
	private final String type;
	private final List<String> alfabeto = new ArrayList<String>();
	
	public List<String> getAlfabet() {
		return alfabeto;
	}


	public Alfabeto(String[] strings, String type) {
		this.type = type;
		for(String str : strings) {
			this.alfabeto.add(str);
		}
	}
	

	public void pertenece(String letra) throws Exception {
		if(!alfabeto.contains(letra) && !letra.equals(".")) {
			Exception e = new Exception("Este caracter '" + letra + "' no pertenece al alfabeto de " + type + ".");
        	throw e;
		}
	}
	
	

	public boolean pertenece(char letra) {
		if(!alfabeto.contains(Character.toString(letra))) {
			return false;
		}
		return true;
	}
	

	public String toString() {
		return alfabeto.toString();
	}
}
