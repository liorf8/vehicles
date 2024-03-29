package vehicles.simulation;

import vehicles.vehicle.*;
import vehicles.environment.*;
import vehicles.util.*;

import java.io.*;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.*;

/**
 * A simulation class for use in the Simulation Editor, essentially a wrapper around some
 * XML generation and manipulation. Modelled on Karls EditorVehicle class
 *
 * @author Shaun
 */
public class EditorSimulation extends Simulation {

	Document xmldoc;    //the XML document we are creating, stored as an object in memory
	Element root;       //the root element of the document

    /**
     * Default Constructor for creating a new EditorSimulation object
     */
    public EditorSimulation(){
		xmldoc = new DocumentImpl();
		root = xmldoc.createElement("Simulation");
	}

	/**
	 * Constructor
	 * @param fileName filename to use for this object
	 */
	public EditorSimulation(String fileName){
		super(fileName);
		xmldoc = new DocumentImpl();
		root = xmldoc.createElement("Simulation");		
	}



	public EditorSimulation(String fileName, boolean newSim){
		this.xmlLocation = fileName;
		xmldoc = new DocumentImpl();
		root = xmldoc.createElement("Simulation");
	}

    /**
	 * Write an element into an XML file
	 * @param elemName The name of the attribute
	 * @param elemValue The value for this attribute
	 * @param xmldoc The document to write into
	 */
	public void writeXMLEntry(String elemName, String elemValue, Document xmldoc){
		Element nameElement = xmldoc.createElement(elemName);
		Text nameText = xmldoc.createTextNode(elemValue);
		nameElement.appendChild(nameText);  //add in the text to the element
		root.appendChild(nameElement);      //and add this new element to the document

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
	 * @param e The environment who's path you wish to add
	 */
	public void addEnvironmentPath(Environment e){
		writeXMLEntry("EnvironmentPath", e.getFileLocation(), xmldoc);
	}

	/** 
	 * Add The Last Modified Time Stamp to the Simulation's document
	 */
	public void addLastModified(){
		String t = UtilMethods.getTimeStamp();
		writeXMLEntry("LastModified", t, xmldoc);
		this.setLastModified(t);
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
	public void saveSimulation() {
        
		if (!this.isSaveable()){
			System.out.println("Simulation Not saveable yet");
			return;
		}

		try {
			/* Form the XML document by saving the various attributes */
			// Add the simulation name
			this.addString("Name", this.simulationName);
			// Add the author name
			this.addString("Author", this.author);
			// Add the description
			this.addString("Description", this.description);
			// Add modified data stamp
			this.addLastModified();
			// Add the vehicle file paths
			Iterator it = this.vehicles.iterator();
			while(it.hasNext()){
				this.addVehiclePath((Vehicle)it.next());
			}
			// Add the environment file path
			this.addString("EnvironmentPath", this.enviro.getFileLocation());
			// Add perishable vehicles
			this.addBoolean("perishable_vehicles", this.perishable_vehicles);
			// Add evolution
			this.addBoolean("evolution", this.evolution);
			// Add Genetic Selection Mehod
			this.addInteger("genetic_selection_method", this.gen_selection);
			// Add Reproduction Method
			this.addInteger("reproduction_method", this.repro_method);
			// Add N for genetic selection
			this.addInteger("n_for_selection", this.n);

			xmldoc.appendChild(root); // Finalise the XML document
            
			/* Now take the file in RAM and write it out to disk */
			FileOutputStream fos = new FileOutputStream(xmlLocation);
			OutputFormat of = new OutputFormat("XML","ISO-8859-1",true);
			of.setLineWidth(Integer.MAX_VALUE);

            /* prepare a serialiser for generating XML documents a s a DOMSerializer */
			XMLSerializer serializer = new XMLSerializer(fos,of);
			serializer.asDOMSerializer();
			serializer.serialize( xmldoc.getDocumentElement() ); // Get the root element and start writing

			fos.close();
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}

	public EditorVehicle[] getEditorVehicleArray(){
		EditorVehicle[] toReturn = new EditorVehicle[vehicles.size()];
		Iterator<Vehicle> it = vehicles.iterator(); int i = 0;
		while(it.hasNext()){
			EditorVehicle temp = new EditorVehicle(it.next());
			toReturn[i] = temp;			
			i++;
		}
		return toReturn;
	}
}

