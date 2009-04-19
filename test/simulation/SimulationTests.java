package test.simulation;
import vehicles.simulation.*;
import vehicles.vehicle.*;

/**A main class for testing of various elements in the simulation.
* Please DON'T upload binary files produced by compiling this into the repo.
*/
public class SimulationTests {

	public static void main(String[] args){
		
		/* Testing the creation of a new simulation from an xml file*/
		System.out.print("\n\nTESTING CREATION OF SIMULATION FROM XML FILE");
		Simulation a = new Simulation("src/test/XML/simulation.xml");
		a.printSimDetails();
		
		
		/*Testing the creation of a simulation by providing data*/
		System.out.println("\n\nTESTING CREATION OF SIMULATION FROM INPUTTING DATA");
		Simulation b = new Simulation();
		b.setAuthor("Shaun");
		b.setSimulationName("Test 2");
		b.setGeneticSelectionMethod(5);
		b.setEnvironment("A complete test yo");
		for(int i = 0; i < 10; i++){
			b.addVehicle(new Vehicle());
		}
		b.setPerishableElements(true);
		b.setPerishableVehicles(false);
		b.setRegeneratingElements(false);
		b.setReproductionMethod(2);
		b.setXmlLocation("my/test/example");
		b.printSimDetails();
		
		
		
		/* Testing creation of simulation XML file */
		System.out.println("Testing the creation of an xml doc from specified data");
		EditorSimulation mySimEditor = new EditorSimulation("src/test/XML/my_simulation_test.xml");
		mySimEditor.setAuthor("Shaun");
		mySimEditor.setSimulationName("Test 3");
		mySimEditor.setGeneticSelectionMethod(5);
		mySimEditor.setEnvironment("I do not exist :D");
		Vehicle v;
		for(int i = 0; i < 10; i++){
			v = new Vehicle();
			v.setXmlLocation("VehicleTest_".concat(Integer.toString(i)));
			mySimEditor.addVehicle(v);
		}
		mySimEditor.setPerishableVehicles(false);
		mySimEditor.setRegeneratingElements(false);
		mySimEditor.saveSimulation();
		
		Simulation test = new Simulation("src/test/XML/my_simulation_test.xml");
		test.printSimDetails();
		
		/* Testing the modification of an existing document */
		System.out.println("TESTING MODIFICATION OF EXISTING XML DOCUMENT");
		EditorSimulation editor2 = new EditorSimulation("src/test/XML/simulation.xml", true);
		editor2.printSimDetails();
		editor2.setSimulationName("New NAME!");
		editor2.saveSimulation();
		editor2 = new EditorSimulation("src/test/XML/simulation.xml", true);
		editor2.printSimDetails();
		
		Vehicle veh = a.getVehicle_SelectionBased();
		
	}
}