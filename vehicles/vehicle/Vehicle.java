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

public class Vehicle{
	protected String xmlLocation; //location of the XML file representing this vehicle
	/*Attributes of a vehicle*/
	protected String vehicleName = null; //the name of this vehicle
	protected Vector <VehicleComponent> components; //components of the vehicle 
	protected double max_battery_capacity = 100; //maximum battery this vehicle can have
	protected double curr_battery_capacity = 100;//current battery capacity
	protected double motorStrength = 0.0;
	protected double aggression = 0.0;
	protected Colour colour = new Colour();

	
	public double getAggression() {
		return aggression;
	}

	public void setAggression(double aggression) {
		this.aggression = aggression;
	}
	

	public double getMotorStrength() {
		return motorStrength;
	}

	public void setMotorStrength(double motorStrength) {
		this.motorStrength = motorStrength;
	}

	public double getMax_battery_capacity() {
		return max_battery_capacity;
	}

	public void setMax_battery_capacity(double max_battery_capacity) {
		this.max_battery_capacity = max_battery_capacity;
	}

	public double getCurr_battery_capacity() {
		return curr_battery_capacity;
	}

	public void setCurr_battery_capacity(double curr_battery_capacity) {
		this.curr_battery_capacity = curr_battery_capacity;
	}
	public void setColour(int r, int b, int g){
		this.colour.setRed(r);
		this.colour.setBlue(b);
		this.colour.setGreen(g);
	}
	/**
	 * Get the fitness of this vehicle, defined by it's current battery strength divided by it's maximum capacity
	 * plus 10 percent of the maximum capacity
	 * @return curr_battery_capacity/max_battery_capacity
	 */
	public double getFitness(){
		return (this.curr_battery_capacity/this.max_battery_capacity) + (this.max_battery_capacity * 0.10);
	}
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
			processVehicleComponents(dom.getElementsByTagName("VehicleComponent"));

			NodeList name = dom.getElementsByTagName("name");
			for(int i = 0;i<name.getLength();i++){
				if(name.item(i).getParentNode().getNodeName().equals("Vehicle")){
					this.setVehicleName(name.item(i).getChildNodes().item(0).getNodeValue());
				}
			}

			NodeList maxBattery = dom.getElementsByTagName("max_battery_capacity");
			this.setMax_battery_capacity(Double.parseDouble(maxBattery.item(0).getChildNodes().item(0).getNodeValue()));

			NodeList currBattery = dom.getElementsByTagName("curr_battery_capacity");
			this.setCurr_battery_capacity(Double.parseDouble(currBattery.item(0).getChildNodes().item(0).getNodeValue()));
			
			NodeList motorStr = dom.getElementsByTagName("motorStrength");
			this.setMotorStrength(Double.parseDouble(motorStr.item(0).getChildNodes().item(0).getNodeValue()));
			
			NodeList aggr = dom.getElementsByTagName("aggression");
			this.setAggression(Double.parseDouble(aggr.item(0).getChildNodes().item(0).getNodeValue()));
			
			NodeList red = dom.getElementsByTagName("red");
			this.colour.setRed(Integer.parseInt(red.item(0).getChildNodes().item(0).getNodeValue()));
			
			NodeList blue = dom.getElementsByTagName("blue");
			this.colour.setBlue(Integer.parseInt(blue.item(0).getChildNodes().item(0).getNodeValue()));
			
			NodeList green = dom.getElementsByTagName("green");
			this.colour.setGreen(Integer.parseInt(green.item(0).getChildNodes().item(0).getNodeValue()));
			
			//Node root = dom.getDocumentElement(); //get the root element from the document
			//handleNode(root); //recursive function to handle the nodes

		}catch(Exception e){
			//e.printStackTrace();			
			System.err.println("An error occurred while creating a vehicle from the file '" +
					filename + "'. Please check that this file exists.");
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
				NodeList children = node.getChildNodes();
				for(int j = 0; j < children.getLength(); j++){ 

					//System.out.println(children.item(j).getNodeName() );
					NodeList values = children.item(j).getChildNodes();
					for(int k=0;k<values.getLength();k++){ //attribute by attribute
						/*PROCESS AN ATTRIBUTE OF AN ELEMENT OF A VEHICLECOMPONENT */
						String attributeType = values.item(k).getParentNode().getNodeName();
						if(attributeType.contains("name")){
							//							System.out.println("This is a name node");
							//						System.out.println(values.item(k).getNodeValue());
							vc.setVehicleComponentName(values.item(k).getNodeValue());
						}else{
							if(attributeType.contains("type")){
								//System.out.println("This is a type node");
								//System.out.println(values.item(k).getNodeValue());
								vc.setVehicleComponentType(values.item(k).getNodeValue());
							}else{
								if(attributeType.contains("LeftSensorRadius")){
									vc.setVehicleComponentLeftSensorRadius(values.item(k).getNodeValue());
								}else{
									if(attributeType.contains("LeftSensorLight")){
										vc.setVehicleComponentLeftSensorLight(values.item(k).getNodeValue());
									}else{
										if(attributeType.contains("LeftSensorHeat")){
											vc.setVehicleComponentLeftSensorHeat(values.item(k).getNodeValue());
										}else{
											if(attributeType.contains("LeftSensorPower")){
												vc.setVehicleComponentLeftSensorPower(values.item(k).getNodeValue());
											}else{
												if(attributeType.contains("LeftSensorWater")){
													vc.setVehicleComponentLeftSensorWater(values.item(k).getNodeValue());
												}else{
													if(attributeType.contains("RightSensorRadius")){
														vc.setVehicleComponentRightSensorRadius(values.item(k).getNodeValue());
													}else{
														if(attributeType.contains("RightSensorLight")){
															vc.setVehicleComponentRightSensorLight(values.item(k).getNodeValue());
														}else{
															if(attributeType.contains("RightSensorHeat")){
																vc.setVehicleComponentRightSensorHeat(values.item(k).getNodeValue());
															}else{
																if(attributeType.contains("RightSensorPower")){
																	vc.setVehicleComponentRightSensorPower(values.item(k).getNodeValue());
																}else{
																	if(attributeType.contains("RightSensorWater")){
																		vc.setVehicleComponentRightSensorWater(values.item(k).getNodeValue());

																	}
																}
															}
														}
													}
												}
											}
										}
									}		

								}

							}
						}


					}//end values for loop

				}//end children for loop
				//System.out.println("END OF COMPONENT");
				components.add(vc);
			}
		}
	}



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
