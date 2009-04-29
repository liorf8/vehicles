package vehicles.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import vehicles.vehicle.*;
import vehicles.environment.Environment;
import vehicles.environment.EnvironmentElement;
import vehicles.environment.Point;
import vehicles.genetics.Genetics;
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

	/**
	 * Delete a directoy with files in it
	 * @param dir The directory to delete
	 * @return boolean stating whether deletion occurred or not
	 */
	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			boolean success;
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				System.out.println("Deleting " + children[i]);
				success = deleteDir(new File(dir, children[i]));
				if (!success) {
					System.err.println("Deletion of " + children[i].toString() + " failed!");
					return false;
				}
			}
		}
		// The directory is now empty so delete it
		return dir.delete();
	} 

	public static void deleteFile(String filepath){
		System.out.println("Attempting to delete " + filepath);
		boolean success = (new File(filepath)).delete();
		if (!success) {
			System.err.println("Deletion of " + filepath + " failed!");
		}
		System.out.println("Deletion successful!");

	}
	/**
	 * A void method to delete all xml in the xml folders environment, simulation and vehicle
	 *
	 */
	public static void resetXML(){
		boolean deleteXML = deleteDir(new File("xml" + File.separator));
		if(!deleteXML){
			System.err.println("Some files could not be deleted!");
		}
		File f;
		f = new File("xml"+  File.separator);
		if(!f.exists()){
			f.mkdirs();
		}
		f = new File("xml" +  File.separator + "environments" +  File.separator);
		if(!f.exists()){
			f.mkdirs();
		}
		f = new File("xml" +  File.separator + "vehicles" +  File.separator);
		if(!f.exists()){
			f.mkdirs();
		}
		f = new File("xml" +  File.separator + "simulations" +  File.separator);
		if(!f.exists()){
			f.mkdirs();
		}

		//Re create them

		//Create a light source, populate it's attributes, and write to file
		EnvironmentElement ls = new EnvironmentElement();	
		ls.setType(EnvironmentElement.LightSource);//TODO put this in object constructor
		ls.setPosition(new Point(100,100));		
		ls.setRadius(150);
		ls.setStrength(10);

		//Create a heat source, populate it's attributes, and write to file
		EnvironmentElement hs = new EnvironmentElement();
		hs.setType(EnvironmentElement.HeatSource); 
		hs.setPosition(new Point(700,500));
		hs.setRadius(150);
		hs.setStrength(10);

		EnvironmentElement ws = new EnvironmentElement();
		ws.setType(EnvironmentElement.WaterSource);
		ws.setPosition(new Point(700,100));
		ws.setRadius(150);
		ws.setStrength(10);

		EnvironmentElement ps = new EnvironmentElement();
		ps.setType(EnvironmentElement.PowerSource);
		ps.setPosition(new Point(100,500));
		ps.setRadius(150);
		ps.setStrength(10);

		// Create an environment and write to xml

		String path = "xml" + File.separator + "environments" + File.separator + "default_environment1.env";
		Environment e = new Environment("default_environment1", path);
		e.setWidth(800);
		e.setHeight(600);
		e.setAuthor("Shaun");
		e.setDescription("Default Environment 1");
		e.addElement(ls);
		e.addElement(hs);
		e.addElement(ws);
		e.addElement(ps);
		e.saveEnvironment();
		System.out.println("Creating " + path);

		path = "xml" + File.separator + "environments" + File.separator + "default_environment3.env";
		e = new Environment("default_environment3", path);
		e.setWidth(800);
		e.setHeight(600);
		e.setAuthor("Karl");
		e.setDescription("Default Environment 3");

		e.addElement(ps);
		e.saveEnvironment();
		System.out.println("Creating " + path);

		path = "xml" + File.separator + "environments" + File.separator + "default_environment2.env";
		Environment env = new Environment(e.getFileLocation()); 
		env.setXMLLocation(path);
		env.setDescription("Default Environment 2");
		ps = new EnvironmentElement();
		ps.setType(EnvironmentElement.PowerSource);
		ps.setPosition(new Point(10, 5));
		ps.setRadius(10);
		ps.setStrength(100);
		env.addElement(ps);
		env.saveEnvironment();
		System.out.println("Creating " + path);


		//Testing creating and editing a vehicle XML entry

		path = "xml" + File.separator + "vehicles" + File.separator + "default_vehicle1.veh";

		EditorVehicle v = new EditorVehicle(path);
		v.setName("Default Vehicle 1"); //set object attributes
		v.setAuthor("Shaun");
		v.setDescription("Default Vehicle 1");

		v.setMotorStrength(100);
		v.setAggression(0);

		v.setMaxBatteryCapacity(50);
		v.setCurrentBatteryCapacity(50);

		v.setMaxMem(30);
		v.setLearningRate(5);

		v.setColour(50, 100, 150);

		v.setLeftSensorHeat(20);
		v.setLeftSensorLight(30);
		v.setLeftSensorPower(40);
		v.setLeftSensorWater(50);

		v.setRightSensorHeat(-10);
		v.setRightSensorLight(-20);
		v.setRightSensorPower(-30);
		v.setRightSensorWater(-40);

		v.saveVehicle(); //convert object and its attributes into XML
		System.out.println("Creating " + path);


		path = "xml" + File.separator + "vehicles" + File.separator + "default_vehicle2.veh";
		v = new EditorVehicle(path);
		v.setName("Default Vehicle 2"); //set object attributes
		v.setAuthor("Shaun");
		v.setDescription("Default Vehicle 2");

		v.setMotorStrength(10);
		v.setAggression(100);

		v.setMaxBatteryCapacity(100);
		v.setCurrentBatteryCapacity(80);

		v.setMaxMem(1);
		v.setLearningRate(1);

		v.setColour(255, 0, 255);

		v.setLeftSensorHeat(0);
		v.setLeftSensorLight(10);
		v.setLeftSensorPower(20);
		v.setLeftSensorWater(50);

		v.setRightSensorHeat(-50);
		v.setRightSensorLight(20);
		v.setRightSensorPower(40);
		v.setRightSensorWater(-40);

		v.saveVehicle(); //convert object and its attributes into XML
		System.out.println("Creating " + path);

		//Re create a simlation
		EditorSimulation es = new EditorSimulation();
		path = "xml" + File.separator + "simulations" + File.separator + "default_simulation1.sim";
		es.setXmlLocation(path);
		es.setName("Default Simulation 1");
		es.setAuthor("Shaun");
		es.setDescription("Default Simulation 1");
		es.setEvolution(true);
		es.setReproductionMethod(2);
		es.setGeneticSelectionMethod(Genetics.GetBestSelection);
		es.setPerishableVehicles(false);
		es.addVehicle("xml" + File.separator + "vehicles" + File.separator + "default_vehicle1.veh");
		es.addVehicle("xml" + File.separator + "vehicles" + File.separator + "default_vehicle1.veh");
		//es.addVehicle("xml" + File.separator + "vehicles" + File.separator + "default_vehicle2.veh");
		es.setEnvironment("xml" + File.separator  + "environments" + File.separator + "default_environment1.env");
		es.saveSimulation();
		System.out.println("Creating " + path);

		es = new EditorSimulation();
		path = "xml" + File.separator + "simulations" + File.separator + "default_simulation2.sim";
		es.setXmlLocation(path);
		es.setName("Default Simulation 2");
		es.setAuthor("Shaun");
		es.setDescription("Default Simulation 2");
		es.setEvolution(true);
		es.setReproductionMethod(2); 
		es.setGeneticSelectionMethod(Genetics.NoSelection);
		es.setPerishableVehicles(false);
		es.addVehicle("xml" + File.separator + "vehicles" + File.separator + "default_vehicle1.veh");
		es.addVehicle("xml" + File.separator + "vehicles" + File.separator + "default_vehicle1.veh");
		//es.addVehicle("xml" + File.separator + "vehicles" + File.separator + "default_vehicle2.veh");
		es.setEnvironment("xml" + File.separator  + "environments" + File.separator + "default_environment2.env");
		es.saveSimulation();
		System.out.println("Creating "+path);
	}
	
	public static String formatFilePath(String filePath){
		String[] parts;
		String new_filePath = "";
		int len;
		if(filePath.contains("/")){
			parts = filePath.split("/");
		}
		else if(filePath.contains("\\")){
			parts = filePath.split("\\\\");
		}
		else{
			return "";
		}
		len = parts.length;
		for(int i = 0; i < len - 1; i++){
			new_filePath = new_filePath.concat(parts[i]).concat(File.separator);
		}
		new_filePath = new_filePath.concat(parts[len - 1]);
		System.out.println("FilePath: " + filePath);
		return new_filePath;
	}



}
