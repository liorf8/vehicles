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

		String p;
		for(int i = 0; i < 101; i++){
			p = Integer.toBinaryString(i);
			p = addLeadingZeros(8, p);
			System.out.println(i + " as binary sring: " +  p);
		}
		
		p = crossoverBits(Integer.toBinaryString(99), Integer.toBinaryString(62));

	}
	
	public static String crossoverBits(String parentA, String parentB){
		parentA = addLeadingZeros(8, parentA);
		parentB = addLeadingZeros(8, parentB);
		System.out.println("Parent A\t" + parentA);
		System.out.println("Parent B\t" + parentB);
		Random r = new Random();
		int ran = r.nextInt(8);
		String crossed = (parentA.substring(0, ran)).concat(parentB.substring(ran, parentB.length()));
		System.out.println("Crossed\t" + crossed);
		System.out.println("Crossove point\t" + ran);
		return "";
	}
	
	
	public static String addLeadingZeros(int n, String bin){
		int length = bin.length();
		System.out.println("Lenght: " + length);
		int num_zeros = n - length;
		System.out.println("Zeroes: " + num_zeros);
		if ((num_zeros) <= 0){
			return null;
		}
		String temp = "";
		for(int i = 0; i < num_zeros; i++){
			temp = temp.concat("0");
		}
		System.out.println("Temp: " + temp);
		bin = temp.concat(bin);
		System.out.println("Bin: " + bin);
		return bin;
		
	}
}
