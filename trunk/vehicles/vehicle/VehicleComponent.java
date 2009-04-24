package vehicles.vehicle;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * 
 * @author Karl
 *	A class representing a vehicle component in memory. It is currently geared towards use solely 
 *	in the editors, though the implementation of the engine may change this.
 *
 */
public class VehicleComponent {
	private Document xmldoc; //the XML document we are creating, stored as an object in memory
	private Element root;//the root element of the document
	private String filename;// filename to associate the XML document with

	private String VehicleComponentName = null; //the name of this component
	private String VehicleComponentType = null; //the type of this component
	
	private String VehicleComponentLeftSensorRadius = null;
	private String VehicleComponentLeftSensorLight = null; 
	private String VehicleComponentLeftSensorHeat = null; 
	private String VehicleComponentLeftSensorWater = null; 
	private String VehicleComponentLeftSensorPower = null; 
	
	private String VehicleComponentRightSensorRadius = null;
	private String VehicleComponentRightSensorLight = null; 
	private String VehicleComponentRightSensorHeat = null; 
	private String VehicleComponentRightSensorWater = null; 
	private String VehicleComponentRightSensorPower = null; 

	public String getVehicleComponentType() {
		return VehicleComponentType;
	}

	public void setVehicleComponentType(String vehicleComponentType) {
		VehicleComponentType = vehicleComponentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getVehicleComponentName() {
		return VehicleComponentName;
	}

	public void setVehicleComponentName(String vehicleComponentName) {
		VehicleComponentName = vehicleComponentName;
	}


	public String getVehicleComponentLeftSensorRadius() {
		return VehicleComponentLeftSensorRadius;
	}

	public void setVehicleComponentLeftSensorRadius(
			String vehicleComponentLeftSensorRadius) {
		VehicleComponentLeftSensorRadius = vehicleComponentLeftSensorRadius;
	}

	public String getVehicleComponentLeftSensorLight() {
		return VehicleComponentLeftSensorLight;
	}

	public void setVehicleComponentLeftSensorLight(
			String vehicleComponentLeftSensorLight) {
		VehicleComponentLeftSensorLight = vehicleComponentLeftSensorLight;
	}

	public String getVehicleComponentLeftSensorHeat() {
		return VehicleComponentLeftSensorHeat;
	}

	public void setVehicleComponentLeftSensorHeat(
			String vehicleComponentLeftSensorHeat) {
		VehicleComponentLeftSensorHeat = vehicleComponentLeftSensorHeat;
	}

	public String getVehicleComponentLeftSensorWater() {
		return VehicleComponentLeftSensorWater;
	}

	public void setVehicleComponentLeftSensorWater(
			String vehicleComponentLeftSensorWater) {
		VehicleComponentLeftSensorWater = vehicleComponentLeftSensorWater;
	}

	public String getVehicleComponentLeftSensorPower() {
		return VehicleComponentLeftSensorPower;
	}

	public void setVehicleComponentLeftSensorPower(
			String vehicleComponentLeftSensorPower) {
		VehicleComponentLeftSensorPower = vehicleComponentLeftSensorPower;
	}

	public String getVehicleComponentRightSensorRadius() {
		return VehicleComponentRightSensorRadius;
	}

	public void setVehicleComponentRightSensorRadius(
			String vehicleComponentRightSensorRadius) {
		VehicleComponentRightSensorRadius = vehicleComponentRightSensorRadius;
	}

	public String getVehicleComponentRightSensorLight() {
		return VehicleComponentRightSensorLight;
	}

	public void setVehicleComponentRightSensorLight(
			String vehicleComponentRightSensorLight) {
		VehicleComponentRightSensorLight = vehicleComponentRightSensorLight;
	}

	public String getVehicleComponentRightSensorHeat() {
		return VehicleComponentRightSensorHeat;
	}

	public void setVehicleComponentRightSensorHeat(
			String vehicleComponentRightSensorHeat) {
		VehicleComponentRightSensorHeat = vehicleComponentRightSensorHeat;
	}

	public String getVehicleComponentRightSensorWater() {
		return VehicleComponentRightSensorWater;
	}

	public void setVehicleComponentRightSensorWater(
			String vehicleComponentRightSensorWater) {
		VehicleComponentRightSensorWater = vehicleComponentRightSensorWater;
	}

	public String getVehicleComponentRightSensorPower() {
		return VehicleComponentRightSensorPower;
	}

	public void setVehicleComponentRightSensorPower(
			String vehicleComponentRightSensorPower) {
		VehicleComponentRightSensorPower = vehicleComponentRightSensorPower;
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
		nameElement.appendChild(nameText);//add in the text to the element
		root.appendChild(nameElement);//and add this new element to the document

	}	

	public VehicleComponent(){
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("VehicleComponent");
	}

	/**
	 * Constructor
	 * @param fileName filename to use for this object
	 */
	public VehicleComponent(String fileName){
		filename = fileName;
		xmldoc= new DocumentImpl();
		root = xmldoc.createElement("VehicleComponent");
	}

	/**
	 * Add a vehicle component name attribute to the XML document being created
	 * @param name The name to use for this VehicleComponent
	 */
	public void addVehicleComponentName(String name){
		writeXMLEntry("name", name, xmldoc);
	}

	/**
	 * Add a vehicle component temperament attribute(LeftSensor | RightSensor) to the XML document being created
	 * @param temperament One of (LeftSensor | RightSensor)
	 */
	public void addVehicleComponentType(String type){
		writeXMLEntry("type", type, xmldoc);
	}
	
	
	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addLeftSensorRadius(String radius){
		writeXMLEntry("LeftSensorRadius", radius, xmldoc);
	}

	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addLeftSensorLight(String light){
		writeXMLEntry("LeftSensorLight", light, xmldoc);
	}

	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addLeftSensorHeat(String heat){
		writeXMLEntry("LeftSensorHeat", heat, xmldoc);
	}

	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addLeftSensorPower(String power){
		writeXMLEntry("LeftSensorPower", power, xmldoc);
	}
	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addLeftSensorWater(String water){
		writeXMLEntry("LeftSensorWater", water, xmldoc);
	}
	
	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addRightSensorRadius(String radius){
		writeXMLEntry("RightSensorRadius", radius, xmldoc);
	}

	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addRightSensorLight(String light){
		writeXMLEntry("RightSensorLight", light, xmldoc);
	}

	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addRightSensorHeat(String heat){
		writeXMLEntry("RightSensorHeat", heat, xmldoc);
	}

	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addRightSensorPower(String power){
		writeXMLEntry("RightSensorPower", power, xmldoc);
	}

	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addRightSensorWater(String water){
		writeXMLEntry("RightSensorWater", water, xmldoc);
	}
	
	/** 
	 * Generate an internal XML representation of the object and its attributes
	 */
	public void toInternalXML(){
		if(VehicleComponentName != null){
			this.addVehicleComponentName(VehicleComponentName);
		}
		if(this.VehicleComponentType != null){
			this.addVehicleComponentType(VehicleComponentType);			
		}
		if(this.VehicleComponentLeftSensorHeat!=null){
			this.addLeftSensorHeat(VehicleComponentLeftSensorHeat);
		}
		if(this.VehicleComponentLeftSensorLight!=null){
			this.addLeftSensorLight(VehicleComponentLeftSensorLight);
		}
		if(this.VehicleComponentLeftSensorPower!=null){
			this.addLeftSensorPower(VehicleComponentLeftSensorPower);
		}
		if(this.VehicleComponentLeftSensorWater!=null){
			this.addLeftSensorWater(VehicleComponentLeftSensorWater);
		}
		if(this.VehicleComponentRightSensorHeat!=null){
			this.addRightSensorHeat(VehicleComponentRightSensorHeat);
		}
		if(this.VehicleComponentRightSensorLight!=null){
			this.addRightSensorLight(VehicleComponentRightSensorLight);
		}
		if(this.VehicleComponentRightSensorPower!=null){
			this.addRightSensorPower(VehicleComponentRightSensorPower);
		}
		if(this.VehicleComponentRightSensorWater!=null){
			this.addRightSensorWater(VehicleComponentRightSensorWater);
		}
		//xmldoc.appendChild(root); this is only needed for saving the actual component to file, we won't be doing that in the end program
	}

	/**
	 * Get the root of this vehicle component, call this method to get the component for inclusion
	 * in a Vehicle XML document 
	 * @return The root element of this VehicleComponent
	 */
	public Element getRootElement(){
		this.toInternalXML(); //brilliantly-placed function call that fixes all sorts of weird errors and cleans up the interface
		return root; //now the root element has had all attributes attached to it, return it
	}
}
