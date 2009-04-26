package vehicles.genetics;

import java.util.Random;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;
import vehicles.vehicle.Vehicle;

/**
 * A class containing only static methods. To be used when the simulation is running to 
 * deal with genetic selection and evolution
 * 
 * @author graysr
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
				break;
			}
		}
		return v.elementAt(i);
	}


	/**
	 * A selection operator which uses roulette selection N times
	 * to produce a tournament subset of vehicles. The best vehicle in this subset
	 * is then chosen as the selected vehicle to be the base for the next generation.
	 * @return a vehicle chosen by roulette selection N times
	 */
	public static Vehicle getVehicleByTournament(Vector<Vehicle> v, int n){
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
		if(n > 100){
			return  null;
		}
		System.out.println("Looking for vehicle in top " + n + " percent of vehicle population");
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
		return v[v.length - 1];
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
	public static void sortByFitness(Vector<Vehicle> v){
		Collections.sort(v);
	}

}
