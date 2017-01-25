import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


public class DFSAMachine {
	public static void main(String[]args){
		String fileName= "InputFile.txt";
		ArrayList<String> input = new ArrayList<String>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			int numInput = 1;
			do{
				System.out.println("Finite State Automaton #" + numInput);
				FSA fsa = new FSA();
				int numStates = Integer.parseInt(br.readLine());
				fsa.setNumStates(numStates);
				fsa.printNumStates(); 

				ArrayList<String> finalStates = new ArrayList<String>(); 
				String [] numFinal = br.readLine().split(" "); 
				for (String s: numFinal){
					finalStates.add(s);
				}
				fsa.setFinalStates(finalStates);
				fsa.printFinalStates();
				ArrayList<String> alphabet = new ArrayList<String>(); 
				String [] alphabet1 = br.readLine().split(" "); 
				for (String s: alphabet1){
					alphabet.add(s);
				}
				fsa.setAlphabet(alphabet);
				fsa.printAlphabet();
				TreeMap<String, String> transitions = new TreeMap<String, String>(); 
				String line = br.readLine();
				do {

					//System.out.println("Transition " + line);

					String [] transitionParts = line.split(" ");
					//	System.out.println(transitionParts[0] + transitionParts[1] + transitionParts[2]);
					transitions.put(transitionParts[0].charAt(1)+transitionParts[1], transitionParts[2].charAt(0)+"");

					line = br.readLine();
				}while (line.contains("("));
				fsa.setTransitions(transitions);
				fsa.printTransitions();
				System.out.println("Strings:");
				do{
//					
					if (line.equals("[X]")){
						System.out.print("empty Set \t");
					}
					else{
						System.out.print(line +"\t");
					}
					System.out.println(fsa.works(line));
					line = br.readLine();
				} while (line.equals("******************")==false);
				System.out.println(line );
				numInput++;
			}while(br.ready());

		}catch(Exception e){

		}
	}

}
