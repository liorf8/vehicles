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

    @Override
	public void setup(){		
		size(200, 200);
		veh = new Vehicle("/home/graysr/Eclipse_Workspace/2ba7_GroupProject/xml/vehicles/hungry.xml");

	}
	
    @Override
	public void draw(){
		if(veh.getName()!= null){ //has it worked?
			background(255);    // Setting the background to white
			stroke(0);          // Setting the outline (stroke) to black
			fill(150);          // Setting the interior of a shape (fill) to grey 
			rect(50,50,75,100); // Drawing the rectangle
		}
	}
}
