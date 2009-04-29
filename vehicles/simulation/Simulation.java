package vehicles.simulation;

import vehicles.vehicle.*;
import vehicles.environment.*;
import vehicles.util.UtilMethods;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
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
	protected String simulationName = null, author = null, lastModified = null, description; //the name of this vehicle
    protected String fileName = null;
	protected Environment enviro = null; //the environment to use for this simulation
	protected Vector <Vehicle> vehicles; //the vehicles in this simulation 

	/***** Variables that will affect how the simulation runs *****/
	/* By default all options are off */
	protected boolean perishable_vehicles = false, evolution = false;
	protected int gen_selection = 0; //genetic selection algorithm to use 0 - X are valid
	protected int repro_method = 0; //reproduction method. 0 - 2 are valid
	protected int n = 0;
	public SimulationLog log = null; //this is public so it can be passed around.
	
	public Simulation(){
		vehicles = new Vector<Vehicle>();
		this.log = new SimulationLog();
	}

	public Simulation(String filename){
		try{			
			DOMParser p = new DOMParser();
            
			System.out.println("Opening file: " + filename);
			p.parse(filename); //get a parsed version of the file into memory
			Document dom = p.getDocument();

			/*Get the root node, check this xml file IS a simulation file
			 * if it is not a simulation file, print an error and return
			 */
			Node root = dom.getDocumentElement(); //get the root element from the document
			if(!root.getNodeName().equals("Simulation")){
				System.err.println("The file '" + filename + "' is NOT a simulation file.");
				return;
			}

			/*If valid Simulation file, continue*/
			vehicles = new Vector<Vehicle>();
			this.xmlLocation = filename;
            this.setFileName();

			/* The following recursive method is used to get the values of all attributes
			 * apart from the vehicles paths
			 */	
			handleNode(root); //recursive function to handle the nodes*/

			/* Fill the vector with the vehicles pointed at in the file */
			NodeList vehiclePaths = dom.getElementsByTagName("VehiclePath");
			processVehiclePaths(vehiclePaths);
			
			this.log = new SimulationLog();

		}catch(Exception e){
			e.printStackTrace();
			System.err.println("An error occurred while creating an Environment from the file '" +
					filename + "'. Please check that this file exists.");

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
			String p = UtilMethods.formatFilePath(vehicle_location);
			this.vehicles.add(new Vehicle(p));
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
				this.setName(node_value);
			}
			else if(name.equals("Author")){
				this.setAuthor(node_value);
			}
			else if(name.equals("Description")){
				this.setDescription(node_value);
			}
			else if(name.equals("LastModified")){
				this.setLastModified(node_value);
			}
			else if(name.equals("EnvironmentPath")){
				this.setEnvironment(UtilMethods.formatFilePath(node_value));
			}
			else if(name.equals("perishable_vehicles")){
				this.setPerishableVehicles(Boolean.valueOf(node_value));
			}
			else if(name.equals("evolution")){
				this.setEvolution(Boolean.valueOf(node_value));
			}
			else if(name.equals("genetic_selection_method")){
				this.setGeneticSelectionMethod(Integer.parseInt(node_value));
			}
			else if(name.equals("reproduction_method")){
				this.setReproductionMethod(Integer.parseInt(node_value));
			}
			else if(name.equals("n_for_selection")){
				this.setN(Integer.parseInt(node_value));
			}
			else break;
		}
	}

	/***** Getter Methods *****/

	public String getEnvironmentPath(){
		return this.enviro.getFileLocation();
	}
	
	public String getXmlLocation() {
		return xmlLocation;
	}
	
	public String getDescription(){
		this.description = this.description.replace('\n', ' ');
		return this.description;
	}

	public String getName() {
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

	public boolean getEvolution(){
		return this.evolution;
	}

	public int getGeneticSelectionMethod(){
		return this.gen_selection;
	}
	
	public int getN(){
		return this.n;
	}

	public int getReproductionMethod(){
		return this.repro_method;
	}
	
	public String getLastModified(){
		return this.lastModified;
	}

	/***** Setter Methods *****/

	public void setXmlLocation(String xmlLocation) {
		this.xmlLocation = xmlLocation;
        this.setFileName();
	}

	public void setName(String simName) {
		this.simulationName = simName;
	}

	public void setAuthor(String name){
		this.author = name;
	}
	
	public void setDescription(String d){
		this.description = d;
	}

	public void setVehicles(Vector<Vehicle> veh) {
		this.vehicles = veh;
	}

	public void addVehicle(String c){
		String p = UtilMethods.formatFilePath(c);
		if(vehicles.size() >= 2){
			System.err.print("Cannot do taht");
		}
		vehicles.add(new Vehicle(c));
	}
	
	public void addVehicle(Vehicle vc){
		vehicles.add(vc);
	}

	public void setEnvironment(Environment e){
		this.enviro = e;
	}

	public void setEnvironment(String filepath){
		this.enviro = new Environment(filepath);
	}

	public void setPerishableVehicles(boolean perish){
		this.perishable_vehicles = perish;
	}
	
	public void setEvolution(boolean evo){
		this.evolution = evo;
	}

	public void setGeneticSelectionMethod(int gen){
		this.gen_selection = gen;
	}

	public void setReproductionMethod(int r){
		this.repro_method = r;
	}

	public void setN(int N){
		if(N == 0){
			this.n = 1;
		}
		else{
			this.n = N;
		}
	}
	
	public void setLastModified(String timeStamp){
		this.lastModified = timeStamp;
	}

    public String getFileName(){
		return this.fileName;
	}

	public void setFileName(String f){
		this.fileName = f;
	}

	public void setFileName(){
		String filename = this.xmlLocation;
		String sep = File.separator;
		String[] parts = filename.split("\\" + sep);
		this.fileName = parts[parts.length - 1];
	}

	/**** Other Methods ****/

	/**
	 * Method used to find out if the current simulation has enough data to be saved
	 * @return
	 */
	public boolean isSaveable(){
		return (this.vehicles.size() >= 1 && this.simulationName != null && this.enviro != null);
	}

	/**
	 * Void method that will print out all of the details about the current Simulation object
	 */
	public void printSimDetails(){
		System.out.println("Name\t" + this.getName());
		System.out.println("Author\t" + this.getAuthor());
		System.out.println("Description\t" + this.description);
		System.out.println("Last Modified\t" + this.getLastModified());
		System.out.println("Location\t" + this.getXmlLocation());
		if(this.vehicles != null){
			System.out.println("NumVehicles\t" + this.vehicles.size());
			System.out.println("\tVehicle Paths");
			for(int i = 0; i < vehicles.size(); i++){
				System.out.println(vehicles.elementAt(i).toString());
			}
		}
		else{
			System.out.println("Vehicles were never initialised for this Simulation.");
			System.out.println("Possibly due to passing an incorrect XML file or never adding " +
					"vehicles to the Simulation");
		}
		if(this.enviro != null){
			System.out.println("Environment Path\t" + this.getEnvironment().getFileLocation());
		}
		else{
			System.out.println("Environment Path not found, or has not been properly initialisd!");
		}
		System.out.println("\tRuntime Options");
		System.out.println("Vehicles can perish\t" + this.getPerishableVehicles());
		System.out.println("Vehicles can evolve\t" + this.getEvolution());
		System.out.println("Genetic Algorithm\t" + this.getGeneticSelectionMethod());
	}
	
    public String toString(){
		return this.simulationName + " (" + this.fileName + ")";
	}
    public void addVehicles(Vehicle[] array){
		for(int i = 0; i < array.length ; i++){
			this.vehicles.add(array[i]);
		}
	}
    public Vehicle[] getVehicleArray(){
    	Vehicle[] vehs = new Vehicle[this.vehicles.size()];
    	return this.vehicles.toArray(vehs);
    }
    public void addVehiclesFromArrayList(List<EditorVehicle> list){
        vehicles.clear();
    	Iterator<EditorVehicle> it = list.iterator();
    	while(it.hasNext()){
    		Vehicle v = new Vehicle(it.next());
    		this.vehicles.add(v);
    	}
    }
}