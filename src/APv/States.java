package APv;

import java.util.ArrayList;
import java.util.List;

public class States {
	
	private List<Transition> transitions = new ArrayList<Transition>();
	
	private String state;
	private int nTransitions;
	
	public States(String state) {
		
		this.setState(state);
		nTransitions = 0;
		
	}
	
	public void addTransition(int transition,String chainIn, String stackIn, String nextState, List<String> stackOutput) {
		++nTransitions;
		transitions.add(new Transition(transition,chainIn, stackIn, nextState, stackOutput));
		
	}
	
	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public int getnTransitions() {
		return nTransitions;
	}

	public void setnTransitions(int nTransitions) {
		this.nTransitions = nTransitions;
	}

	public ArrayList<Integer> searchTransitions(String state, String chainInput, String stackInput){
		ArrayList<Integer> transitions = new ArrayList<Integer>();
		
		if(state.equals( this.state)) {
		for(Transition tran: this.transitions) {
			if(tran.getStackInput().equals( stackInput))
				if(tran.getChainInput().equals(Character.toString(chainInput.charAt(0))) || tran.getChainInput().equals(".")) {
					transitions.add(new Integer( tran.getnTransition() ));
				}
			
		}
		}
		
		return transitions;
		
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
