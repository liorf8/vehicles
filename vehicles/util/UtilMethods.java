package vehicles.util;

import java.io.File;
import java.util.Vector;
import vehicles.vehicle.*;
import vehicles.environment.Environment;
import vehicles.simulation.*;

public class UtilMethods {

	/**
	 * Get a list of all Vehicles in a particular folder
	 * @param folderName The folder to generate the list from
	 * @return A Vehicle[] of the Vehicles in folderName
	 */
	public static EditorVehicle[] getVehiclesFromFolder(String folderName){
	
		Vector<EditorVehicle> v = new Vector<EditorVehicle>();
		File[] files = new File(folderName).listFiles(); //get list of files
		EditorVehicle[] list = new EditorVehicle[files.length - 1];
		for(int i =0; i < files.length;i++){
			if(files[i].isFile() && files[i].toString().endsWith(".veh")){ // if current entry is a vehicle file
				//System.out.println(files[i].toString());
				v.add(new EditorVehicle(files[i].toString(),true)); //create an object from the XML in this file
			}
		}
		return (EditorVehicle[])v.toArray();
	}
	
	/**
	 * Get a list of simulations from a particular folder
	 * @param folderPath The path to generate the list from
	 * @return A Simulation Array of simulations at the folder path
	 */
	public static Simulation[] getSimulationsFromFolder(String folderPath){
		Vector<Simulation> s = new Vector<Simulation>();
		File[] files = new File(folderPath).listFiles();
		int len = files.length;
		Simulation[] list = new Simulation[len - 1];
		for(int i = 0; i < len; i++){
			if(files[i].isFile() && files[i].toString().endsWith(".sim")){
				s.add(new Simulation(files[i].toString()));
			}
		}
		return (Simulation[])s.toArray();
	}
	
	/**
	 * Get a list of environments from a particular folder
	 * @param folderPath The path to generate the list from
	 * @return An Environment Array of environments at the folder path
	 */
	public static Environment[] getEnvironmentsFromFolder(String folderPath){
		Vector<Environment> e = new Vector<Environment>();
		File[] files = new File(folderPath).listFiles();
		int len = files.length;
		Environment[] list = new Environment[len - 1];
		for(int i = 0; i < len; i++){
			if(files[i].isFile() && files[i].toString().endsWith(".env")){
				e.add(new Environment(files[i].toString()));
			}
		}
		return (Environment[])e.toArray();
	}
	
	/**
	 * Removes all non alphanumeric characters in a string
	 * @param s The string to format
	 * @return A formatted version of the string passed
	 */
	public static String formatString(String s){
        s = s.toLowerCase();
        s = s.replaceAll("[^a-z0-9]", "");
		return s;
	}
}
