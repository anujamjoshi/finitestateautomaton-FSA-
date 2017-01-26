import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * CS 311 Formal Languages and Automata
 * Project #1 -finite state automaton (FSA)
 * Winter Quarter 2017
 * Professor Dr. Daisy Sang
 * Author: Anuja Joshi 
 *
 * Project Description: In this project we created machines that would take in input strings
 * and then output a value of "accept" or "reject" depending on if the input is within the language 
 * that the machine describes. 
 *  
 *
 * How to compile, link, and run this program:
 *   1) make sure that InputFile.txt is within the correct directory before compilation 
 *   2) javac DFSAMachine.java
 *   3) javac FSA.java
 *   3) java DFSAMachine
 * 
 * 
 *   Please note that there are special characters in the input file, defined as bellow 
 *   
 *   "[X] "		is an empty string 
 *   "******************" represents the end_marker for a FSA definition
 *   0%9			represents digits 0...9
 *   1%9			represents digits 1...9
 *   a%z			represents lowercase characters a...z
 *   A%Z			represents uppercase characters A...Z
 *   
 *   Please note: in the output file I made empty strings  print out as "empty" 
 *   
 *   Main Class: DFSAMachine.java
 * */
public class DFSAMachine {
	private static BufferedReader br;
	/*
	 * start file read 
	 */
	public static void main(String[]args){
		String fileName= "InputFile.txt";
		try{
			/*
			 * start file read using a buffered reader 
			 */
			br = new BufferedReader(new FileReader(fileName));
			/*
			 * keep track of the machine number 
			 */
			int numInput = 1;
			do{
				/*
				 * create a new machine object 
				 */
				System.out.println("Finite State Automaton #" + numInput);
				FSA fsa = new FSA();
				/*
				 * send the number of states 
				 */
				int numStates = Integer.parseInt(br.readLine());
				fsa.setNumStates(numStates);
				fsa.printNumStates(); 
				//----------------------------------- number of states
				/*
				 * send the final state numbers
				 */
				ArrayList<String> finalStates = new ArrayList<String>(); 
				String [] numFinal = br.readLine().split(" "); 
				for (String s: numFinal){
					finalStates.add(s);
				}
				fsa.setFinalStates(finalStates);
				fsa.printFinalStates();
				//----------------------------------- final states
				/*
				 * send the alphabet 
				 */
				ArrayList<String> alphabet = new ArrayList<String>(); 
				String [] alphabet1 = br.readLine().split(" "); 
				for (String s: alphabet1){
					alphabet.add(s);
				}
				fsa.setAlphabet(alphabet);
				fsa.printAlphabet();
				//----------------------------------- alphabet 
				/*
				 * send the Transitions 
				 */

				TreeMap<String, String> transitions = new TreeMap<String, String>(); 
				String line = br.readLine();
				do {
					String [] transitionParts = line.split(" ");
					String p = transitionParts[0];
					String p1 = p.substring(1);
					String q = transitionParts[2];
					int ind = q.indexOf(')');
					String q1 = q.substring(0, ind) ;
					transitions.put(p1+" "+transitionParts[1], q1);
					line = br.readLine();
				}while (line.contains("("));
				fsa.setTransitions(transitions);
				fsa.printTransitions();
				//----------------------------------- Transitions

				/*
				 * send the Input Strings until you reach the end of the machine definition
				 * which is defined by "******************"
				 */				
				System.out.println("Strings:");
				do{			
					if (line.equals("[X]")){
						System.out.print("empty Set \t");
					}
					else{
						System.out.print(line +"\t");
					}
					System.out.println(fsa.works(line));
					line = br.readLine();
				} while (line.equals("******************")==false);
				//______________________________________________ input strings 
				System.out.println(line );
				numInput++;
			}while(br.ready());

		}catch(Exception e){

		}
	}

}
