package test.simulation;
import vehicles.simulation.*;
import vehicles.vehicle.*;

import java.util.Vector;
import java.util.Arrays;
import java.util.Collections;



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
		es.addVehicle("xml/vehicles/hungry.veh");
		es.addVehicle("xml/vehicles/angry.veh");
		es.setEnvironment("xml/environments/desert.env");
		es.saveSimulation();
	}


//	// Testing the creation of a new simulation from an xml file
//	System.out.print("\n\nTESTING CREATION OF SIMULATION FROM XML FILE");
//	Simulation a = new Simulation("xml/simulations/my_simulation_test.xml");
//	Vector<Vehicle> ve = a.getVehicles();
//	int size = ve.size();
//	System.out.println("PRINTING DETAILS FOR VEHCILES POINTED AT IN FILE: 'xml/simulations/simulation.xml'");
//	for(int i = 0; i < size; i++){
//	ve.elementAt(i).printDetails();
//	}
//	Environment ee = a.getEnvironment();
//	System.out.println("PRINTING DETAILS FOR ENVIRONMENT IN FILE 'xml/simulations/simulation.xml'");
//	ee.printDetails();
//	System.out.println("PRINTING DETAILS FOR 'Simulation.xml'");
//	a.printSimDetails();


//	/*Testing the creation of a simulation by providing data*/
//	System.out.println("\n\nTESTING CREATION OF SIMULATION FROM INPUTTING DATA");
//	Simulation b = new Simulation();
//	b.setAuthor("Shaun");
//	b.setName("Test 2");
//	b.setGeneticSelectionMethod(5);
//	b.setEnvironment("A complete test yo");
//	for(int i = 0; i < 10; i++){
//	b.addVehicle(new Vehicle());
//	}
//	b.setPerishableVehicles(false);
//	b.setXmlLocation("my/test/example");
//	b.setEnvironment("xml/environments/desert.xml");
//	b.printSimDetails();



//	/* Testing creation of simulation XML file */
//	System.out.println("Testing the creation of an xml doc from specified data");
//	EditorSimulation mySimEditor = new EditorSimulation("xml/simulations/my_simulation_test.xml");
//	mySimEditor.setAuthor("Shaun");
//	mySimEditor.setName("Test 3");
//	mySimEditor.setGeneticSelectionMethod(5);
//	mySimEditor.setEnvironment("I do not exist :D");
//	Vehicle v;
//	for(int i = 0; i < 10; i++){
//	v = new Vehicle();
//	v.setXmlLocation("VehicleTest_".concat(Integer.toString(i)));
//	mySimEditor.addVehicle(v);
//	}
//	mySimEditor.setPerishableVehicles(false);
//	mySimEditor.setEnvironment("xml/environments/desert.xml");
//	mySimEditor.saveSimulation();

//	Simulation test = new Simulation("xml/simulations/my_simulation_test.xml");
//	test.printSimDetails();

//	/* Testing the modification of an existing document */
//	System.out.println("TESTING MODIFICATION OF EXISTING XML DOCUMENT");
//	EditorSimulation editor2 = new EditorSimulation("xml/simulations/my_simulation_test.xml", true);
//	editor2.printSimDetails();
//	editor2.setName("New NAME!");
//	editor2.saveSimulation();
//	editor2 = new EditorSimulation("xml/simulations/my_simulation_test.xml", true);
//	editor2.printSimDetails();

//	Vehicle veh = a.getVehicle_SelectionBased();

//	/* Testing the creation of a new simulation from an incorrect xml file*/
//	System.out.println("\n\nTESTING CREATION OF SIMULATION FROM AN INCORRECT XML FILE");
//	Simulation wrong = new Simulation("xml/simulations/hungry.xml");
//	wrong.printSimDetails();

//	System.out.println("\n\nTESTING STRING FORMATTING IN JAVA UTILS");
//	String teststst = "heya how ARFEEW 0987654 ][#''#;];]";
//	System.out.println("String is now: " + teststst);
//	teststst = UtilMethods.formatString(teststst);
//	System.out.println("String is now: " + teststst);
}
