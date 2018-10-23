package APv;

import java.util.*;



public class Automata {
	
	private LeerFichero lector = new LeerFichero("APv-2.txt");
	private String actualState;

	private ArrayList<String> stack = new ArrayList<String>();

	private String initialStack;

	private List<States> states = new ArrayList<States>();
	private ArrayList<Memory> memory = new ArrayList<Memory>();

	public Automata() {
		
		lector.Caracter();
		
		
		
		
		
		
		
		

		
//		  this.actualState = "q1";
//		  
//		  this.initialStack = "S"; this.states.add(new States("q1"));
//		  this.states.add(new States("q2")); List<String> stacko = new
//		  ArrayList<String>(); stacko.add("A"); this.states.get(0).addTransition(1,"a",
//		  "S", "q1", stacko); stacko.clear(); stacko.add("A"); stacko.add("A");
//		  this.states.get(0).addTransition(2,"a", "A", "q1", stacko); stacko.clear();
//		  stacko.add("."); this.states.get(0).addTransition(3,"b", "A", "q2", stacko);
//		  this.states.get(1).addTransition(4,"b", "A", "q2", stacko);
		 

		this.actualState = "p";

		this.initialStack = "S";
		this.states.add(new States("p"));
		this.states.add(new States("q"));
		List<String> stacko = new ArrayList<String>();
		stacko.add("0");
		stacko.add("S");
		this.states.get(0).addTransition(1, "0", "S", "p", stacko);
		stacko.clear();
		stacko.add("1");
		stacko.add("S");
		this.states.get(0).addTransition(2, "1", "S", "p", stacko);
		stacko.clear();
		stacko.add("0");
		stacko.add("0");
		this.states.get(0).addTransition(3, "0", "0", "p", stacko);
		stacko.clear();
		stacko.add("0");
		stacko.add("1");
		this.states.get(0).addTransition(4, "0", "1", "p", stacko);
		stacko.clear();
		stacko.add("1");
		stacko.add("0");
		this.states.get(0).addTransition(5, "1", "0", "p", stacko);
		stacko.clear();
		stacko.add("1");
		stacko.add("1");
		this.states.get(0).addTransition(6, "1", "1", "p", stacko);
		stacko.clear();
		stacko.add("S");
		this.states.get(0).addTransition(7, ".", "S", "q", stacko);
		stacko.clear();
		stacko.add("0");
		this.states.get(0).addTransition(8, ".", "0", "q", stacko);
		stacko.clear();
		stacko.add("1");
		this.states.get(0).addTransition(9, ".", "1", "q", stacko);
		stacko.clear();
		stacko.add(".");
		this.states.get(1).addTransition(10, "0", "0", "q", stacko);
		stacko.clear();
		stacko.add(".");
		this.states.get(1).addTransition(11, "1", "1", "q", stacko);
		stacko.clear();
		stacko.add(".");
		this.states.get(1).addTransition(12, ".", "S", "q", stacko);

	}

	public boolean compute(String chain) {
		
		int cadenaConsumida = 0;
		
		int transicion = 0;
		
		ArrayList<Integer> transiciones = new ArrayList<Integer>();
		
		for(States state : states) {
			if(state.getState() == actualState)
				transiciones = state.searchTransitions(actualState, chain, initialStack);		//Busco las primeras transiciones
		}
		
		stack.add(initialStack);
		
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
			
			cadenaConsumida = memory.get(memory.size()-1).getMemoConsumida();
			chain = memory.get(memory.size()-1).getChain();
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
				if (tran.getStackOutput().get(i) != ".")
					stack.add(tran.getStackOutput().get(i));		//Añado las cosas de la pila de transicion
			}
			
	
			if(!stack.isEmpty()) {
				for(States state : states) {
					if(state.getState() == actualState)
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
						finBucle = true;
				if(memory.isEmpty())
					finBucle = true;
			
					
			//Tenemos un error por el bucle while
			
			
		}
		
		if(stack.isEmpty() && chain.isEmpty()) {
			return true;
		}
		
		System.out.println("Sin pila: " + stack.isEmpty() + "   Sin cadena: " + chain.isEmpty());
		
		return false;

	}

	public static void main(String[] args) {

		Automata aut = new Automata();

		if (aut.compute("1001"))
			System.out.println("Cadena Aceptada");
		else
			System.out.println("Cadena No aceptada");

	}

}
