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
public class VehicleComponent{
	private Document xmldoc; //the XML document we are creating, stored as an object in memory
	private Element root;//the root element of the document
	private String filename;// filename to associate the XML document with

	private String VehicleComponentName = null; //the name of this component
	private String VehicleComponentType = null; //the type of this component
	private String VehicleComponentBatteryCapacity = null; //battery capacity
	private String VehicleComponentMotorStrength = null; //motor strength
	private String VehicleComponentSensorRadius = null; //sensor radius
	private String VehicleComponentPosition = null; //position



	public String getVehicleComponentName() {
		return VehicleComponentName;
	}

	public void setVehicleComponentName(String vehicleComponentName) {
		VehicleComponentName = vehicleComponentName;
	}

	public String getVehicleComponentType() {
		return VehicleComponentType;
	}

	public void setVehicleComponentType(String vehicleComponentType) {
		VehicleComponentType = vehicleComponentType;
	}

	public String getVehicleComponentBatteryCapacity() {
		return VehicleComponentBatteryCapacity;
	}

	public void setVehicleComponentBatteryCapacity(
			String vehicleComponentBatteryCapacity) {
		VehicleComponentBatteryCapacity = vehicleComponentBatteryCapacity;
	}

	public String getVehicleComponentMotorStrength() {
		return VehicleComponentMotorStrength;
	}

	public void setVehicleComponentMotorStrength(
			String vehicleComponentMotorStrength) {
		VehicleComponentMotorStrength = vehicleComponentMotorStrength;
	}

	public String getVehicleComponentSensorRadius() {
		return VehicleComponentSensorRadius;
	}

	public void setVehicleComponentSensorRadius(String vehicleComponentSensorRadius) {
		VehicleComponentSensorRadius = vehicleComponentSensorRadius;
	}

	public String getVehicleComponentPosition() {
		return VehicleComponentPosition;
	}

	public void setVehicleComponentPosition(String vehicleComponentPosition) {
		VehicleComponentPosition = vehicleComponentPosition;
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
	 * Add a vehicle component temperament attribute(Motor | Sensor | Memory | Battery) to the XML document being created
	 * @param temperament One of (Motor | Sensor | Memory | Battery)
	 */
	public void addVehicleComponentType(String type){
		writeXMLEntry("type", type, xmldoc);
	}
	/**
	 * Add a vehicle component motor strength attribute (0.0 - 100.0) to the XML document being created
	 * @param temperament One of (0.0 - 100.0)
	 */
	public void addMotorStrength(String strength){
		writeXMLEntry("motorStrength", strength, xmldoc);
	}
	/**
	 * Add a vehicle component battery capacity attribute (0.0 - 100.0) to the XML document being created
	 * @param temperament One of (0.0 - 100.0)
	 */
	public void addBatteryCapacity(String capacity){
		writeXMLEntry("batteryCapacity", capacity, xmldoc);
	}
	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addSensorRadius(String radius){
		writeXMLEntry("sensorRadius", radius, xmldoc);
	}
	/**
	 * Add a vehicle component position attribute 
	 * (top-left | top-right | left | right | bottom-left 
                 | bottom-right)
 to the XML document being created
	 * @param temperament One of (top-left | top-right | left | right | bottom-left | bottom-right)
	 */
	public void addPosition(String pos){
		writeXMLEntry("position", pos, xmldoc);
	}
	/**
	 * Write out the current VehicleComponent to a file specified by the filename attribute
	 */
	/* Taken out for now, probably not going to be needed
	public void serialiseXMLDoc(){
		try{
			if() //TODO write attributes from object to XML
			xmldoc.appendChild(root); //finalise the XML document
			FileOutputStream fos = new FileOutputStream(filename);
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
	 */
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
		if(this.VehicleComponentPosition != null){
			this.addPosition(VehicleComponentPosition);
		}
		if(this.VehicleComponentBatteryCapacity != null){
			this.addBatteryCapacity(VehicleComponentBatteryCapacity);
		}
		if(this.VehicleComponentMotorStrength != null){
			this.addMotorStrength(VehicleComponentMotorStrength);
		}
		if(this.VehicleComponentSensorRadius != null){
			this.addSensorRadius(VehicleComponentSensorRadius);
		}
		xmldoc.appendChild(root);
	}

	/**
	 * Get the root of this vehicle component, call this method to get the component for inclusion
	 * in a Vehicle XML document 
	 * @return The root element of this VehicleComponent
	 */
	public Element getRootElement(){
		return root;
	}

}
