package vehicles.genetics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;

import vehicles.vehicle.MemoryUnit;
import vehicles.vehicle.Vehicle;
import vehicles.vehicle.VehicleBattery;
import vehicles.vehicle.VehicleColour;
import vehicles.vehicle.VehicleComponent;

/**
 * A class containing only static methods. To be used when the simulation is running to 
 * deal with genetic selection and evolution
 * 
 * @author Shaun
 *
 */
public class Genetics {
		

	/*******************************************************************************************\
	/																							\
	/                      Genetic Selection Algorithms for a set of Vehicles					\
	/																							\
	/*******************************************************************************************/
	
	/*
	 * 
	 * Values are as follows:
	 * 0 - 	Set by default, no selection method chosen. null returned
	 * 1 - 	Roulette - A selection operator in which the chance of a vehicle
	 *     	being selected is proportional to its fitness.
	 * 2 - 	Tournament - A selection operator which uses roulette selection N times
	 *     	to produce a tournament subset of vehicles. The best vehicle in this subset
	 *   	is then chosen as the selected vehicle to be the base for the next generation.
	 * 3 - 	Top Percent - A selection operator which randomly selects a vehicle from the top 
	 * 		N percent of the population as specified by the user.
	 * 4 - 	Best - A selection operator which selects the best vehicle (as determined by fitness).
	 * 		If there are two or more vehicle with the same level of top fitness, one of them is chosen
	 * 		randomly.
	 * 5 - Random - A selection operator which randomly selects a single vehicle from the population.
	 */
	
	protected static int NoSelection = 0;
	protected static int RouletteSelection = 1;
	protected static int TournamenetSelection = 2;
	protected static int TopNPercenSelection = 3;
	protected static int GetBestSelection = 4;
	protected static int RandomSelection = 5;

	
	/**
	 * A selection method that will choose a vehicle from the array of vehicles based
	 * on the value passed for genetic selection
	 * 
	 * @param gen_selection The genetic selection identifier. 
	 * @param v A vehicle array to extract a single vehicle from
	 * @param n This is for some of the genetic algorithms that require user input such as topNselection
	 */ 
	public static Vehicle getVehicle_SelectionBased(int gen_selection, Vector<Vehicle> v, int n){
		switch(gen_selection){
		case 0:
			System.out.println("No genetic selection method set! Returning null");
			return null;
		case 1:
			return getVehicleByRoulette(v);
		case 2:
			return getVehicleByTournament(v, n);
		case 3:
			return getVehicleByTop_N_Percent(v, n);
		case 4:
			return getVehicleByBest(v);
		case 5:
			return getVehicleByRandom(v);
		default:
			return null;
		}
	}


	/**
	 * A selection operator in which the chance of a vehicle being selected is 
	 * proportional to its fitness. This is where the concept of survival of the
	 * fittest comes into being. The fitness of a vehicle is related to the power 
	 * of its battery and motor
	 * 
	 * @return a vehicle chosen by roulette selection
	 */
	public static Vehicle getVehicleByRoulette(Vector<Vehicle> v){
		int len = v.size();
		sortByFitness(v);
		int i = 0;
		double[]percent = new double[len];
		double total = 0;
		for(i = 0; i < len; i++){
			percent[i] = v.elementAt(i).getFitness();
			total += percent[i];
		}
		percent[0] = (percent[0]/total) * 100;
		//System.out.println("Percent at 0: " + percent[0]); 
		for(i = 1; i < percent.length; i++){
			percent[i] = (percent[i]/total) * 100;
			percent[i] = percent[i] + percent[i-1];
			//System.out.println("Percent at " + i + " : " + percent[i]); 
		}
		
		Random ran = new Random();
		int random = ran.nextInt(101);
		//System.out.println("Random Number: " + random);
		for(i = 0; i < percent.length; i++){
			if(random <= percent[i]){
				return v.elementAt(i);
			}
		}
		return v.lastElement();
	}


	/**
	 * A selection operator which uses roulette selection N times
	 * to produce a tournament subset of vehicles. The best vehicle in this subset
	 * is then chosen as the selected vehicle to be the base for the next generation.
	 * @return a vehicle chosen by roulette selection N times
	 */
	public static Vehicle getVehicleByTournament(Vector<Vehicle> v, int n){
		if(n == 0){
			return null;
		}
		Vehicle[] subset = new Vehicle[n];

		for(int i = 0; i < n; i++){
			subset[i] = getVehicleByRoulette(v);
		}
		return getVehicleByBest(subset);
	}


