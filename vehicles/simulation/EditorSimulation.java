package vehicles.simulation;
import vehicles.vehicle.*;
import vehicles.environment.*;

import java.io.*;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



/**
 * A simulation class for use in the Simulation Editor, essentially a wrapper around some
 * XML generation and manipulation. Moddelled on Karls EditorVehicle class 
 * @author Shaun
 *
 */

public class EditorSimulation extends Simulation{
	Document  xmldoc; //the XML document we are creating, stored as an object in memory
	Element root;//the root element of the document

	/**
	 * Write an element into an XML file
	 * @param elemName The name of the attribute
	 * @param elemValue The value for this attribute
	 * @param xmldoc The document to write into
	 */
	public void writeXMLEntry(String elemName, String elemValue, Document xmldoc){
		Element nameElement = xmldoc.createElement(elemName);
		Text nameText = xmldoc.createTextNode(elemValue);
		nameElement.appendChild(nameText);//add in the text to the element
		root.appendChild(nameElement);//and add this new element to the document

	}

	/**
	 * Constructor
	 * @param fileName filename to use for this object
	 */
	public EditorSimulation(String fileName){
		xmlLocation = fileName;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Simulation");
	}

	public EditorSimulation(String fileName, boolean newSim){
		super(fileName);
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Simulation");
	}

	/**
	 * Add a simulation name attribute to the XML document being created
	 * @param name The name to use for this Simulation
	 */
	public void addSimulationName(String name){
		writeXMLEntry("Name", name, xmldoc);
	}

	/**
	 * Add an author attribute to the XML document being created
	 * @param author The name of the author of this simulation
	 */
	public void addSimulationAuthor(String author){
		writeXMLEntry("Author", author, xmldoc);
	}

	/**
	 * Add a vehicle XML file path entry into this Simulation's document
	 * @param vp The vehicle path to add
	 */
	public void addVehiclePath(String vp){
		writeXMLEntry("VehiclePath", vp, xmldoc);
	}

	/**
	 * Add a vehicle XML file path entry into this Simulation's document
	 * @param v The vehicle whoe's path you wish to add
	 */
	public void addVehiclePath(Vehicle v){
		writeXMLEntry("VehiclePath", v.getXmlLocation(), xmldoc);
	}

	/**
	 * Add an environment XML file path entry into this Simulation's document
	 * @param ep The environment path you wish to add
	 */
	public void addEnvironmentPath(String ep){
		writeXMLEntry("EnvironmentPath", ep, xmldoc);
	}

	/**
	 * Add an environment XML file path entry into this Simulation's document
	 * @param e The environment who's path you wish to add
	 */
	public void addEnvironmentPath(Enviroment e){
		writeXMLEntry("EnvironmentPath", e.getFileLocation(), xmldoc);
	}

	/** 
	 * Add The Last Modified Time Stamp to the Simulation's document
	 */
	public void addLastModified(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		writeXMLEntry("LastModified", dateFormat.format(date), xmldoc);
	}

	/**
	 * Add a perishable vehicles entry into the simulations document
	 * @param pv The value of perishable_vehicles to write
	 */
	public void addPerishableVehicles(boolean pv){
		writeXMLEntry("perishable_vehicles", Boolean.toString(pv) , xmldoc);
	}

	/**
	 * Add a regenerating elements entry into the simulations document
	 * @param re The value of regenerating_elements to write
	 */
	public void addRegeneratingElements(boolean re){
		writeXMLEntry("regenerating_elements", Boolean.toString(re) , xmldoc);
	}

	/**
	 * Add a perishable elements entry into the simulations document
	 * @param pe The value of perishable_elements to write
	 */
	public void addPerishableElements(boolean pe){
		writeXMLEntry("perishable_elements", Boolean.toString(pe) , xmldoc);
	}

	/**
	 * Add an evolution entry into the simulations document
	 * @param evo The value of evolution to write
	 */
	public void addEvolution(boolean evo){
		writeXMLEntry("evolution", Boolean.toString(evo) , xmldoc);
	}

	/**
	 * Add a reproduction method entry into the simulations document
	 * @param rep The value of reproduction_method to write
	 */
	public void addPerishableElements(int rep){
		writeXMLEntry("reproduction_method", Integer.toString(rep) , xmldoc);
	}

	/**
	 * Add a sensor viw entry into the simulations document
	 * @param sv The value of sensor_view to write
	 */
	public void addSensorView(boolean sv){
		writeXMLEntry("sensor_view", Boolean.toString(sv) , xmldoc);
	}

	/**
	 * Add a genetic selection method entry into the simulations document
	 * @param gs The value of genetic_selection_method to write
	 */
	public void addGeneticSelectionMethod(int gs){
		writeXMLEntry("genetic_selection_method", Integer.toString(gs) , xmldoc);
	}

	/**
	 * Writes a boolean of name name to the xml file xmldoc
	 * @param name the name pf the xml entry to write
	 * @param value the value of the boolean to write
	 */
	public void addBoolean(String name, boolean value){
		writeXMLEntry(name, Boolean.toString(value), xmldoc);
	}

	/**
	 * Writes a string of name name to the xml file xmldoc
	 * @param name the name of the xml entry to write
	 * @param value the value of the string to write
	 */
	public void addString(String name, String value){
		writeXMLEntry(name, value, xmldoc);
	}

	/**
	 * Writes an integer of name name to the xml file xmldoc
	 * @param name the name of the xml entry to write
	 * @param value the value of the integer to write
	 */
	public void addInteger(String name, int value){
		writeXMLEntry(name, Integer.toString(value),  xmldoc);
	}

	/**
	 * Write out the current Vehicle to a file specified by the filename attribute. This method
	 * will save the vehicle object as it is to disk, so make sure it's only called when we're finished
	 * with it
	 */
	public void saveEnvironment(){
		if (!this.isSaveable()){
			System.out.println("Simulation Not saveable yet");
			return;
		}

		try{
			/*Form the XML document by saving the various attributes*/
			//Add the simulation name
			this.addString("Name", this.simulationName);
			//add the author name
			this.addString("Author", this.author);
			//add modified data stamp
			this.addLastModified();
			//add the vehicle file paths
			Iterator it = this.vehicles.iterator();
			while(it.hasNext()){
				this.addVehiclePath((Vehicle)it.next());
			}
			//add the environment file path
			//this.addString("EnvironmentPath", this.enviro.getFileLocation());
			//add perishable vehicles
			this.addBoolean("perishable_vehicles", this.perishable_vehicles);
			//Add regenerating_elements
			this.addBoolean("regenerating_elements", this.regenerating_elements);
			//Add perishable_elements
			this.addBoolean("perishable_elements", this.perishable_elements);
			//Add evolution
			this.addBoolean("evolution", this.evolution);
			//Add reproduction_method
			this.addInteger("reproduction_method", this.repro_method);
			//Add sensor_view
			this.addBoolean("sensor_view", this.sensor_view);
			//Add Genetic Selection Mehod
			this.addInteger("genetic_selection_methodd", this.gen_selection);

			xmldoc.appendChild(root); //finalise the XML document
			/*Now take the file in RAM and write it out to disk*/
			FileOutputStream fos = new FileOutputStream(xmlLocation);
			OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);
			of.setIndent(1);
			of.setIndenting(true);
			XMLSerializer serializer = new XMLSerializer(fos,of);//prepare a serialiser for
			//generating XML documents
			// As a DOMSerializer
			serializer.asDOMSerializer();
			serializer.serialize( xmldoc.getDocumentElement() );//get the root element and start writing
			fos.close();
		}catch(Exception e ){
			e.printStackTrace();
		}
	}
}


