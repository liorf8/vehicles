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
	boolean specialSense = true, perishable_vehicles, evolution = false, asexual = false;
	float move_speed = 0;
	int num_sources, num_vehicles;
	int w, h;
	int repro_method;
	int sel_method;
	int n_for_sel;
	PFont font;
	String font_location = "src" + java.io.File.separator + "data" + java.io.File.separator + "CourierNew36.vlw";
	String on_screen_message = null;
	ProcessingVehicle curr_on_screen = null;

	public float getMove_speed() {
		return move_speed;
	}

	public void setMove_speed(float move_speed) {
		this.move_speed = move_speed / PI;
	}

	public SimulatonEngine(Simulation simu) {

		System.out.println("Engine received object " + simu.getXmlLocation());
		this.sim = simu;

		this.perishable_vehicles = this.sim.getPerishableVehicles();
		this.evolution = this.sim.getEvolution();
		this.sel_method = this.sim.getGeneticSelectionMethod();
		this.n_for_sel = this.sim.getN();
		this.repro_method = this.sim.getReproductionMethod();

		Vector<EnvironmentElement> elements = simu.getEnvironment().getElements();
		Vector<Vehicle> veh = simu.getVehicles();

		vehicleVector = new Vector<ProcessingVehicle>();
		int num_veh = veh.size();

		boolean paired = false;
		if(this.evolution){
			if(this.repro_method == 1){
				asexual = true;
			}
			else if(this.repro_method == 2){
				paired = true;
			}
			else{
				this.evolution = false;
			}
		}

		for (int i = 0; i < num_veh; i++) {
			this.vehicleVector.add(new ProcessingVehicle(this, veh.elementAt(i), 400, 400, /*random(PI)*/12f, 10, i, 3, paired, this.perishable_vehicles));
		}

		this.num_vehicles = vehicleVector.size();

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
		font = loadFont(font_location); 
		textFont(font, 14); 

		size(w, h);
		noStroke();
		ground = new PImage(width, height);
		smooth();
		updateGround();
	}


	// Processing Sketch Main Loop
	@Override
	public void draw() {
		image(ground, 0, 0);

		if(this.on_screen_message != null){
			fill(100, 255, 190);
			text(this.on_screen_message, 200, 200, this.on_screen_message.length() * 5, 100);
		}


		if(this.num_vehicles > 0){
			for(int i = 0; i < this.num_vehicles; i++){
				ProcessingVehicle temp = this.vehicleVector.elementAt(i);
				temp.move();
				temp.draw();
				this.num_vehicles = vehicleVector.size();
			}
		}

		updateOnScreenMessage();
		
		if(mousePressed){
			checkMouse(pmouseX, pmouseY);
		}
	}

	public void pause(){
		System.out.println("Paused!");
		noLoop();
	}

	public void startSim(){
		loop();
	}

	public void updateOnScreenMessage(){
		if(this.curr_on_screen != null){
			this.on_screen_message = this.curr_on_screen.toString();
		}
		else this.on_screen_message = null;
	}
	
	public void checkMouse(float x, float y){
		float xPos, yPos, axle;
		for(int i = 0; i < this.num_vehicles; i++){
			xPos = this.vehicleVector.elementAt(i).x;
			yPos = this.vehicleVector.elementAt(i).y;
			axle = this.vehicleVector.elementAt(i).axle;
			if((x <= xPos + axle && y <= yPos + axle) && (x >= xPos - axle && y <= yPos + axle) &&
					(x <= xPos + axle && y >= yPos - axle) && (x >= xPos - axle && y >= yPos - axle)){
				this.curr_on_screen = this.vehicleVector.elementAt(i);
				this.updateOnScreenMessage();
			}
			else {
				this.on_screen_message = null;
				this.curr_on_screen = null;
			}
		}

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
								color(r, g, b / 8, c)); //r,g,b
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