	/**
	 * A selection operator which randomly selects a vehicle from the top 
	 * N percent of the population as specified by the user.
	 * @return a vehicle chosen from the top N percent
	 */
	public static Vehicle getVehicleByTop_N_Percent(Vector<Vehicle> v, int n){
		int size = v.size();
		//cant enter a number greater than 100..only goes up to 100%
		if(n > 100 || n <= 0){
			return  null;
		}
		//System.out.println("Looking for vehicle in top " + n + " percent of vehicle population");
		double topN = size * ((double)n/100);
		//System.out.println("topN of pop: " + topN);		
		sortByFitness(v);
	
		int diff = (int)(size - topN);
		int diff_minus = size - diff;
		//System.out.println("Generating random number between 0 and " + (diff_minus - 1));
		Random r = new Random();
		int ran = r.nextInt(diff_minus);
		//System.out.println("Random number: " + ran);
		return v.elementAt(ran + diff);
	}

	/**
	 * A selection operator which selects the best vehicle (as determined by fitness).
	 * @param A vector of vehicles to choose the best from
	 * @return the best vehicle in the set of vehicles
	 */
	public static Vehicle getVehicleByBest(Vector<Vehicle> v){
		sortByFitness(v);
		return v.lastElement();
	}

	/**
	 * A selection operator which selects the best vehicle (as determined by fitness).
	 * @param An array of vehicles to choose the best from
	 * @return the best vehicle in the set of vehicles
	 */
	public static Vehicle getVehicleByBest(Vehicle[] v){
		Arrays.sort(v);
		//for(int i = 0; i < v.length; i++){
			//System.out.println("" + v[i].getFitness());
		//}
		return v[(v.length - 1)];
	}
	
	/**
	 * A selection operator which randomly selects a single vehicle from the population.
	 * @return a random vehicle from the set of vehicles
	 */
	public static Vehicle getVehicleByRandom(Vector<Vehicle> v){
		Random ran = new Random();
		int num_veh = v.size();
		int random = ran.nextInt(num_veh);
		return v.elementAt(random);
	}

	/**
	 * A method to sort an array of vehicles by fitness
	 *@param v the vehcile array to sort 
	 */
	@SuppressWarnings("unchecked")
	public static void sortByFitness(Vector<Vehicle> v){
		Collections.sort(v);
	}
	
	
	
	
	
	
	
	
	/*******************************************************************************************\
	/																							\
	/                      Evolution and Mutation Algorithms for Vehicles						\
	/																							\
	/*******************************************************************************************/
	

	/**
	 * A method that takes in two integers and using a binary crossover and mutation algorithm
	 * returns a crssed over mutated combination of the two ints
	 * See http://geneticalgorithms.ai-depot.com/Tutorial/Overview.html for more
	 * @param parentA Parent A's integer to be used
	 * @param parentB Parent B's integer to be used
	 * @param The max value to be returned
	 * @return an int representing the combination of the two paretns ints
	 */
	public static int crossoverBitsAndMutate(int parentA, int parentB, int max_value){
		//get the two integers as binary and pad to 8 bits
		String pA = Integer.toBinaryString(parentA);
		String pB = Integer.toBinaryString(parentB);
		pA = addLeadingZeros(8, pA);
		pB = addLeadingZeros(8, pB);
		//generate a random number, this is the crossover point
		Random r = new Random();
		int ran = r.nextInt(8);
		//crossover the two binary numbers at the crossover point
		String crossed = (pA.substring(0, ran)).concat(pB.substring(ran, pB.length()));
		
		/*
		 * For Debugging
		System.out.println("Parent A as int\t" + parentA);
		System.out.println("Parent B as int\t" + parentB);
		System.out.println("Parent A as binary padded to 8 bits\t" + pA);
		System.out.println("Parent B as binary padded to 8 bits\t" + pB);
		System.out.println("Crossover point\t" + ran);
		System.out.println("Crossed\t" + crossed);
		*/
		//now choose a random bit and flip it
		ran = r.nextInt(8);
		//System.out.println("Bit to mutate\t" + ran);
		if(crossed.charAt(ran) == '0'){
			crossed = crossed.substring(0, ran) + "1" + crossed.substring(ran+1);
		}
		else{
			crossed = crossed.substring(0, ran) + "0" + crossed.substring(ran+1);
		}
		//System.out.println("Mutated\t" + crossed);
		int fin = Integer.parseInt(crossed, 2) ;
		//this allows us to use this method for any int in the range 0 .. 255
		if(fin > max_value){
			fin = max_value;
		}
		//Debugging
		//System.out.println("As an int\t" + fin);
		return fin;
	}

