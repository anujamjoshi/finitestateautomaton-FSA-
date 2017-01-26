import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;


/**
 * CS 311 Formal Languages and Automata
 * Project #1 -finite state automaton (FSA)
 * Winter Quarter 2017
 * Professor Dr. Daisy Sang
 * Author: Anuja Joshi 
 * 
 * Object Class for each FSA machine: FSA.java
 * Class Description: This class creates the components of the machine that are passed 
 * in from the main class. It then takes the inputs and has a works(input ) method that returns 
 * if the input was accepted or not. 
 * 
 */
public class FSA {
	/**
	 * global variables that store each FSA's number of states, 
	 * final states, alphabet. transitions
	 */
	int numStates;
	ArrayList<String> finalStates;
	ArrayList<String> alphabet;
	TreeMap<String, String> transitions;

	/**
	 * methods to set the Number of states and print them 
	 */
	public void setNumStates(int numStates) {
		this.numStates= numStates;
	}
	public void printNumStates() {
		System.out.println("numStates " + numStates);
	}
	/**
	 * methods to set the List of final states and print them 
	 */
	public void setFinalStates(ArrayList<String> finalStates) {
		this.finalStates = finalStates;
	}
	public void printFinalStates() {
		System.out.println("final states " + finalStates.toString());

	}	
	/**
	 * methods to set the Number of alphabet and print them 
	 */
	public void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	public void printAlphabet() {
		System.out.println("alphabet " +alphabet.toString());
	}
	/**
	 * methods to set the transitions between states and print them 
	 */
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
/**
 * This is the main method that checks to see if the input string is a valid string 
 * @param input 
 * @return accept or reject 
 */
	public String works(String input) {
		/*
		 * if input is null
		 */
		if (input.equals("[X]")){
			if (finalStates.contains("0")){
				return "accept";
			}
		}
		/*
		 * if input's characters are within the alphabet 
		 */
		else if(containsAllVals(input) == false){
			return "reject";
		}
		/*
		 * if the input enters a trap state at some point 
		 */
		String output = entersTrap(input);
		 if (entersTrap(input).equals("Trap")){
			
			
			return "reject";
		}
			/*
			 * if input ends with a state that is not a trap state but also is not a final state
			 */
		 else if (!finalStates.contains(output)){
			 return "reject";
		 }
			/*
			 * if input is valid
			 */
		return "accept";
	}
	
	/**
	 * @param input
	 * @return "Trap" 
	 * if the state that the string goes to is the trap state (Number of states -1) 
	 * or @return state 
	 * (the value of the state if it's not a trap state) 
	 */
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
	/**
	 * @param input
	 * @return true if all the characters are within the alphabet 
	 * (making sure that the meta characters are accounted for) 
	 */
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
	/**
	 * @param check
	 * @return the correspoinding meta characters 
	 * eg: a%z is retured for a or b or c or ... z
	 * A%Z is retured for A or B or C or ... Z
	 */
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