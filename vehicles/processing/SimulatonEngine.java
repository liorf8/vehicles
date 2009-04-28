package vehicles.processing;

import java.util.Vector;
import java.util.Iterator;
import processing.core.*;
import vehicles.environment.*;
import vehicles.simulation.*;
import vehicles.vehicle.*;

@SuppressWarnings("serial")
public class SimulatonEngine extends PApplet {

	Simulation sim; //simulation representing this
	Vector<ProcessingVehicle> vehicleVector;
	Vector<ProcessingEnviroElement> elementVector;
	Environment enviro;
	PImage ground; //background image
	boolean specialSense = true;
	float move_speed = 0;
	int num_sources;
	int w, h;

	public float getMove_speed() {
		return move_speed;
	}

	public void setMove_speed(float move_speed) {
		this.move_speed = move_speed / PI;
	}

	public SimulatonEngine(Simulation simu) {

		System.out.println("Engine received object " + simu.getXmlLocation());
		this.sim = simu;

		Vector<EnvironmentElement> elements = simu.getEnvironment().getElements();
		Vector<Vehicle> veh = simu.getVehicles();

		vehicleVector = new Vector<ProcessingVehicle>();
		int num_veh = veh.size();

		for (int i = 0; i < num_veh; i++) {
			this.vehicleVector.add(new ProcessingVehicle(this, veh.elementAt(i), 400, 400, random(PI), 10, i, 3));
		}

		this.num_sources = elements.size();
		enviro = this.sim.getEnvironment();
		this.w = enviro.getWidth();
		this.h = enviro.getHeigth();

		elementVector = new Vector<ProcessingEnviroElement>();

		for (int i = 0; i < this.num_sources; i++) {
			EnvironmentElement curr = elements.elementAt(i);
			System.out.print(i + " : ");
			elementVector.add(new ProcessingEnviroElement(this, curr, curr.getName().hashCode()));
			print(elementVector.elementAt(i).toString());
		}
	}

	// Processing Sketch Setup
	@Override
	public void setup() {
		size(w, h);
		noStroke();
		ground = new PImage(width, height);
		smooth();
		updateGround();
	}


	// Processing Sketch Main Loop
	@Override
	public void draw() {
		background(0, 0, 0);

		image(ground, 0, 0);
		fill(155);
		//for (int i = 0; i < num_sources; i++) {
		//	elementVector.elementAt(i).draw();

		//}
		/*
		 * if(frameCount % some_constant == 0){
		 * 		make vehicles evolve
		 */

		Iterator<ProcessingVehicle> vehicleIterator = vehicleVector.iterator();
		while (vehicleIterator.hasNext()) {
			ProcessingVehicle temp = vehicleIterator.next();
			temp.move();
			temp.draw();
		}
	}

	private double distBetweenPoints(Point a, Point b) {
		double x1 = a.getXpos();
		double y1 = a.getYpos();
		double x2 = b.getXpos();
		double y2 = b.getYpos();
		double dist = Math.sqrt((Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2)));
		return dist;
	}

	/**
	 * update the light source on the ground
	 * 
	 * This seems to be for the functionality of inverting the sense of the environment
	 *  elements, so the vehicles are afraid of them -- only partly. It actually draws
	 *  the elementVector
	 */
	void updateGround() {

		float sum;
		int c, r, g, b;
		int px = 1;

		ground = new PImage(width, height);
		for (int i = 0; i < width; i += px) {
			for (int k = 0; k < height; k += px) { //process every pixel in the image

				sum = 0;
                r = 0;
                g = 0;
                b = 0;
				for (int m = 0; m < num_sources; m++) {

					//pass this pixel's position
					sum += elementVector.elementAt(m).getIntensityAtPoint(i, k);
                    r += elementVector.elementAt(m).getRedAtPoint(i, k);
                    g += elementVector.elementAt(m).getGreenAtPoint(i, k);
                    b += elementVector.elementAt(m).getBlueAtPoint(i, k);
					//sum up intensity of elementVector until it reachrs one
					if (sum >= 1) {
						break;
					}

				}

				c = (int) min(sum * 255, 255);

				
				for (int p = 0; p < px; p++) {
					for (int q = 0; q < px; q++) {
						//agh, horrible code
						ground.set(i + p, k + q,
								color(r, g, b / 8)); //r,g,b

					}
				}
				
			}
		}

	}
	
	float nonlinear(float r, float rmax) {
        float f = (rmax - Math.min(r, rmax)) / rmax;
        return 0.5f - 0.5f * cos(f * PI);
    }


}

