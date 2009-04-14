package vehicles.vehicle;

import java.io.PrintStream;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.apache.xerces.parsers.DOMParser;


import vehicles.*;
/**
 * 
 *
 * A superclass for vehicle, which can be sub-classed for use in the vehicle editors 
 * or the simulation, while maintaining some common attributes
 * @author Karl 
 */
public class Vehicle {
	protected String xmlLocation; //location of the XML file representing this vehicle
	/*Attributes of a vehicle*/
	protected String vehicleName = null; //the name of this vehicle
	protected String VehicleTemperament = null; //the vehicle's temperament
	protected Vector <VehicleComponent> components = null; //components of the vehicle 

	public Vehicle(){		
	}

	public Vehicle(String filename){
		try{
			xmlLocation = filename;
			DOMParser p = new DOMParser();
			p.parse(xmlLocation); //get a parsed version of the file into memory
			Document dom = p.getDocument();
			Node root = dom.getDocumentElement(); //get the root element from the document
			handleNode(root); //recursive function to handle the nodes
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	/**
	 * Take in a node from an XML document and use it to instansiate parts of the object- for 
	 * internal class use only by other methods
	 * @param node The node to handle
	 */
	private void handleNode(Node node){
		int type = node.getNodeType();
		//System.out.println(node.getNodeName() + " " + node.getNodeValue());
		switch (type) { //depending on the type of node, perform different actions
		case Node.ELEMENT_NODE: 
			System.out.println(node.getNodeName());
			NodeList children = node.getChildNodes();
			int len = children.getLength();
			for (int i=0; i<len; i++)
				handleNode(children.item(i));
			break;
		case Node.TEXT_NODE:
			System.out.println("Parent of parent = " +node.getParentNode().getParentNode().getNodeName());
			System.out.println("Parent = " + node.getParentNode().getNodeName())
;			System.out.println("\tChild= " + node.getNodeValue() );
		}
		
		
	}


	/*	
			DOMParser p = new DOMParser();
			p.parse(xmlLocation); //get a parsed version of the file into memory
			Document dom = p.getDocument();
			Node root = dom.getDocumentElement(); //get the root element from the document
			NodeList nodes = root.getChildNodes(); //get a list of the child nodes
			for (int i = 0; i < nodes.getLength(); i++) { //iterate over each of the children
				Node node = nodes.item(i); //get current node
				if(node.getNodeName().contains("name")){ //a name node

				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}
	 */



	public String getXmlLocation() {
		return xmlLocation;
	}
	public void setXmlLocation(String xmlLocation) {
		this.xmlLocation = xmlLocation;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleTemperament() {
		return VehicleTemperament;
	}
	public void setVehicleTemperament(String vehicleTemperament) {
		VehicleTemperament = vehicleTemperament;
	}
	public Vector<VehicleComponent> getComponents() {
		return components;
	}
	public void setComponents(Vector<VehicleComponent> vehicleComponent) {
		this.components = vehicleComponent;
	}
	public void addVehicleComponent(VehicleComponent vc){
		components.add(vc);
	}


}
