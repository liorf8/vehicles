package vehicles.vehicle;

import java.io.*;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.*;

import vehicles.util.UtilMethods;

/**
 * A vehicle class for use in the Vehicle Editor, essentially a wrapper around some
 * XML generation and manipulation. 
 * @author Karl
 *
 */
public class EditorVehicle extends Vehicle {
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
	public EditorVehicle(String fileName){
		xmlLocation = fileName;
		this.setFileName();
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Vehicle");
	}

	public EditorVehicle(String fileName, boolean newVeh){
		super(fileName);
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("Vehicle");
	}

	/**
	 * Add a vehicle name attribute to the XML document being created
	 * @param name The name to use for this Vehicle
	 */
	public void addVehicleName(String name){
		writeXMLEntry("name", name, xmldoc);
	}

	/**
	 * Add a vehicle max_battery_capacity attribute to the XML document being created
	 * @param max_capacity The name to use for this Vehicle
	 */
	public void addMaxBatteryCapacity(String max_capacity){
		writeXMLEntry("max_battery_capacity", max_capacity, xmldoc);
	}

	/**
	 * Add a vehicle maximum_memory attribute to the XML document being created
	 * @param max_mem The desired maximum memory for this vehicle
	 */
	public void addMaxMem(String max_mem){
		writeXMLEntry("maximum_memory", max_mem, xmldoc);
	}
	

	/**
	 * Add a vehicle learning rate attribute to the XML document being created
	 * @param l The desired learning rate for this vehicle
	 */
	public void addLearningRate(String l){
		writeXMLEntry("learning_rate", l, xmldoc);
	}
	
	/**
	 * Add a vehicle curr_battery_capacity attribute to the XML document being created
	 * @param name The name to use for this Vehicle
	 */
	public void addCurrBatteryCapacity(String curr_capacity){
		writeXMLEntry("curr_battery_capacity", curr_capacity, xmldoc);
	}

	/**
	 * Add a vehicle motor strength attribute to the XML document being created
	 * @param strength The strength
	 */
	public void addMotorStrength(String str){
		writeXMLEntry("motorStrength", str, xmldoc);
	}
    
	/**
	 * Add a vehicle aggression attribute to the XML document being created
	 * @param aggr The aggression
	 */
	public void addAggression(String aggr){
		writeXMLEntry("aggression", aggr, xmldoc);
	}
	
	
	/**
	 * Add a vehicle component XML entry into this Vehicle's document
	 * @param vc The vehicle component to add
	 */
	public void addVehicleComponentXML(VehicleComponent vc){
		root.appendChild(xmldoc.adoptNode(vc.getRootElement().cloneNode(true)));
		///	System.out.println("Append component!");

	}

	/**
	 * Add a vehicle vehicleColour XML entry into this Vehicle's document
	 * @param vc The vehicleColour to add
	 */
	public void addVehicleColour(VehicleColour c){
		root.appendChild(xmldoc.adoptNode(c.getRootElement().cloneNode(true)));
	}
	/**
	 * Add a vehicle memory XML entry into this Vehicle's document
	 * @param vc The memory unit to add
	 */
	public void addVehicleMemory(MemoryUnit mu){
		root.appendChild(xmldoc.adoptNode(mu.getRootElement().cloneNode(true)));
	}
	/**
	 * Add a vehicle battery XML entry into this Vehicle's document
	 * @param vc The battery to add
	 */
	public void addVehicleBattery(VehicleBattery b){
		root.appendChild(xmldoc.adoptNode(b.getRootElement().cloneNode(true)));
	}

	/** 
	 * Add The Last Modified Time Stamp to the Simulation's document
	 */
	public void writeTimeStamp(Document xmldoc){
		String t = UtilMethods.getTimeStamp();
		writeXMLEntry("LastModified", t, xmldoc);
		this.setLastModified(t);
	}
	
	/**
	 * Write out the current Vehicle to a file specified by the filename attribute. This method
	 * will save the vehicle object as it is to disk, so make sure it's only called when we're finished
	 * with it
	 */
	public void saveVehicle(){
		FileOutputStream fos = null;
		try{
			/*Form the XML document by saving the various attributes*/
			if(this.vehicleName != null){ 
				addVehicleName(vehicleName);
			}
			if(this.vehicleAuthor != null){
				this.writeXMLEntry("author", this.vehicleAuthor, xmldoc);
			}
			if(this.vehicleDescription != null){
				this.writeXMLEntry("description", this.vehicleDescription, xmldoc);
			}
			this.writeTimeStamp(xmldoc);
			//this.addMaxBatteryCapacity(Integer.toString(max_battery_capacity));
			//this.addCurrBatteryCapacity(Integer.toString(curr_battery_capacity));
			//this.addMaxMem(Integer.toString(max_mem));
			//this.addLearningRate(Integer.toString(learning_rate));
			this.addMotorStrength(Integer.toString(this.motorStrength));
			this.addAggression(Integer.toString(this.aggression));
            this.addVehicleColour(this.vehicleColour);
            this.addVehicleMemory(this.mu);
            this.addVehicleBattery(this.battery);
			
			if(this.components != null){
				//System.out.println(components.capacity());
				Iterator<VehicleComponent> it = components.iterator();
				int count = 0;
				while(it.hasNext()){
					VehicleComponent curr = it.next();
					/*
					 * Object o = curr.getRootElement().getFirstChild(); //determine if the xml tree is populated 
					if(o == null){ //if not, populate it(convert object attributes to xml tree)
						curr.toInternalXML();
					}
					*/
					curr.setFilename("temp");
					this.addVehicleComponentXML(curr);
					count++;
				}
			}
			xmldoc.appendChild(root); //finalise the XML document
			/*Now take the file in RAM and write it out to disk*/
			try{
				fos = new FileOutputStream(xmlLocation);
			}catch(FileNotFoundException e ){
				File f = new File(xmlLocation);
				f.createNewFile();
				fos = new FileOutputStream(xmlLocation);
			}
			
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
	
	public String toString(){
		return this.vehicleName + " (" + this.fileName + ")";
	}
}
