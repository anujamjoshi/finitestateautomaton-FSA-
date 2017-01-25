import java.util.ArrayList;
import java.util.HashMap;


public class FSA {
	int numStates;
	ArrayList<String> finalStates;
	ArrayList<String> alphabet;
	HashMap<String, String> transitions;

	ArrayList<String> inputVal;
	public void setNumStates(int numStates) {
		this.numStates= numStates;
	}
	public void printNumStates() {
		System.out.println("numStates " + numStates);
	}
	public void setFinalStates(ArrayList<String> finalStates) {
		this.finalStates = finalStates;
	}
	public void printFinalStates() {
		System.out.println("final states " + finalStates.toString());

	}
	public void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	public void printAlphabet() {
		System.out.println("alphabet " +alphabet.toString());
	}
	public void setTransitions(HashMap<String, String> transitions) {
		this.transitions=transitions;
	}
	public void setInput(ArrayList<String> inputVal) {
		this.inputVal = inputVal;

	}
	public void printTransitions() {
		System.out.println("Transitions" + transitions.toString());

	}

	public String works(String input) {
		// if the input is null 
		if (input.equals("[X]")){
			if (finalStates.contains("0")){
				return "accept";
			}
		}
		else if(containsAllVals(input) == false){
			return "reject";
		}
		else if (entersTrap(input)){
			return "reject";
		}
		
		return "accept";
	}
	private boolean entersTrap(String input) {
		String check;
		String state ="0";
		for (int i =0; i < input.length(); i++){
			check = input.substring(i, i+1);
		//	System.out.println("check" + check);
			String key = state+check;
			state= transitions.get(key);
			//System.out.println("State = " + state);
			if (state.equals(String.valueOf(numStates-1))){
				return true;
			}
		}
		
		return false;
	}
	private boolean containsAllVals(String input) {
		String check;
		for (int i =0; i < input.length(); i++){
			check = input.substring(i, i+1);
			if (!alphabet.contains(check)){
				return false;
			}

		}
		return true;
	}
	



}
