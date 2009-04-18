package test.simulation;
import vehicles.simulation.*;

/**A main class for testing of various elements in the simulation.
* Please DON'T upload binary files produced by compiling this into the repo.
*/
public class SimulationTests {

	public static void main(String[] args){
		
		/* Testing the creation of a new simulation */
		Simulation s = new Simulation("/home/graysr/Eclipse_Workspace/2ba7_GroupProject/src/test/simulation/simulation.xml");
		s.printSimDetails();
		
		
	}
}