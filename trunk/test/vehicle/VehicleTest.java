package test.vehicle;
import vehicles.vehicle.*;


public class VehicleTest {

	/**A main class for testing of various elements in the program.
	 * Please DON'T upload binary files produced by compiling this into the repo.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		/*Testing creating and editing a vehicle XML entry*/

		EditorVehicle v = new EditorVehicle("xml/vehicles/hungry.veh");
		v.setName("Hungy Vehicle"); //set object attributes
		v.setAuthor("Some guy");
		v.setDescription("This is a hungry little vehicle ");
		v.setCurr_battery_capacity(46);
		v.setMotorStrength(67);
		v.setAggression(55);
		v.setColour(102, 203, 150);

		v.setLeftSensorHeat(12);
		v.setLeftSensorLight(35);
		v.setLeftSensorPower(42);
		v.setLeftSensorWater(86);

		v.setRightSensorHeat(98);
		v.setRightSensorLight(67);
		v.setRightSensorPower(84);
		v.setRightSensorWater(56);

		v.saveVehicle(); //convert object and its attributes into XML
		System.out.println("Testing getting filename");
		System.out.println("Filepath: " + v.getXmlLocation());
		System.out.println("Filename: " + v.getFileName());

		/*Now the vehicle is saved as an xml doc, lets try load that xml back into an object */
		EditorVehicle veh = new EditorVehicle("xml/vehicles/hungry.veh",true); //constructor loads xml into an object
		veh.setXmlLocation("xml/vehicles/hungryduplicate.xml");//where to save the new xml, should be same as hungry.xml
		veh.saveVehicle(); //write the xml
		//Now we have created an object, written to xml, created an object from that xml, and wrote that
		//  to produce the same xml



	}
}
