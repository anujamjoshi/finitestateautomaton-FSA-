import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;


public class DFSAMachine {
	private static BufferedReader br;

	public static void main(String[]args){
		String fileName= "InputFile.txt";
		try{
			br = new BufferedReader(new FileReader(fileName));
			int numInput = 1;
			do{
				System.out.println("Finite State Automaton #" + numInput);
				FSA fsa = new FSA();
				int numStates = Integer.parseInt(br.readLine());
				fsa.setNumStates(numStates);
				fsa.printNumStates(); 
				//----------------------------------- number of states
				ArrayList<String> finalStates = new ArrayList<String>(); 
				String [] numFinal = br.readLine().split(" "); 
				for (String s: numFinal){
					finalStates.add(s);
				}
				//----------------------------------- final states
				fsa.setFinalStates(finalStates);
				fsa.printFinalStates();
				ArrayList<String> alphabet = new ArrayList<String>(); 
				String [] alphabet1 = br.readLine().split(" "); 
				for (String s: alphabet1){
					alphabet.add(s);
				}
				//----------------------------------- alphabet 
				fsa.setAlphabet(alphabet);
				fsa.printAlphabet();
				//----------------------------------- Transitions
				TreeMap<String, String> transitions = new TreeMap<String, String>(); 
				String line = br.readLine();
				do {

					//System.out.println("Transition " + line);

					String [] transitionParts = line.split(" ");
					//	System.out.println("AHHHH" +transitionParts[0] +" "+  transitionParts[1] +" "+ transitionParts[2]);
						String p = transitionParts[0];
						String p1 = p.substring(1);
//						System.out.println("p1 " + p1);
						String q = transitionParts[2];
//						System.out.println("q" + q);
						int ind = q.indexOf(')');
						String q1 = q.substring(0, ind) ;
//						System.out.println(q1+" q1");
						transitions.put(p1+" "+transitionParts[1], q1);

					line = br.readLine();
				}while (line.contains("("));
				fsa.setTransitions(transitions);
				fsa.printTransitions();


				//______________________________________________ input strings 
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
				System.out.println(line );
				numInput++;
			}while(br.ready());

		}catch(Exception e){

		}
	}

}
