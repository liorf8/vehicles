
public class Test {

	/**A main class for testing of various elements in the program.
	 * Please DON'T upload binary files produced by compiling this into the repo.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		/*Testing creating and editing a vehicle XML entry*/
			EditorVehicle v = new EditorVehicle("hungry.xml");
			v.addVehicleName("Hungry Vehicle"); // add a name
			v.addVehicleTemperament("Timid"); //add a temperament
			/*Add a fast motor component*/
			VehicleComponent vc = new VehicleComponent("fastmotor.xml");
				vc.addVehicleComponentName("Fast Motor");
				vc.addVehicleComponentType("Motor");
				vc.addMotorStrength(new Double(86.00).toString());
				vc.addPosition("left");
				vc.serialiseXMLDoc();
			v.addVehicleComponent(vc);//perform the add
			/*Add a strong sensor component*/
			vc = new VehicleComponent("strongsensor.xml");
				vc.addVehicleComponentName("Strong Sensor");
				vc.addVehicleComponentType("Sensor");
				vc.addSensorRadius(Integer.toString(57));
				vc.addPosition("top-left");
				vc.serialiseXMLDoc();
			v.addVehicleComponent(vc);
			/*Add a weak sensor component*/
			vc = new VehicleComponent("weaksensor.xml");
				vc.addVehicleComponentName("Weak Sensor");
				vc.addVehicleComponentType("Sensor");
				vc.addSensorRadius(Integer.toString(22));
				vc.addPosition("top-right");
				vc.serialiseXMLDoc();
			v.addVehicleComponent(vc);	
			/*Generate the xml itself*/
			v.serialiseXMLDoc();
	}

}
