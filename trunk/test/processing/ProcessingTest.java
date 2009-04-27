package test.processing;
/**
 *  test class
 * @author Karl
 */
import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import vehicles.simulation.Simulation;
import vehicles.vehicle.*;
import vehicles.environment.EnvironmentElement;
import processing.core.*;
@SuppressWarnings("serial")

public class ProcessingTest extends PApplet{
	Simulation sim;
	Vector<EnvironmentElement> elements;
	Vector<ProcessingVehicle> vehicles;

	public ProcessingTest(Simulation simu){
		this.sim = simu;
	}

	public void setMove_speed(float x){
		//need to implement
	}

	@Override
	public void setup(){	
		size(sim.getEnvironment().getWidth() ,sim.getEnvironment().getHeigth());
		//populate our list of environment elements
		this.elements = sim.getEnvironment().getElements();

		Vector<Vehicle> temp = sim.getVehicles();
		this.vehicles = new Vector<ProcessingVehicle>();
		Iterator<Vehicle> it = temp.iterator();
		while(it.hasNext()){
			this.vehicles.add(new ProcessingVehicle(it.next()));
		}

	}

	@Override
	public void draw(){
		stroke(0);          // Setting the outline (stroke) to black
		background(0,0,0);
		fill(150);          // Setting the interior of a shape (fill) to grey 
		Iterator<EnvironmentElement> it = elements.iterator();
		while(it.hasNext()){
			EnvironmentElement curr = it.next();
			switch(curr.getType()){
			case EnvironmentElement.HeatSource:
				fill(Color.RED.getRGB());
				break;
			case EnvironmentElement.LightSource:
				fill(Color.GREEN.getRGB());
				break;
			case EnvironmentElement.WaterSource:
				fill(Color.BLUE.getRGB());
				break;
			case EnvironmentElement.PowerSource:
				fill(Color.WHITE.getRGB());
				break;
			}
			ellipse((float)curr.getXpos(),
					(float)curr.getYpos(),
					(float)curr.getRadius(),
					(float)curr.getRadius());
		}
		Iterator<ProcessingVehicle> vehIt = this.vehicles.iterator();
		while(vehIt.hasNext()){
			ProcessingVehicle curr = vehIt.next();
			curr.draw();
		}
	}
	/**
	 * A vehicle in the engine, subclassing the more generic Vehicle class
	 * @author Karl
	 *
	 */
	class ProcessingVehicle extends Vehicle{
		float x,y; //vehicle's position
		int max_movement = 5; //the maximum number of units we can move in a single step

		ProcessingVehicle(Vehicle v){
			super(v);
			this.x = (int)random(sim.getEnvironment().getWidth());
			this.y = (int)random(sim.getEnvironment().getHeigth());
		}

		void draw(){
			fill(Color.ORANGE.getRGB()); //vehicles are orange
			rect(x,y,10,15);
			Iterator<EnvironmentElement> it = elements.iterator();
			
			int total_absolute_sensed = 0; //the amount this vehicle likes elements
			float total_intensities_at_point = 0.0f; //total intensities of all elements around the vehicle
			/*
			 * Process a single element realitive to this vehicle
			 */
			while(it.hasNext()){
				EnvironmentElement curr = it.next();
				float distanceTo = dist( //from us(x,y) to curr(getXpos,getYpos)
						x,
						y,
						(float)curr.getXpos(),
						(float)curr.getYpos());
			
				boolean onLeft = (curr.getXpos() - this.x <= 0); 
				boolean above = (curr.getYpos() - this.y <= 0); 
				//well now we have the distance to an element, and its type and direction
				// so we need to do some maths, based on things like this.getLeftSensorHeat()
				// to determine what speed to apply to the motor
				if(distanceTo < curr.getRadius()){ //is actually in range
					//sum intensities
					float relIntensity = curr.getStrength() / distanceTo; //relative intensity
					total_intensities_at_point += relIntensity;
					//sum likes
					switch(curr.getType()){
					case EnvironmentElement.HeatSource:
						total_absolute_sensed += abs(this.getLeftSensorHeat());
						break;
					case EnvironmentElement.LightSource:
						total_absolute_sensed += abs(this.getLeftSensorLight());
						break;
					case EnvironmentElement.PowerSource:
						total_absolute_sensed += abs(this.getLeftSensorPower());
						break;
					case EnvironmentElement.WaterSource:
						total_absolute_sensed += abs(this.getLeftSensorWater());
						break;
					}
					
				} //end if
			}// end a single element that this vehicle can see
			//TODO generate the 'temp' variables
		} //end draw
		
		
		
	}//end inner class
}//end outer class

