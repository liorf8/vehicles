package vehicles.simulation;

import vehicles.vehicle.*;
import vehicles.environment.*;
import java.util.Vector;
import java.util.Random;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.xerces.parsers.DOMParser;

/**
 * 
 *
 * A class used to represent a simulation
 * @author Shaun  
 */

public class Simulation {
	protected String xmlLocation; //location of the XML file representing this simulation

	/*Attributes of an environment	*/
	protected String simulationName = null, author = null, lastModified = null; //the name of this vehicle
	protected Environment enviro = null; //the environment to use for this simulation
	protected Vector <Vehicle> vehicles; //the vehicles in this simulation 

	/***** Variables that will affect how the simulation runs *****/
	/* By default all options are off */
	protected boolean perishable_vehicles = false, regenerating_elements = false,
	perishable_elements = false, evolution = false, sensor_view = false;
	protected int repro_method = 0; //reproduction method to be used. 0 - X are valid
	protected int gen_selection = 0; //genetic selection algorithm to use 0 - X are valid

	public Simulation(){
		vehicles = new Vector<Vehicle>();
	}

	public Simulation(String filename){
		try{
			xmlLocation = filename;
			Vector <String> vehicle_locations = new Vector<String>();
			String enviro_location = "";
			vehicles = new Vector<Vehicle>();
			DOMParser p = new DOMParser();
			System.out.println("Opening file: " + xmlLocation);
			p.parse(xmlLocation); //get a parsed version of the file into memory
			Document dom = p.getDocument();

			/* The following recursive method is used to get the values of all attributes
			 * apart from the vehicles paths
			 */	
			Node root = dom.getDocumentElement(); //get the root element from the document
			handleNode(root); //recursive function to handle the nodes*/

			/* Fill the vector with the vehicles pointed at in the file */
			NodeList vehiclePaths = dom.getElementsByTagName("VehiclePath");
			processVehiclePaths(vehiclePaths);


		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * Take a NodeList representing file paths to vehicles and creates 
	 * vehicle objects from these paths and adds them to the vehile vector
	 * @param vehicleList A NodeList of vehicle paths taken from the XML file
	 */
	private void processVehiclePaths(NodeList vehicleList){
		int length = vehicleList.getLength();
		String vehicle_location = null;
		for(int i = 0; i < length; i++){
			vehicle_location = vehicleList.item(i).getChildNodes().item(0).getNodeValue();
			this.vehicles.add(new Vehicle(vehicle_location));
		}
	}


	/**
	 * Take in a node from an XML document and use it to instansiate parts of the object- for 
	 * internal class use only by other methods
	 * @param node The node to handle
	 */
	private void handleNode(Node node){
		int type = node.getNodeType();
		String name, node_value;
		switch (type) { //depending on the type of node, perform different actions
		case Node.ELEMENT_NODE: 
			NodeList children = node.getChildNodes();
			int len = children.getLength();
			for (int i=0; i<len; i++)
				handleNode(children.item(i));
			break;
		case Node.TEXT_NODE:
			name = node.getParentNode().getNodeName();
			node_value = node.getNodeValue();
			if(name.equals("Name")){
				this.setSimulationName(node_value);
			}
			else if(name.equals("Author")){
				this.setAuthor(node_value);
			}
			else if(name.equals("LastModified")){
				this.setLastModified(node_value);
			}
			else if(name.equals("EnvironmentPath")){
				this.setEnvironment(node_value);
			}
			else if(name.equals("perishable_vehicles")){
				this.setPerishableVehicles(Boolean.getBoolean(node_value));
			}
			else if(name.equals("regenerating_elements")){
				this.setRegeneratingElements(Boolean.getBoolean(node_value));
			}
			else if(name.equals("perishable_elements")){
				this.setPerishableElements(Boolean.getBoolean(node_value));
			}
			else if(name.equals("evolution")){
				this.setEvolution(Boolean.getBoolean(node_value));
			}
			else if(name.equals("reproduction_method")){
				this.setReproductionMethod(Integer.parseInt(node_value));
			}
			else if(name.equals("sensor_view")){
				this.setSensorView(Boolean.getBoolean(node_value));
			}
			else if(name.equals("genetic_selection_method")){
				this.setGeneticSelectionMethod(Integer.parseInt(node_value));
			}
			else break;
		}


	}

	/***** Getter Methods *****/

	public String getXmlLocation() {
		return xmlLocation;
	}

	public String getSimulationName() {
		return simulationName;
	}

	public String getAuthor(){
		return this.author;
	}

	public Vector<Vehicle> getVehicles() {
		return this.vehicles;
	}

	public Environment getEnvironment(){
		return this.enviro;
	}

	public boolean getPerishableVehicles(){
		return this.perishable_vehicles;
	}

	public boolean getRegeneratingElements(){
		return this.regenerating_elements;
	}

	public boolean getPerishableElements(){
		return this.perishable_elements;
	}

	public boolean getEvolution(){
		return this.evolution;
	}

	public boolean getSensorView(){
		return this.sensor_view;
	}

	public int getReproductionMethod(){
		return this.repro_method;
	}

	public int getGeneticSelectionMethod(){
		return this.gen_selection;
	}

	public String getLastModified(){
		return this.lastModified;
	}

	/***** Setter Methods *****/

	public void setXmlLocation(String xmlLocation) {
		this.xmlLocation = xmlLocation;
	}

	public void setSimulationName(String simName) {
		this.simulationName = simName;
	}

	public void setAuthor(String name){
		this.author = name;
	}

	public void setVehicles(Vector<Vehicle> veh) {
		this.vehicles = veh;
	}

	public void addVehicle(Vehicle vc){
		vehicles.add(vc);
	}

	public void setEnvironment(Environment e){
		this.enviro = e;
	}

	public void setEnvironment(String filepath){
		//TODO Fix environment class so this will work!
		//this.enviro = new Environment(filepath);
	}

	public void setPerishableVehicles(boolean perish){
		this.perishable_vehicles = perish;
	}

	public void setRegeneratingElements(boolean regen){
		this.regenerating_elements = regen;
	}

	public void setPerishableElements(boolean perish){
		this.perishable_elements = perish;
	}

	public void setEvolution(boolean evo){
		this.evolution = evo;
	}

	public void setSensorView(boolean s_view){
		this.sensor_view = s_view;
	}

	public void setReproductionMethod(int r){
		//TODO Find out which repro methods will be
		//used and make sure the user can only enter a valid one
		this.repro_method = r;
	}

	public void setGeneticSelectionMethod(int gen){
		//TODO Find out which genetic selection methods will be
		//used and make sure the user can only enter a valid one
		this.gen_selection = gen;
	}

	public void setLastModified(String timeStamp){
		this.lastModified = timeStamp;
	}

	/**** Other Methods ****/

	/**
	 * Method used to find out if the current simulation has enough data to be saved
	 * @return
	 */
	public boolean isSaveable(){
		return (this.vehicles.size() >= 2 && this.simulationName != null);
		//TODO Add this back in, when Environment is fixed ->   && this.enviro != null);
	}

	/**
	 * Void method that will print out all of the details about the current Simulation object
	 */
	public void printSimDetails(){
		System.out.println("Name\t" + this.getSimulationName());
		System.out.println("Author\t" + this.getAuthor());
		System.out.println("Last Modified\t" + this.getLastModified());
		System.out.println("Location\t" + this.getXmlLocation());
		System.out.println("NumVehicles\t" + this.vehicles.size());
		System.out.println("\tVehicle Paths");
		for(int i = 0; i < vehicles.size(); i++){
			System.out.println(vehicles.elementAt(i).getXmlLocation());
		}
		//System.out.println("Environment Path\t" + this.getEnvironment().getFileLocation());
		System.out.println("\tRuntime Options");
		System.out.println("Vehicles can perish\t" + this.getPerishableVehicles());
		System.out.println("Elements can perish\t" + this.getPerishableElements());
		System.out.println("Elements can regenerate\t" + this.getRegeneratingElements());
		System.out.println("Vehicles can evolve\t" + this.getEvolution());
		System.out.println("Reproduction Method\t" + this.getReproductionMethod());
		System.out.println("Sensor View\t" + this.getSensorView());
		System.out.println("Genetic Algorithm\t" + this.getGeneticSelectionMethod());
	}



	/*******************************************************************************************\
	/																							\
	/                      Genetic Selection Algorithms for a Simulation						\
	/																							\
	/*******************************************************************************************\


	//TODO fix vehicle battery_capacity and battery stuff so that fitness can be determined properly



	/**
	 * A selection method that will choose a vehicle from the vector of vehicles based
	 * on the value set for gen_selection
	 * 
	 * Values are as follows:
	 * 0 - 	Set by default, no selection method chosen. null returned
	 * 1 - 	Roulette - A selection operator in which the chance of a vehicle
	 *     	being selected is proportional to its fitness.
	 * 2 - 	Tournament - A selection operator which uses roulette selection N times
	 *     	to produce a tournament subset of vehicles. The best vehicle in this subset
	 *   	is then chosen as the selected vehicle to be the base for the next generation.
	 * 3 - 	Top Percent - A selection operator which randomly selects a vehicle from the top 
	 * 		N percent of the population as specified by the user.
	 * 4 - 	Best - A selection operator which selects the best vehicle (as determined by fitness).
	 * 		If there are two or more vehicle with the same level of top fitness, one of them is chosen
	 * 		randomly.
	 * 5 - Random - A selection operator which randomly selects a single vehicle from the population.
	 */ 
	public Vehicle getVehicle_SelectionBased(){
		//TODO Need methods to get user input at runtime from the gui for top N percent
		//and for Tournament. Random Numbers being used for now.
		switch(this.gen_selection){
		//Set by default, no selection method chosen
		case 0:
			System.out.println("No genetic selection method set! Return null");
			return null;
			//
		case 1:
			return this.getVehicleByRoulette();
		case 2:
			return this.getVehicleByTournament();
		case 3:
			return this.getVehicleByTop_N_Percent();
		case 4:
			return this.getVehicleByBest();
		case 5:
			return this.getVehicleByRandom();
		default:
			return null;
		}
	}


	/**
	 * A selection operator in which the chance of a vehicle being selected is 
	 * proportional to its fitness. This is where the concept of survival of the
	 * fittest comes into being. The fitness of a vehicle is related to the power 
	 * of its battery and motor
	 * 
	 * @return a vehicle chosen by roulette selection
	 */
	private Vehicle getVehicleByRoulette(){
		//TODO fill this in when vehicle fitness is decided upon . . again
		return new Vehicle();
	}


	/**
	 * A selection operator which uses roulette selection N times
	 * to produce a tournament subset of vehicles. The best vehicle in this subset
	 * is then chosen as the selected vehicle to be the base for the next generation.
	 * @return a vehicle chosen by roulette selection N times
	 */
	private Vehicle getVehicleByTournament(){
		return new Vehicle();
	}

	/**
	 * A selection operator which randomly selects a vehicle from the top 
	 * N percent of the population as specified by the user.
	 * @return a vehicle chosen from the top N percent
	 */
	private Vehicle getVehicleByTop_N_Percent(){
		Random ran = new Random(System.currentTimeMillis());
		int random = ran.nextInt(100) + 1;
		//TODO fill this in when vehicle fitness is decided upon . . again
		return new Vehicle();
	}

	/**
	 * A selection operator which selects the best vehicle (as determined by fitness).
	 * If there are two or more vehicle with the same level of top fitness, one of them is chosen
	 * randomly.
	 * @return the best vehicle in the set of vehicles
	 */
	private Vehicle getVehicleByBest(){
		Vehicle best;
		Iterator it = this.vehicles.iterator();
		while(it.hasNext()){
			//TODO fill this in when vehicle fitness is decided upon . . again
		}
		return new Vehicle();
	}

	/**
	 * A selection operator which randomly selects a single vehicle from the population.
	 * @return a random vehicle from the set of vehicles
	 */
	private Vehicle getVehicleByRandom(){
		Random ran = new Random(System.currentTimeMillis());
		int num_veh = this.vehicles.size();
		int random = ran.nextInt(num_veh);
		return this.vehicles.elementAt(random);
	}
}