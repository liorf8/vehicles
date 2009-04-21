package vehicles.util;

import vehicles.vehicle.*;

//WARNING- hard-coded values
public class FileTest {
	public static void main(String args[]){
		/*CREATE A NEW VEHICLE*/
		EditorVehicle veh = new EditorVehicle("/home/karl/XMLVehicles/angry.xml"); 
		veh.setVehicleName("Angry Vehicle");
		veh.setCurr_battery_capacity(65);
		veh.setMax_battery_capacity(100);
		veh.setVehicleTemperament("aggresive");

		VehicleComponent vc = new VehicleComponent("fastmotor.xml"); //new object
			vc.setVehicleComponentName("Fast Motor");//set object attributes
			vc.setVehicleComponentType("motor");
			vc.setVehicleComponentPosition("left");
			vc.setVehicleComponentMotorStrength(Double.toString(86.05));
			vc.toInternalXML(); //generate xml in ram
	
		veh.addVehicleComponent(vc); //move xml in ram from component into vehicle
		veh.saveVehicle();
		/*GET LIST OF ALL VEHICLES IN FOLDER*/
		Vehicle[] array = UtilMethods.getVehiclesFromFolder("/home/karl/XMLVehicles");
		for(int i = 0; i<array.length;i++){
			System.out.println(array[i].getVehicleName()+"\n");
			System.out.println("\t"+array[i].getVehicleTemperament());
		}
	}

}
