package APv;

import java.io.File;
import java.util.*;

public class Automata {

	private String actualState;

	private ArrayList<String> stack = new ArrayList<String>();

	private Alfabet chainAlfabet;

	private Alfabet stackAlfabet;

	private String initialStack;

	private List<States> states = new ArrayList<States>();
	private ArrayList<Memory> memory = new ArrayList<Memory>();

	public Automata(String fichero) {


		try {
			
			Scanner in = new Scanner(new File(fichero));
			String[] estados ;
			do {
				estados = in.nextLine().split(" ");
			}while(estados[0].charAt(0) == '#');
			
			
			for(String st : estados) {
//				System.out.println(estados);
				this.states.add(new States(st));
				
			}
			
			chainAlfabet = new Alfabet(in.nextLine().split(" "), "Cadena");
			
			stackAlfabet = new Alfabet(in.nextLine().split(" "), "Pila");
			
			actualState = in.nextLine();
			
			initialStack = in.nextLine();
			int ntransiciones = 1;
			

			
			while (in.hasNextLine()) {
				
				String [] transiciones = in.nextLine().split(" ");
			
				for(States estado : states) {
					if(estado.getState().equals(transiciones[0])) {
						List<String> outStack = new ArrayList<String>();
						for(int i = 4; i < transiciones.length; i++) {
							stackAlfabet.pertenece(transiciones[i]);
							outStack.add(transiciones[i]);
						}
						chainAlfabet.pertenece(transiciones[1]);
						stackAlfabet.pertenece(transiciones[2]);
						estado.addTransition(ntransiciones, transiciones[1], transiciones[2], transiciones[3], new ArrayList<String>(outStack));
				//		System.out.println(ntransiciones+ " " + estado.getState() + " "+ transiciones[1] + " " + transiciones[2] + " " + transiciones[3] + " " + outStack);
						ntransiciones++;
					}
							
				}
			}
			
			
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
			System.out.println("Revise el fichero de entrada.");
			System.exit(0);
		}

	}

	public boolean compute(String chain) {
		
		int cadenaConsumida = 0;
		
		int transicion = 0;
		
		ArrayList<Integer> transiciones = new ArrayList<Integer>();
		
		for(States state : states) {
			if(state.getState().equals(actualState)) {
				transiciones = state.searchTransitions(actualState, chain, initialStack);		//Busco las primeras transiciones
			}
		}
		
		stack.add(initialStack);
		//System.out.println("T: " + transiciones);
		for(int i : transiciones) {
			memory.add(new Memory(actualState, chain, new ArrayList<String>(stack), i, cadenaConsumida));	//Las añado a la memoria
		}
		transiciones.clear();
		
		//~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·~·
		
		int nveces = 0;
		boolean finBucle = false;
		
		while(!finBucle) {
			nveces++;
			
			System.out.println(memory);
			
			
			chain = memory.get(memory.size()-1).getChain();
			cadenaConsumida = memory.get(memory.size()-1).getMemoConsumida();
			stack = memory.get(memory.size()-1).getStack();
			actualState = memory.get(memory.size()-1).getState();	//Saco la transion del top
			transicion = memory.get(memory.size()-1).getPosiblyTransitions();
			memory.remove(memory.size()-1);	//La elimino
		//	System.out.println(" Cadena: " + chain + " Pila: " + stack + " Estado Actual: " + actualState + " Transicion: " + transicion);
			

			
			Transition tran = null;
			for (States state : states) {
				for (Transition trans : state.getTransitions()) {	//Busco la transicion
					if (trans.getnTransition() == transicion) {
						tran = trans;
						
					}
				}
			}
			
			if(tran.getChainInput() != ".") {
				chain = chain.substring(1);	//Quito la letra de la cadena
				cadenaConsumida++;
			}
			stack.remove(stack.size()-1);	//Quito el top de la pila
			actualState = tran.getNextState(); //Cambio el estado
			
			for (int i = tran.getStackOutput().size() - 1; i >= 0; i--) {
				if (!tran.getStackOutput().get(i).equals("."))
					stack.add(tran.getStackOutput().get(i));		//Añado las cosas de la pila de transicion
			}
			
	
			if(!stack.isEmpty()) {
				for(States state : states) {
					if(state.getState().equals(actualState))
						if(chain.length() != 0)
							transiciones = state.searchTransitions(actualState, chain, stack.get(stack.size()-1));	//Busco las posibles transiciones con el nuevo chain y stack
						else
							transiciones = state.searchTransitions(actualState, ".", stack.get(stack.size()-1));
				}
				
				
				if(!transiciones.isEmpty()) {
					for(int i : transiciones) {
						memory.add(new Memory(actualState, chain, new ArrayList<String>(stack), i, cadenaConsumida));		//Las meto en la memoria
					}
					transiciones.clear();
				}
			}	
			
			
				if(chain.isEmpty())
					if(stack.isEmpty())
						finBucle = true;	//Se acepta la cadena
				if(memory.isEmpty())
					finBucle = true;	//Se han hecho todas las posibilidades y no se acepta
			
					
			
			
			
		}
		
		if(stack.isEmpty() && chain.isEmpty()) {
			return true;
		}
		
		System.out.println("Sin pila: " + stack.isEmpty() + "   Sin cadena: " + chain.isEmpty());
		
		return false;

	}

	public static void main(String[] args) {

		Automata aut = new Automata(args[0]);

		if (aut.compute("1111"))
			System.out.println("Furula");
		else
			System.out.println("No furula");

	}

}
