package vehicles.vehicle;

import java.io.FileOutputStream;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
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
	Document  xmldoc; //the XML document we are creating, stored as an object in memory
	Element root;//the root element of the document
	String filename;// filename to associate the XML document with
	
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
		Element nameElement = xmldoc.createElement("name");
        Text nameText = xmldoc.createTextNode(name);
        nameElement.appendChild(nameText);//add in the text to the element
        root.appendChild(nameElement);//and add this new element to the document
	} 
	/**
	 * Add a vehicle component temperament attribute(Motor | Sensor | Memory | Battery) to the XML document being created
	 * @param temperament One of (Motor | Sensor | Memory | Battery)
	 */
	public void addVehicleComponentType(String type){
		Element typeElement = xmldoc.createElement("type");
        Text typeText = xmldoc.createTextNode(type);
        typeElement.appendChild(typeText);//add in the text to the element
        root.appendChild(typeElement); //and add this new element to the document
	}
	/**
	 * Add a vehicle component motor strength attribute (0.0 - 100.0) to the XML document being created
	 * @param temperament One of (0.0 - 100.0)
	 */
	public void addMotorStrength(String strength){
		Element msElement = xmldoc.createElement("motorStrength");
        Text msText = xmldoc.createTextNode(strength);
        msElement.appendChild(msText);//add in the text to the element
        root.appendChild(msElement); //and add this new element to the document
	}
	/**
	 * Add a vehicle component battery capacity attribute (0.0 - 100.0) to the XML document being created
	 * @param temperament One of (0.0 - 100.0)
	 */
	public void addBatteryCapacity(String capacity){
		Element bcElement = xmldoc.createElement("batteryCapacity");
        Text bcText = xmldoc.createTextNode(capacity);
        bcElement.appendChild(bcText);//add in the text to the element
        root.appendChild(bcElement); //and add this new element to the document
	}
	/**
	 * Add a vehicle component sensor radius attribute (0 - 100) to the XML document being created
	 * @param temperament One of (0 - 100)
	 */
	public void addSensorRadius(String radius){
		Element srElement = xmldoc.createElement("sensorRadius");
        Text srText = xmldoc.createTextNode(radius);
        srElement.appendChild(srText);//add in the text to the element
        root.appendChild(srElement); //and add this new element to the document
	}
	/**
	 * Add a vehicle component position attribute 
	 * (top-left | top-right | left | right | bottom-left 
                 | bottom-right)
 to the XML document being created
	 * @param temperament One of (top-left | top-right | left | right | bottom-left | bottom-right)
	 */
	public void addPosition(String pos){
		Element srElement = xmldoc.createElement("position");
        Text srText = xmldoc.createTextNode(pos);
        srElement.appendChild(srText);//add in the text to the element
        root.appendChild(srElement); //and add this new element to the document
	}
	/**
	 * Write out the current VehicleComponent to a file specified by the filename attribute
	 */
	public void serialiseXMLDoc(){
		try{
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
	/**
	 * Get the root of this vehicle component, call this method to get the component for inclusion
	 * in a Vehicle XML document 
	 * @return The root element of this VehicleComponent
	 */
	public Element getRootElement(){
		return root;
	}

}
