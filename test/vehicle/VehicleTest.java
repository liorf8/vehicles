package test.vehicle;
import vehicles.vehicle.*;

public class VehicleTest {

/**A main class for testing of various elements in the program.
	 * Please DON'T upload binary files produced by compiling this into the repo.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		/*Testing creating and editing a vehicle XML entry*/
			EditorVehicle v = new EditorVehicle("xml/vehicles/hungry.xml");
			v.setVehicleName("Hungy Vehicle"); //set object attributes
			v.setAuthor("Some guy");
			v.setDescription("This is a hungry little vehicle ");
			v.setCurr_battery_capacity(46);
			v.setMotorStrength(67);
			v.setAggression(55);
			v.setColour(102, 203, 150);

				
				VehicleComponent vc = new VehicleComponent("weaksensor.xml"); //new object
					vc.setVehicleComponentName("Left Sensor");//set object attributes
					vc.setVehicleComponentType("Left");
					vc.setVehicleComponentLeftSensorRadius(Integer.toString(22));
					vc.setVehicleComponentLeftSensorLight(Integer.toString(45));
					vc.setVehicleComponentLeftSensorHeat(Integer.toString(34));
					vc.setVehicleComponentLeftSensorPower(Integer.toString(67));
					vc.setVehicleComponentLeftSensorWater(Integer.toString(89));

					v.addVehicleComponent(vc); //move xml in ram from component into vehicle

				vc = new VehicleComponent("weaksensor.xml"); //new object
					vc.setVehicleComponentName("Right Sensor");//set object attributes
					vc.setVehicleComponentType("Right");
					vc.setVehicleComponentRightSensorRadius(Integer.toString(34));
					vc.setVehicleComponentRightSensorLight(Integer.toString(45));
					vc.setVehicleComponentRightSensorHeat(Integer.toString(12));
					vc.setVehicleComponentRightSensorPower(Integer.toString(9));
					vc.setVehicleComponentRightSensorWater(Integer.toString(-8));

					v.addVehicleComponent(vc); //move xml in ram from component into vehicle

			v.saveVehicle(); //convert object and its attributes into XML

			/*Now the vehicle is saved as an xml doc, lets try load that xml back into an object */
			EditorVehicle veh = new EditorVehicle("xml/vehicles/hungry.xml",true); //constructor loads xml into an object
			veh.setXmlLocation("xml/vehicles/hungryduplicate.xml");//where to save the new xml, should be same as hungry.xml
			veh.saveVehicle(); //write the xml
			//Now we have created an object, written to xml, created an object from that xml, and wrote that
			//  to produce the same xml

			}

}
