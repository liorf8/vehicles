package test.processing;
/**
 * Crappy crappy test class
 * @author Karl
 */
import vehicles.vehicle.*;
import processing.core.*;
@SuppressWarnings("serial")

public class ProcessingTest extends PApplet{
	Vehicle veh; //one of our Java vehicles
	
	public void setup(){		
		size(200, 200);
		veh = new Vehicle("hungry.xml");

	}
	
	public void draw(){
		if(veh.getVehicleName()!= null){ //has it worked?
			background(255);    // Setting the background to white
			stroke(0);          // Setting the outline (stroke) to black
			fill(150);          // Setting the interior of a shape (fill) to grey 
			rect(50,50,75,100); // Drawing the rectangle
		}
	}
}
