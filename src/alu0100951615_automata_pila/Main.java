package alu0100951615_automata_pila;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ejecutable del programa. El fichero se le pasa como argumento de linea de comandos.
 * 
 * TRAZA
 * --- > { "Estado" "Cadena" "[Simbolos Pila]" "Transicion" } , Si el elemento es el ultimo del almacén lleva al principio ###
 * puesto que es el que se está examinando
 *
 */
public class Main {
	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		Aut_Pila aut = null;
		String cadena;
		boolean valida = true;

		if (args.length == 0) {
			System.out.println("No hay fichero");
			return;
		} else {
			aut = new Aut_Pila(args[0]);
		}
		try {
			do {
				valida = true;
				System.out.println("Introduce la cadena: ");
				cadena = br.readLine();
				for (int i = 0; i < cadena.length(); i++)	//para comprobar el alfabeto de las transiciones
					if (!Aut_Pila.alfabeto.pertenece(cadena.charAt(i)))
						valida = false;
			} while (!valida);
			if (aut.compute(cadena)) {
				System.out.println("");
				System.out.println("Cadena Aceptada ");
			} else {
				System.out.println("");
				System.out.println("Cadena Rechazada ");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
