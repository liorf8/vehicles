package vehicles.vehicle;

import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.xerces.parsers.DOMParser;

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
	protected Vector <VehicleComponent> components; //components of the vehicle 

	public Vehicle(){
		components = new Vector<VehicleComponent>();
	}

	public Vehicle(String filename){
		try{
			xmlLocation = filename;
			components = new Vector<VehicleComponent>();
			DOMParser p = new DOMParser();
			p.parse(xmlLocation); //get a parsed version of the file into memory
			Document dom = p.getDocument();
			processVehicleComponents( dom.getElementsByTagName("VehicleComponent"));
			
			NodeList name = dom.getElementsByTagName("name");
			for(int i = 0;i<name.getLength();i++){
				if(name.item(i).getParentNode().getNodeName().equals("Vehicle")){
					this.setVehicleName(name.item(i).getChildNodes().item(0).getNodeValue());
				}
			}
			
			NodeList temperament = dom.getElementsByTagName("temperament");
			this.setVehicleTemperament(temperament.item(0).getChildNodes().item(0).getNodeValue());
			
			//Node root = dom.getDocumentElement(); //get the root element from the document
			//handleNode(root); //recursive function to handle the nodes

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	/**
	 * Take a NodeList representing components of a vehicle and instansiate the vehicle object accordingly
	 * @param componentList A NodeList of vehicle components taken from the XML file
	 */
	private void processVehicleComponents(NodeList componentList){
		for(int i = 0; i < componentList.getLength(); i++){ //process component by component
			Node node = componentList.item(i); // get a single vehicle component
			if(node.getNodeType() == Node.ELEMENT_NODE){
				VehicleComponent vc = new VehicleComponent(); //set the attributes of this object as we go on
				System.out.println("START COMPONENT");
				NodeList children = node.getChildNodes();
				for(int j = 0; j < children.getLength(); j++){ 
					
					//System.out.println(children.item(j).getNodeName() );
					NodeList values = children.item(j).getChildNodes();
					for(int k=0;k<values.getLength();k++){ //attribute by attribute
						/*PROCESS AN ATTRIBUTE OF AN ELEMENT OF A VEHICLECOMPONENT */
						String attributeType = values.item(k).getParentNode().getNodeName();
						if(attributeType.contains("name")){
							System.out.println("This is a name node");
							System.out.println(values.item(k).getNodeValue());
							vc.setVehicleComponentName(values.item(k).getNodeValue());
							//TODO object
						}else{
							if(attributeType.contains("type")){
								System.out.println("This is a type node");
								System.out.println(values.item(k).getNodeValue());
								vc.setVehicleComponentType(values.item(k).getNodeValue());
								//TODO object
							}else{
								if(attributeType.contains("battery")){
									System.out.println("This is a batteryType node");
									System.out.println(values.item(k).getNodeValue());
									vc.setVehicleComponentBatteryCapacity(values.item(k).getNodeValue());
									//TODO object
								}else{
									if(attributeType.contains("motorStrength")){
										System.out.println("This is a motorStrength node");
										System.out.println(values.item(k).getNodeValue());
										vc.setVehicleComponentMotorStrength(values.item(k).getNodeValue());
										//TODO object
									}else{
										if(attributeType.contains("sensor")){
											System.out.println("This is a sensorRadius node");
											System.out.println(values.item(k).getNodeValue());
											vc.setVehicleComponentSensorRadius(values.item(k).getNodeValue());
											//TODO object
										}else{
											if(attributeType.contains("pos")){
												System.out.println("This is a position node");
												System.out.println(values.item(k).getNodeValue());
												vc.setVehicleComponentPosition(values.item(k).getNodeValue());
												//TODO object
											}else{
											}
										}
									}
								}

							}

						}
						
					}//end values for loop
					
				}//end children for loop
				System.out.println("END OF COMPONENT");
				vc.toInternalXML();				
				components.add(vc);
			}
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
