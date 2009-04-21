package vehicles.util;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import org.apache.xerces.parsers.DOMParser;

import vehicles.vehicle.Vehicle;

public class UtilMethods {

	
	/**
	 * Get a list of all Vehicles in a particular folder
	 * @param folderName The folder to generate the list from
	 * @return A Vehicle[] of the Vehicles in folderName
	 */
	public static Vehicle[] getVehiclesFromFolder(String folderName){
	
		Vector<Vehicle> v = new Vector<Vehicle>();
		File[] files = new File(folderName).listFiles(); //get list of files
		Vehicle[] list = new Vehicle[files.length];
		for(int i =0; i < files.length;i++){
			if(files[i].isFile()){ // if current entry is a file
				v.add(new Vehicle(files[i].toString())); //create an object from the XML in this file
			}
		}
		return v.toArray(list); //return an array
	}
	
}