	public static Vehicle pairedMating(Vehicle parentA, Vehicle parentB){
		Vehicle child = new Vehicle();
		String name = "Offspring of "+ parentA.getName() + " and " + parentB.getName();
		child.setName(name);
		child.setAuthor(name);
		child.setDescription(name);
		
		//These will be set for an editor vehicle if we go down that path
		//For an object in memory alone, null is fine for these
		//child.setXmlLocation("tmp/" + name);
		//child.setFileName(name);
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		child.setLastModified(dateFormat.format(date));
		
		/*According to http://geneticalgorithms.ai-depot.com/Tutorial/Overview.html there is a 
		 * approximately a 70 % chance that crossover will occur. Our algorithm pushes this up to 80%
		 * and as such, for each attribute to be set via evolution, it will be determind via a random 
		 * number if it is to be created via crossover. If not, one of the parents will be chosen and 
		 * the attribute will be inerited directly.
		 */
		
		//Set the battery
		setBattery(child, parentA, parentB);
		
		//Set the memory
		setMemory(child, parentA, parentB);
		
		//Set the motor strength
		setMotorStrength(child, parentA, parentB);
		
		//Set the aggression
		setAggression(child, parentA, parentB);
		
		//Set the vehicle colour
		setColour(child, parentA, parentB);
		
		//Set Left Sensor
		setLeftSensor(child, parentA, parentB);
		
		//Set Right Sensor
		setRightSensor(child, parentA, parentB);
		
		return child;
	}
	
	private static void setRightSensor(Vehicle child, Vehicle parentA, Vehicle parentB){
		Random r = new Random();
		int ran = r.nextInt(10);
		int light, heat, power, water;
		if(ran <= 7){
			light = crossoverBitsAndMutate(parentA.getRightSensorLight() + 50, parentB.getRightSensorLight() + 50, 100) - 50; 
			heat = crossoverBitsAndMutate(parentA.getRightSensorHeat() + 50, parentB.getRightSensorHeat() + 50, 100) - 50;
			power = crossoverBitsAndMutate(parentA.getRightSensorPower() + 50, parentB.getRightSensorPower() + 50, 100) - 50;
			water = crossoverBitsAndMutate(parentA.getRightSensorWater() + 50, parentB.getRightSensorWater() + 50, 100) - 50;
			child.setRightSensorLight(light);
			child.setRightSensorHeat(heat);
			child.setRightSensorPower(power);
			child.setRightSensorWater(water);
			
		}
		else if (ran == 8){
			light = parentA.getRightSensorLight(); 
			heat = parentA.getRightSensorHeat();
			power = parentA.getRightSensorPower();
			water = parentA.getRightSensorWater();
			child.setRightSensorLight(light);
			child.setRightSensorHeat(heat);
			child.setRightSensorPower(power);
			child.setRightSensorWater(water);
		}
		else{
			light = parentB.getRightSensorLight(); 
			heat = parentB.getRightSensorHeat();
			power = parentB.getRightSensorPower();
			water = parentB.getRightSensorWater();
			child.setRightSensorLight(light);
			child.setRightSensorHeat(heat);
			child.setRightSensorPower(power);
			child.setRightSensorWater(water);
		}
	}
	
	private static void setLeftSensor(Vehicle child, Vehicle parentA, Vehicle parentB){
		Random r = new Random();
		int ran = r.nextInt(10);
		int light, heat, power, water;
		if(ran <= 7){
			light = crossoverBitsAndMutate(parentA.getLeftSensorLight() + 50, parentB.getLeftSensorLight() + 50, 100) - 50; 
			heat = crossoverBitsAndMutate(parentA.getLeftSensorHeat() + 50, parentB.getLeftSensorHeat() + 50, 100) - 50 ;
			power = crossoverBitsAndMutate(parentA.getLeftSensorPower() + 50, parentB.getLeftSensorPower() + 50, 100) - 50 ;
			water = crossoverBitsAndMutate(parentA.getLeftSensorWater() + 50, parentB.getLeftSensorWater() + 50, 100) - 50;
			child.setLeftSensorLight(light);
			child.setLeftSensorHeat(heat);
			child.setLeftSensorPower(power);
			child.setLeftSensorWater(water);
			
		}
		else if (ran == 8){
			light = parentA.getLeftSensorLight(); 
			heat = parentA.getLeftSensorHeat();
			power = parentA.getLeftSensorPower();
			water = parentA.getLeftSensorWater();
			child.setLeftSensorLight(light);
			child.setLeftSensorHeat(heat);
			child.setLeftSensorPower(power);
			child.setLeftSensorWater(water);
		}
		else{
			light = parentB.getLeftSensorLight(); 
			heat = parentB.getLeftSensorHeat();
			power = parentB.getLeftSensorPower();
			water = parentB.getLeftSensorWater();
			child.setLeftSensorLight(light);
			child.setLeftSensorHeat(heat);
			child.setLeftSensorPower(power);
			child.setLeftSensorWater(water);
		}
	}
	
