package APv;

import java.util.*;

public class Transition {
	
	private String nextState;
	private String chainInput;
	private String stackInput;
	private int nTransition;
	private List<String> stackOutput = new ArrayList<String>();
	
	public int getnTransition() {
		return nTransition;
	}

	public void setnTransition(int nTransition) {
		this.nTransition = nTransition;
	}

	public String getNextState() {
		return nextState;
	}

	public void setNextState(String nextState) {
		this.nextState = nextState;
	}

	public String getChainInput() {
		return chainInput;
	}

	public void setChainInput(String chainInput) {
		this.chainInput = chainInput;
	}

	public String getStackInput() {
		return stackInput;
	}

	public void setStackInput(String stackInput) {
		this.stackInput = stackInput;
	}

	public List<String> getStackOutput() {
		return stackOutput;
	}

	public void setStackOutput(List<String> stackOutput) {
		this.stackOutput = stackOutput;
	}
	
	public Transition(int nTransition, String chainInput, String stackInput ,String nextState, List<String> stackOutput) {
		this.nTransition = nTransition;
		this.nextState = nextState;
		this.chainInput = chainInput;
		this.stackInput = stackInput;
		this.stackOutput.addAll(stackOutput);
	}

}
