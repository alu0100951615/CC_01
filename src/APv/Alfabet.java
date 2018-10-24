package APv;

import java.util.*;

public class Alfabet {
	private final String type;
	private final List<String> alfabet = new ArrayList<String>();
	
	public List<String> getAlfabet() {
		return alfabet;
	}

	public Alfabet(String[] strings, String type) {
		this.type = type;
		for(String str : strings) {
			this.alfabet.add(str);
		}
		
		
		
	}
	
	public void pertenece(String letra) throws Exception {
		
		if(!alfabet.contains(letra) && !letra.equals(".")) {
			Exception e = new Exception("Este caracter '" + letra + "' no pertenece al alfabeto de " + type + ".");
        	throw e;
		}
		 
		
	}
	
}