	private static void setColour(Vehicle child, Vehicle parentA, Vehicle parentB){
		Random r = new Random();
		int ran = r.nextInt(10);
		int red, blue, green;
		if(ran <= 7){
			red = crossoverBitsAndMutate(parentA.getVehicleColourRed(), parentB.getVehicleColourRed(), 255);
			blue = crossoverBitsAndMutate(parentA.getVehicleColourBlue(), parentB.getVehicleColourBlue(), 255);
			green = crossoverBitsAndMutate(parentA.getVehicleColourGreen(), parentB.getVehicleColourGreen(), 255);
			child.setColour(red, green, blue);
		}
		else if (ran == 8){
			red = parentA.getVehicleColourRed();
			blue = parentA.getVehicleColourBlue();
			green = parentA.getVehicleColourGreen();
			child.setColour(red, green, blue);
		}
		else{
			red = parentB.getVehicleColourRed();
			blue = parentB.getVehicleColourBlue();
			green = parentB.getVehicleColourGreen();
			child.setColour(red, green, blue);
		}
	}
	
	private static void setAggression(Vehicle child, Vehicle parentA, Vehicle parentB){
		Random r = new Random();
		int ran = r.nextInt(10);
		int aggression;
		if(ran <= 7){
			aggression = crossoverBitsAndMutate(parentA.getAggression(), 
					parentB.getAggression(), 100);
			child.setAggression(aggression);
		}
		else if (ran == 8){
			child.setAggression(parentA.getAggression());
		}
		else{
			child.setAggression(parentB.getAggression());
		}
	}
	
	private static void setMotorStrength(Vehicle child, Vehicle parentA, Vehicle parentB){
		Random r = new Random();
		int ran = r.nextInt(10);
		int motStrength;
		if(ran <= 7){
			motStrength = crossoverBitsAndMutate(parentA.getMotorStrength(), 
					parentB.getMotorStrength(), 100);
			child.setMotorStrength(motStrength);
		}
		else if (ran == 8){
			child.setMotorStrength(parentA.getMotorStrength());
		}
		else{
			child.setMotorStrength(parentB.getMotorStrength());
		}
	}
	
	
	private static void setMemory(Vehicle child, Vehicle parentA, Vehicle parentB){
		Random r = new Random();
		int ran = r.nextInt(10);
		int max_mem, l_rate;
		if(ran <= 7){
			max_mem = crossoverBitsAndMutate(parentA.getMaxMem(),
					parentB.getMaxMem(), 100);
			l_rate = crossoverBitsAndMutate(parentA.getLearningRate(),
					parentB.getLearningRate(), 20);
			child.setMaxMem(max_mem);
			child.setLearningRate(l_rate);
		}
		else if (ran == 8){
			child.setMaxMem(parentA.getMaxMem());
			child.setLearningRate(parentA.getLearningRate());
		}
		else{
			child.setMaxMem(parentB.getMaxMem());
			child.setLearningRate(parentB.getLearningRate());
		}
	}
	
	
	private static void setBattery(Vehicle child, Vehicle parentA, Vehicle parentB){
		Random r = new Random();
		int ran = r.nextInt(10);
		int max_batt;
		if(ran <= 7){
			max_batt = crossoverBitsAndMutate(parentA.getMaxBatteryCapacity(),
					parentB.getMaxBatteryCapacity(), 100);
			child.setMaxBatteryCapacity(max_batt);
		}
		else if (ran == 8){
			child.setMaxBatteryCapacity(parentA.getMaxBatteryCapacity());
		}
		else{
			child.setMaxBatteryCapacity(parentB.getMaxBatteryCapacity());
		}
		//have the vehicle start off fully charged, after all, it was only just created
		child.setCurrentBatteryCapacity(child.getMaxBatteryCapacity());
	}

	/**
	 * A method to pad a string representing a binary number out to n bits by adding
	 * leading zeros
	 * @param n The desired number of bits this binary number should have
	 * @param bin The string representing a binary number
	 * @return A padded out version of the inputted string
	 */
	private static String addLeadingZeros(int n, String bin){
		int length = bin.length();
		int num_zeros = n - length;
		if ((num_zeros) <= 0){
			return bin;
		}
		String temp = "";
		for(int i = 0; i < num_zeros; i++){
			temp = temp.concat("0");
		}
		bin = temp.concat(bin);
		return bin;
	}

}
