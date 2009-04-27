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
	
	public ProcessingTest(Simulation simu){
		this.sim = simu;
	}
	
	public void setMove_speed(float x){
		//need to implement
	}
	
    @Override
	public void setup(){	
    	size(sim.getEnvironment().getWidth() ,sim.getEnvironment().getHeigth());
    	this.elements = sim.getEnvironment().getElements();

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
	}
}
