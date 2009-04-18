package test.vehicle;
import vehicles.vehicle.*;

public class VehicleTests {

	/**A main class for testing of various elements in the program.
	 * Please DON'T upload binary files produced by compiling this into the repo.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		/*Testing creating and editing a vehicle XML entry*/
			EditorVehicle v = new EditorVehicle("src/test/XML/hungry.xml");
			v.setVehicleName("Hungy Vehicle"); //set object attributes
			v.setVehicleTemperament("Timid"); //set object attributes 
			v.setCurr_battery_capacity(46);
			
				VehicleComponent vc = new VehicleComponent("fastmotor.xml"); //new object
					vc.setVehicleComponentName("Fast Motor");//set object attributes
					vc.setVehicleComponentType("motor");
					vc.setVehicleComponentPosition("left");
					vc.setVehicleComponentMotorStrength(Double.toString(86.05));
					vc.toInternalXML(); //generate xml in ram
			
				v.addVehicleComponent(vc); //move xml in ram from component into vehicle
				
				vc = new VehicleComponent("weaksensor.xml"); //new object
					vc.setVehicleComponentName("Weak Sensor");//set object attributes
					vc.setVehicleComponentType("sensor");
					vc.setVehicleComponentPosition("top-right");
					vc.setVehicleComponentSensorRadius(Integer.toString(22));
					vc.toInternalXML(); //generate xml in ram
		
					v.addVehicleComponent(vc); //move xml in ram from component into vehicle
			
			v.saveVehicle(); //convert object and its attributes into XML
			
			/*Now the vehicle is saved as an xml doc, lets try load that xml back into an object */
			EditorVehicle veh = new EditorVehicle("src/test/XML/hungry.xml",true); //constructor loads xml into an object
			veh.setXmlLocation("src/test/XML/hungryduplicate.xml");//where to save the new xml, should be same as hungry.xml
			veh.saveVehicle(); //write the xml
			//Now we have created an object, written to xml, created an object from that xml, and wrote that
			//  to produce the same xml
		
			}

}
