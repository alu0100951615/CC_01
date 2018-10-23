package APv;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerFichero {
	
	private String linea;
	private String nombreFichero;
	
	public LeerFichero(String nombre) {
		this.nombreFichero = nombre;		
	}
	
	public String Caracter() {

		try {
		      FileReader fr = new FileReader(nombreFichero);
		      BufferedReader br = new BufferedReader(fr);
		      
		      while((linea = br.readLine()) != null)
		        System.out.println(linea);
		 
		      fr.close();
		      
		    }
		    catch(Exception e) {
		      System.out.println("Excepcion leyendo fichero "+ nombreFichero + ": " + e);
		    }		
		return linea;		
	}
	
	
	 

}
