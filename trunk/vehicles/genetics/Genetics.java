package vehicles.genetics;

import java.util.Random;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;

import vehicles.vehicle.Vehicle;
import vehicles.simulation.*;
import vehicles.util.*;

/**
 * A class containing only static methods. To be used when the simulation is running to 
 * deal with genetic selection and evolution
 * 
 * @author Shaun
 *
 */
public class Genetics {

	public static int AsexualReproduction = 0;
	public static int PairedMating = 1;	
	private static int offspring_number = 0;

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

	public static int NoSelection = 0;
	public static int RouletteSelection = 1;
	public static int TournamenetSelection = 2;
	public static int TopNPercenSelection = 3;
	public static int GetBestSelection = 4;
	public static int RandomSelection = 5;


	/**
	 * A selection method that will choose a vehicle from the array of vehicles based
	 * on the value passed for genetic selection
	 * 
	 * @param gen_selection The genetic selection identifier. 
	 * @param v A vehicle array to extract a single vehicle from
	 * @param n This is for some of the genetic algorithms that require user input such as topNselection
	 */ 
	public static Vehicle getVehicle_SelectionBased(int gen_selection, Vector<Vehicle> v, int n, SimulationLog s){
		switch(gen_selection){
		case 0:
			System.out.println("No genetic selection method set! Returning null");
			return null;
		case 1:
			return getVehicleByRoulette(v, s);
		case 2:
			return getVehicleByTournament(v, n, s);
		case 3:
			return getVehicleByTop_N_Percent(v, n, s);
		case 4:
			return getVehicleByBest(v, s);
		case 5:
			return getVehicleByRandom(v, s);
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
	public static Vehicle getVehicleByRoulette(Vector<Vehicle> v,  SimulationLog s){
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
		s.addToLog("Choosing vehicle by Roulette Selection.\nVehicle " + v.lastElement().getName() + " chosen by Roulette Selection");
		return v.lastElement();
	}


	/**
	 * A selection operator which uses roulette selection N times
	 * to produce a tournament subset of vehicles. The best vehicle in this subset
	 * is then chosen as the selected vehicle to be the base for the next generation.
	 * @return a vehicle chosen by roulette selection N times
	 */
	public static Vehicle getVehicleByTournament(Vector<Vehicle> v, int n,  SimulationLog s){
		if(n == 0){
			s.addToLog("Choosing vehicle by Tournament Selection. As n was 0, a null vehicle is being returned");
			return null;
		}
		Vehicle[] subset = new Vehicle[n];

		for(int i = 0; i < n; i++){
			subset[i] = getVehicleByRoulette(v, s);
		}
		Vehicle temp = getVehicleByBest(subset);
		s.addToLog("Choosing vehicle by Tournament Selection. For this run a subset of the population\n" +
				"of size " + n + " will be created using roulette selection " + n + " times and the\n" +
				"best vehicle in this subset will be returned.\nVehicle " + temp.getName() + " chosen by Tournament Selection");
		return temp;
	}


	/**
	 * A selection operator which randomly selects a vehicle from the top 
	 * N percent of the population as specified by the user.
	 * @return a vehicle chosen from the top N percent
	 */
	public static Vehicle getVehicleByTop_N_Percent(Vector<Vehicle> v, int n,  SimulationLog s){
		int size = v.size();
		//cant enter a number greater than 100..only goes up to 100%
		if(n > 100 || n <= 0){
			s.addToLog("As n was 0, returning a null vehicle");
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
		Vehicle temp = v.elementAt(ran + diff);
		s.addToLog("Choosing a vehicle from the top " +  n + " percent of the population.\nVehicle " + temp.getName()
				+ " chosen as a vehicle from the top " + n + 
		" percent of the population");
		return temp;
	}

	/**
	 * A selection operator which selects the best vehicle (as determined by fitness).
	 * @param A vector of vehicles to choose the best from
	 * @return the best vehicle in the set of vehicles
	 */
	public static Vehicle getVehicleByBest(Vector<Vehicle> v,  SimulationLog s){
		sortByFitness(v);
		s.addToLog("Choosing the best vehicle from the population in terms of fitness.\nVehicle " 
				+ v.lastElement().getName() + " chosen as the Best\nVehicle in the population.");
		return v.lastElement();
	}

	/**
	 * A selection operator which selects the best vehicle (as determined by fitness).
	 * @param An array of vehicles to choose the best from
	 * @return the best vehicle in the set of vehicles
	 */
	public static Vehicle getVehicleByBest(Vehicle[] v){
		Arrays.sort(v);
		return v[(v.length - 1)];
	}

	/**
	 * A selection operator which randomly selects a single vehicle from the population.
	 * @return a random vehicle from the set of vehicles
	 */
	public static Vehicle getVehicleByRandom(Vector<Vehicle> v,  SimulationLog s){
		Random ran = new Random();
		int num_veh = v.size();
		int random = ran.nextInt(num_veh);
		s.addToLog("Choosing a random vehicle from the population\nVehicle " 
				+ v.elementAt(random).getName() + " chosen by random from the population.");
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


	////////////////////////////////     Paired Mating     \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

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

	public static Vehicle pairedMating(Vehicle parentA, Vehicle parentB,  SimulationLog s){
		Vehicle child = new Vehicle();
		String description = "Offspring of \n'"+ parentA.getName() + "' and\n'" + parentB.getName() + "'" +
			"\nCreated " + UtilMethods.getTimeStamp();
		String name = "Offspring of paired mating #" + offspring_number;
		offspring_number ++;
		s.addToLog("New vehicle created from\n'" + parentA.getName() + "' and\n'" + parentB.getName() +
				"'\nNew vehicle name: " + name);
		child.setName(name);
		child.setAuthor(name);
		child.setDescription(description);

		//These will be set for an editor vehicle if we go down that path
		//For an object in memory alone, null is fine for these
		//child.setXmlLocation("src/test/genetics/tmp/" + Integer.toString(description.hashCode()) + ".veh");
		//child.setFileName(Integer.toString(name.hashCode()));
		child.setFileName(name);


		child.setLastModified(UtilMethods.getTimeStamp());

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

		//child.saveVehicle();
		return child;
	}

	/*According to http://geneticalgorithms.ai-depot.com/Tutorial/Overview.html there is a 
	 * approximately a 70 % chance that crossover will occur. Our algorithm pushes this up to 80%
	 * and as such, for each attribute to be set via evolution, it will be determind via a random 
	 * number if it is to be created via crossover. If not, one of the parents will be chosen and 
	 * the attribute will be inerited directly.
	 */

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
			child.setCurrentBatteryCapacity(max_batt);
		}
		else if (ran == 8){
			child.setMaxBatteryCapacity(parentA.getMaxBatteryCapacity());
			child.setCurrentBatteryCapacity(parentA.getMaxBatteryCapacity());
		}
		else{
			child.setMaxBatteryCapacity(parentB.getMaxBatteryCapacity());
			child.setCurrentBatteryCapacity(parentB.getMaxBatteryCapacity());
		}
	}





	////////////////////////////////     Asexual Reproduction     \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

	/**
	 * This methods allows you to pass all variables associated with asexual reproduction
	 * in one pass to generate a vehicle
	 */
	public static Vehicle produceVehicleAsexually(int gen_selection, int n_for_selection, Vector<Vehicle> v, SimulationLog s){
		Vehicle parent = Genetics.getVehicle_SelectionBased(gen_selection, v, n_for_selection, s);
		if(parent == null){
			return null;
		}
		return Genetics.asexualReproduction(parent, s);
	}

	/**
	 * There is a 70% chance of a some attributes of a vehicle being mutated
	 * There is a 30% chance of inheriting some attributes directly
	 * @param parent The parent to produce offspring from asexually
	 * @parma s A simulation log to log the reproduction
	 * @return A vehicle which is the offspring of parent
	 */
	public static Vehicle asexualReproduction(Vehicle parent, SimulationLog s){
		Vehicle child = new Vehicle();
		String name = "Offspring of "+ parent.getName();
		s.addToLog("Creating a new vehicle by asexual reproduction of " + parent.getName() +
				"\nNew vehicle name: " + name);
		child.setName(name);
		child.setAuthor(name);
		child.setDescription(name);

		//These will be set for an editor vehicle if we go down that path
		//For an object in memory alone, null is fine for these
		//child.setXmlLocation("src/test/genetics/tmp/" + UtilMethods.formatString(name) + ".veh");
		child.setXmlLocation(null);
		//s.addToLog("New vehicle temporarily stored in: " + child.getXmlLocation());
		child.setFileName(name);


		child.setLastModified(UtilMethods.getTimeStamp());

		//Set the battery
		setBattery(child, parent);

		//Set the memory
		setMemory(child, parent);

		//Set the motor strength
		setMotorStrength(child, parent);

		//Set the aggression
		setAggression(child, parent);

		//Set the vehicle colour
		setColour(child, parent);

		//Set Left Sensor
		setLeftSensor(child, parent);

		//Set Right Sensor
		setRightSensor(child, parent);

		//child.saveVehicle();

		return child;
	}


	private static void setRightSensor(Vehicle child, Vehicle parent){
		Random r = new Random();
		int ran = r.nextInt(10);
		int light = parent.getRightSensorLight() + 50;
		int heat = parent.getRightSensorHeat() + 50;
		int power = parent.getRightSensorPower() + 50;
		int water = parent.getRightSensorWater() + 50;
		if(ran <= 7){
			child.setRightSensorLight((mutateAttribute(light, 100)) - 50);
			child.setRightSensorHeat((mutateAttribute(heat, 100)) - 50);
			child.setRightSensorPower((mutateAttribute(power, 100)) - 50);
			child.setRightSensorWater((mutateAttribute(water, 100)) - 50);

		}
		else{
			child.setRightSensorLight(light - 50);
			child.setRightSensorHeat(heat - 50);
			child.setRightSensorPower(power - 50);
			child.setRightSensorWater(water - 50);
		}
	}	

	private static void setLeftSensor(Vehicle child, Vehicle parent){
		Random r = new Random();
		int ran = r.nextInt(10);
		int light = parent.getLeftSensorLight() + 50;
		int heat = parent.getLeftSensorHeat() + 50;
		int power = parent.getLeftSensorPower() + 50;
		int water = parent.getLeftSensorWater() + 50;
		if(ran <= 7){
			child.setLeftSensorLight((mutateAttribute(light, 100)) - 50);
			child.setLeftSensorHeat((mutateAttribute(heat, 100)) - 50);
			child.setLeftSensorPower((mutateAttribute(power, 100)) - 50);
			child.setLeftSensorWater((mutateAttribute(water, 100)) - 50);

		}
		else{
			child.setLeftSensorLight(light - 50);
			child.setLeftSensorHeat(heat - 50);
			child.setLeftSensorPower(power - 50);
			child.setLeftSensorWater(water - 50);
		}
	}	

	private static void setColour(Vehicle child, Vehicle parent){
		Random r = new Random();
		int ran = r.nextInt(10);
		int red = parent.getVehicleColourRed();
		int blue =  parent.getVehicleColourBlue();
		int green = parent.getVehicleColourGreen();
		if(ran < 7){
			child.setColour(mutateAttribute(red, 255), mutateAttribute(green, 255), mutateAttribute(blue, 255));
		}
		else{
			child.setColour(red, green, blue);
		}
	}

	private static void setAggression(Vehicle child, Vehicle parent){
		Random r = new Random();
		int ran = r.nextInt(10);
		int a = parent.getAggression();
		if(ran < 7){
			child.setAggression(mutateAttribute(a, 100));
		}
		else{
			child.setAggression(a);
		}
	}

	private static void setMotorStrength(Vehicle child, Vehicle parent){
		Random r = new Random();
		int ran = r.nextInt(10);
		int strength = parent.getMotorStrength();
		if(ran < 7){
			child.setMotorStrength(mutateAttribute(strength, 100));
		}
		else{
			child.setMotorStrength(strength);
		}
	}


	private static void setMemory(Vehicle child, Vehicle parent){
		Random r = new Random();
		int ran = r.nextInt(10);
		int max_mem = parent.getMaxMem();
		int learn = parent.getLearningRate();
		if(ran < 7){
			child.setMaxMem(mutateAttribute(max_mem, 100));
			child.setLearningRate(mutateAttribute(learn, 20));
		}
		else{
			child.setMaxMem(max_mem);
			child.setLearningRate(learn);
		}
	}

	private static void setBattery(Vehicle child, Vehicle parent){
		Random r = new Random();
		int ran = r.nextInt(10);
		int max_batt = parent.getMaxBatteryCapacity();
		if(ran < 7){
			child.setMaxBatteryCapacity(mutateAttribute(max_batt, 100));
			child.setCurrentBatteryCapacity(child.getMaxBatteryCapacity());
		}
		else{
			child.setMaxBatteryCapacity(max_batt);
			child.setCurrentBatteryCapacity(max_batt);
		}
	}



	private static int mutateAttribute(int attr, int max){
		String bin = Integer.toBinaryString(attr);
		bin = addLeadingZeros(8, bin);
		Random r = new Random();
		int ran = r.nextInt(8);
		if(bin.charAt(ran) == '0'){
			bin = bin.substring(0, ran) + "1" + bin.substring(ran+1);
		}
		else{
			bin = bin.substring(0, ran) + "0" + bin.substring(ran+1);
		}
		int fin = Integer.parseInt(bin, 2) ;
		if(fin > max){
			return max;
		}
		else return fin; 
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
