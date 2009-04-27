package test.genetics;
import vehicles.simulation.*;
import vehicles.vehicle.*;
import vehicles.genetics.*;

import java.util.Vector;
import java.util.Random;

/**
 * Class for testing genetic algorithms
 * @author graysr
 *
 */
public class GeneticsTestCases {

	public static void main(String[] args){


		Random r = new Random();

		Simulation sim = new Simulation("xml/simulations/testsim.sim");

		Vector<Vehicle> v = sim.getVehicles();

		Vehicle temp;

		//Tester for best vehicle in the population
		temp = Genetics.getVehicleByBest(v);
		if(temp != null){
			System.out.println("Best vehicles fitness: " + temp.getFitness());
			System.out.println("Filepath: " + temp.getXmlLocation());
		}

		System.out.println("\n\n\n");

		//Tester for random vehicle in the population.
		//Run n times to check random generator seed is good
		for(int i = 0; i < 10; i++){
			temp = Genetics.getVehicleByRandom(v);
			if(temp != null){
				System.out.println("Random vehicles fitness: " + temp.getFitness());
				System.out.println("Filepath: " + temp.getXmlLocation());
			}
		}

		System.out.println("\n\n\n");

		//Tester for getting a vehicle by roulette
		//Run n times to check it works properly
		for(int i = 0; i < 10; i++){
			temp = Genetics.getVehicleByRoulette(v);
			if(temp != null){
				System.out.println("Roulette vehicles fitness: " + temp.getFitness());
				System.out.println("Filepath: " + temp.getXmlLocation());
			}
		}

		System.out.println("\n\n\n");

		//Tester for getting a vehicle by tournament
		//Run n times to check it works properly with n being random between 1 and 100 each time
		for(int i = 0; i < 10; i++){
			temp = Genetics.getVehicleByTournament(v, r.nextInt(101));
			if(temp != null){
				System.out.println("Tournament vehicles fitness: " + temp.getFitness());
				System.out.println("Filepath: " + temp.getXmlLocation());
			}
		}


		System.out.println("\n\n\n");

		//Tester for getting a vehicle by topNPercent
		//Run n times to check it works properly with n being random between 1 and 100 each time
		for(int i = 0; i < 10; i++){
			temp = Genetics.getVehicleByTop_N_Percent(v, r.nextInt(101));
			if( temp != null){
				System.out.println("TopNPercent vehicles fitness: " + temp.getFitness());
				System.out.println("Filepath: " + temp.getXmlLocation());
			}
		}
		
		System.out.println("\n\nTesting Paired Mating \n\n");
		
		Vehicle parentA = new Vehicle("xml/vehicles/angry.veh");
		Vehicle parentB = new Vehicle("xml/vehicles/stoner.veh");
		System.out.println("Parent A specs:");
		parentA.printDetails();
		System.out.println("\nParent B specs:");
		parentB.printDetails();
		Vehicle child = Genetics.pairedMating(parentA, parentB);
		System.out.println("\nChild specs:");
		child.printDetails();
		child.saveVehicle();
		
	}
}
