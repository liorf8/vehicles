package vehicles.util;

import java.io.File;
import java.util.Vector;
import vehicles.vehicle.*;

public class UtilMethods {

	/**
	 * Get a list of all Vehicles in a particular folder
	 * @param folderName The folder to generate the list from
	 * @return A Vehicle[] of the Vehicles in folderName
	 */
	public static EditorVehicle[] getVehiclesFromFolder(String folderName){
	
		Vector<EditorVehicle> v = new Vector<EditorVehicle>();
		File[] files = new File(folderName).listFiles(); //get list of files
		EditorVehicle[] list = new EditorVehicle[files.length];
		for(int i =0; i < files.length;i++){
			if(files[i].isFile()){ // if current entry is a file
				v.add(new EditorVehicle(files[i].toString())); //create an object from the XML in this file
			}
		}
		return v.toArray(list); //return an array
	}
}
