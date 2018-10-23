package APv;

import java.util.*;

public class Memory {
	
	
	private String state;
	private String chain;
	private ArrayList<String> stack = new ArrayList<String>();
	private int posiblyTransitions;
	private int memoConsumida;
	
	public Memory(String state, String chain, ArrayList<String> stack, int posibilitys, int memoConsumida) {
		this.memoConsumida = memoConsumida;
		this.state = state;
		this.chain = chain;
		this.stack = stack;
		this.posiblyTransitions = posibilitys;
		
	}
	
	public String toString() {
		return " Estado:  " + state + " Cadena: " + chain + " Pila: " + stack.toString() + " Transicion: " + posiblyTransitions + " Cadena Consumida: " + memoConsumida;
	}
	
	public int getMemoConsumida() {
		return memoConsumida;
	}


	public void setMemoConsumida(int memoConsumida) {
		this.memoConsumida = memoConsumida;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChain() {
		return chain;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}

	public ArrayList<String> getStack() {
		return stack;
	}

	public void setStack(ArrayList<String> stack) {
		this.stack = stack;
	}


	public int getPosiblyTransitions() {
		return posiblyTransitions;
	}
	public void setPosiblyTransitions(int posiblyTransitions) {
		this.posiblyTransitions = posiblyTransitions;
	}
	
	
}
