package vehicles.vehicle;

import java.util.Vector;
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
