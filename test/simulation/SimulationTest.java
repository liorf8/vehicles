package test.simulation;
import vehicles.simulation.*;



/**A main class for testing of various elements in the simulation.
 * Please DON'T upload binary files produced by compiling this into the repo.
 */
public class SimulationTest {

	public static void main(String[] args){
		EditorSimulation es = new EditorSimulation();
		es.setXmlLocation("xml/simulations/testsim.sim");
		es.setName("TestSim");
		es.setAuthor("Some guy");
		es.setDescription("a test simulation");
		es.setEvolution(true);
		es.setGeneticSelectionMethod(0);
		es.addVehicle("xml/vehicles/default_vehicle1.veh");
		es.addVehicle("xml/vehicles/default_vehicle1.veh");
		es.setEnvironment("xml/environments/default_environment1.env");
		es.saveSimulation();
		Simulation e = new Simulation("xml/simulations/testsim.sim");
		e.printSimDetails();
	}
}
