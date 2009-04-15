package vehicles.vehicle;

import vehicles.*;
import java.io.*;
import java.util.Iterator;
import java.util.Vector;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.Locator;
import org.xml.sax.helpers.*;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xerces.utils.QName;
import org.apache.xerces.readers.MIME2Java;
import org.apache.xml.serialize.*;
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
	 * Add a vehicle temperament attribute(Aggressive | Timid | None) to the XML document being created
	 * @param temperament One of (Aggressive | Timid | None)
	 */
	public void addVehicleTemperament(String temperament){
		writeXMLEntry("temperament", temperament, xmldoc);
	}
	/**
	 * Add a vehicle component XML entry into this Vehicle's document
	 * @param vc The vehicle component to add
	 */
	public void addVehicleComponent(VehicleComponent vc){
		root.appendChild(xmldoc.adoptNode(vc.getRootElement().cloneNode(true)));
		System.out.println("Append component!");
		
	}
	/**
	 * Write out the current Vehicle to a file specified by the filename attribute. This method
	 * will save the vehicle object as it is to disk, so make sure it's only called when we're finished
	 * with it
	 */
	public void saveVehicle(){
		try{
			/*Form the XML document by saving the various attributes*/
			if(this.vehicleName != null){ 
				addVehicleName(vehicleName);
			}
			if(this.VehicleTemperament != null){
				addVehicleTemperament(VehicleTemperament);
			}
			if(this.components != null){
				System.out.println(components.capacity());
				Iterator it = components.iterator();
				int count = 0;
				while(it.hasNext()){
					System.out.println("iteration :"+count);
					this.addVehicleComponent((VehicleComponent)it.next());
					count++;
				}
			}
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
