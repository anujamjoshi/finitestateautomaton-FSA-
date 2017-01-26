import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;


public class FSA {
	int numStates;
	ArrayList<String> finalStates;
	ArrayList<String> alphabet;
	TreeMap<String, String> transitions;

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
	public void setTransitions(TreeMap<String, String> transitions) {
		this.transitions=transitions;
	}
	public void printTransitions() {
		System.out.println("Transitions");
		String out = "";
		Set<String> transitionKeys = transitions.keySet();
		for(String s: transitionKeys){
			String p = s.substring(0,1);
			String a = s.substring(1);
			out+= "(" + p+ " " + a +" ";
			out+= transitions.get(s);
			out+= ")\n";
		}
		System.out.println(out);

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
		String output = entersTrap(input);
		System.out.println("Output " + output);
		 if (entersTrap(input).equals("Trap")){
			
			
			return "reject";
		}
		 else if (!finalStates.contains(output)){
			 return "reject";
		 }
		
		return "accept";
	}
	private String entersTrap(String input) {

		String check;
		String state ="0";
		for (int i =0; i < input.length(); i++){
			check = input.substring(i, i+1);
	
			check =getMeta(check);
		

			String key = state+" " +check;
			//System.out.println("KEy " + key);
			state= transitions.get(key);
		//	System.out.println("State " + state);
	
			if (state.equals(String.valueOf(numStates-1))){
				return "Trap";
			}
		}
		
		return state;
	}
	private boolean containsAllVals(String input) {
	
		String check;
	
		for (int i =0; i < input.length(); i++){
			check = input.substring(i, i+1);
			check = getMeta(check);
			
			if (!alphabet.contains(check)){
				
				return false;
			}

		}

		return true;
	}
	private String getMeta(String check) {
	
		if (alphabet.contains("a%z")){
			for(char c = 'a'; c <= 'z'; c++) {
				if (check.equals(String.valueOf(c))){
					return "a%z";
				}
			}

		}
		 if (alphabet.contains("A%Z")){
			for(char c = 'A'; c <= 'Z'; c++) {
				if (check.equals(String.valueOf(c))){
					return "A%Z";
				}
			}
		}
		if (alphabet.contains("0%9")){
			for(char c = '0'; c <= '9'; c++) {
				if (check.equals(String.valueOf(c))){
					return "0%9";
				}
			}
			
		}
		else if (alphabet.contains("1%9")){
			for(char c = '1'; c <= '9'; c++) {
				if (check.equals(String.valueOf(c))){
					return "1%9";
				}
			}
			
		}
		
		return check;
	}
	



}