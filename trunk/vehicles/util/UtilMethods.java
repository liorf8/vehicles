package vehicles.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		int count = 0;
		for(int i =0; i < files.length;i++){
			if(files[i].isFile() && files[i].toString().endsWith(".veh")){ // if current entry is a vehicle file
				//System.out.println(files[i].toString());
				v.add(new EditorVehicle(files[i].toString(),true)); //create an object from the XML in this file
				count++;
			}
		}
		EditorVehicle[] list = new EditorVehicle[count];
		return v.toArray(list);
	}
	
	/**
	 * Get a list of simulations from a particular folder
	 * @param folderPath The path to generate the list from
	 * @return A Simulation Array of simulations at the folder path
	 */
	public static EditorSimulation[] getSimulationsFromFolder(String folderPath){
		Vector<EditorSimulation> s = new Vector<EditorSimulation>();
		File[] files = new File(folderPath).listFiles();
		int len = files.length;
		int count = 0;		
		for(int i = 0; i < len; i++){
			if(files[i].isFile() && files[i].toString().endsWith(".sim")){
				s.add(new EditorSimulation(files[i].toString()));
				count++;
			}
		}
		EditorSimulation[] list = new EditorSimulation[count];
		return s.toArray(list);
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
		int count = 0;
		for(int i = 0; i < len; i++){
			if(files[i].isFile() && files[i].toString().endsWith(".env")){
				e.add(new Environment(files[i].toString()));
			}
		}

		Environment[] list = new Environment[count];
		return e.toArray(list);
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
	
	public static String getTimeStamp(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
