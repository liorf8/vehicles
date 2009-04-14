package vehicles.vehicle;

/**
 * 
 *
 * A superclass for vehicle, which can be sub-classed for use in the vehicle editors 
 * or the simulation, while maintaining some common attributes
 * @author Karl 
 */
public class Vehicle {
	private String xmlLocation; //location of the XML file representing this vehicle
	private String name; //This vehicle's name
	
	/**
	 * 
	 * @return Location of this object's xml file
	 */
	public String getXmlLocation() {
		return xmlLocation; 
	}
	public void setXmlLocation(String xmlLocation) {
		this.xmlLocation = xmlLocation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
