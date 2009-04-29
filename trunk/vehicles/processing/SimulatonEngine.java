package vehicles.processing;

import java.text.DecimalFormat;
import java.util.Vector; 
import processing.core.*;
import vehicles.environment.*;
import vehicles.simulation.*;
import vehicles.vehicle.*;
import vehicles.genetics.*;
import vehicles.util.StopWatch;

@SuppressWarnings("serial")
public class SimulatonEngine extends PApplet {
	Simulation sim; //simulation representing this
	Vector<ProcessingVehicle> vehicleVector;
	Vector<ProcessingEnviroElement> elementVector;
	Environment enviro;
	PImage ground; //background image
	boolean specialSense = true, perishable_vehicles, evolution = false, asexual = false, pairedMating = false;
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
	int update_on_screen = 0;
	float asexual_reproduction_constant = 0;
	float chance_asexual_repro = 1.0f; //this is out of ten i.e 10% chance
	StopWatch stopwatch;
	int axle = 10;
	int min_time_for_asexual = 20;
	int max_time_for_asexual = 40;
	int time_now = 0;
	int text_box_width = 0;
	int text_box_height = 0;
	boolean canMate = false;

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

		if(this.evolution){
			if(this.repro_method == 1){
				asexual = true;
			}
			else if(this.repro_method == 2){
				pairedMating = true;
			}
			else{
				this.evolution = false;
			}
		}

		if(asexual){
			if (this.sel_method == Genetics.NoSelection){
				evolution = false;
				asexual = false;
			}
			if(this.n_for_sel == 0 && (this.sel_method == Genetics.TopNPercenSelection || this.sel_method == Genetics.TournamenetSelection)){
				this.evolution = false;
				asexual = false;
			}
		}

		for (int i = 0; i < num_veh; i++) {
			this.vehicleVector.add(new ProcessingVehicle(this, veh.elementAt(i), 400, 400, /*random(PI)*/12f, axle,
					i, pairedMating, this.perishable_vehicles));
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
		//this.asexual_reproduction_constant = (int)this.random(this.max_time_for_asexual - this.min_time_for_asexual) + this.min_time_for_asexual;
		this.asexual_reproduction_constant = 1;
		System.out.println("Sexual Reproduction_constant: " + this.asexual_reproduction_constant);
		stopwatch = new StopWatch();
	}

	// Processing Sketch Setup
	@Override
	public void setup() {
		this.stopwatch.start();
		//this.stopwatch.addSecond();
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

		this.num_vehicles = this.vehicleVector.size();
		if(this.num_vehicles > 0){
			for(int i = 0; i < this.num_vehicles; i++){
				ProcessingVehicle temp = this.vehicleVector.elementAt(i);
				temp.move();
				temp.draw();
				this.num_vehicles = vehicleVector.size();
			}
			if(this.asexual){
				this.asexualReproduction();
			}
			this.num_vehicles = vehicleVector.size();
		}

		updateOnScreenMessage();

		if (mousePressed && (mouseButton == RIGHT)) {
			checkMouse(pmouseX, pmouseY, 2);
		}
		else if (mousePressed && (mouseButton == LEFT)) {
			checkMouse(pmouseX, pmouseY, 1);
		}
		else {
		}

		if(this.on_screen_message != null){
			//fill(100, 255, 190, 50);
			fill(50, 120, 140, 120);
			rect(50,50, this.text_box_width, this.text_box_height);
			fill(255, 255, 190);
			text(this.on_screen_message, 66, 58, 10000, 10000);
		}

	}

	public void asexualReproduction(){
		long elapsed = this.stopwatch.getElapsedTimeSecs();
		String log = "";
		if(elapsed == this.asexual_reproduction_constant){
			this.stopwatch.reset();
			float r = this.random(10);
			if(this.chance_asexual_repro <= r){
				Vehicle v = Genetics.produceVehicleAsexually(this.sel_method, this.n_for_sel, this.vehicleVector, this.sim.log);
				if(v == null){
					return;
				}

				ProcessingVehicle pv = new ProcessingVehicle(this, v, this.random(this.width),
						this.random(this.height), this.random(PI), axle, v.getName().hashCode(),
						this.pairedMating, this.perishable_vehicles);
				this.vehicleVector.add(pv);
				log += "Adding new vehicle to simulation.\nVehicle " + v.getName() + " added successfully!";
				if(this.num_vehicles >= 100){
					log+= "\nToo many vehicles in simulation. Oldest vehicle moving out.\nVehicle " + 
					this.vehicleVector.elementAt(0).getName() + " has moved out!";
					this.vehicleVector.removeElementAt(0);
				}
				this.sim.log.addToLog(log);
			}
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
		if(this.update_on_screen == 1){
			if(this.curr_on_screen != null){
				this.on_screen_message = this.curr_on_screen.toString();
			}
		}
		else if(this.update_on_screen == 2){

		}
		else this.on_screen_message = null;
	}

	public void updateTextBox(String message){
		String[] st = message.split("\n");
		if(st.length == 1){
			this.text_box_height = 22;
			this.text_box_width = message.length() * 10;
			return;
		}
		int depth = 0, width = st[0].length();
		for(int i = 1; i < st.length; i++){
			if(st[i].length() > width){
				width = st[i].length();
			}
			depth++;
		}
		if(this.update_on_screen == 1){
			this.text_box_height = depth * 16;
		}
		else{
			this.text_box_height = (depth + 1)* 20;
		}
		this.text_box_width = width * 10;
	}

	public void checkMouse(float x, float y, int button){
		float xPos, yPos, axle;
		boolean on_screen = false;
		int range;
		if(button == 1){
			ProcessingVehicle v;
			for(int i = 0; i < this.num_vehicles; i++){
				v = this.vehicleVector.elementAt(i);
				xPos = v.x;
				yPos = v.y;
				axle = v.axle;
				if((x <= xPos + axle && y <= yPos + axle) && (x >= xPos - axle && y <= yPos + axle) &&
						(x <= xPos + axle && y >= yPos - axle) && (x >= xPos - axle && y >= yPos - axle)){
					this.curr_on_screen = v;
					this.update_on_screen = button;
					on_screen = true;
					this.updateTextBox(this.curr_on_screen.toString());
					this.updateOnScreenMessage();
				}
			}
			if(!on_screen){
				this.on_screen_message = null;
				this.curr_on_screen = null;
			}
			return;
		}
		else{
			String message = "";
			ProcessingEnviroElement ee;
			DecimalFormat df = new DecimalFormat("#.##");
			for(int i = 0; i < this.num_sources; i++){
				ee = this.elementVector.elementAt(i);
				xPos = ee.xPos;
				yPos = ee.yPos;
				range = ee.getRadius();
				if((x <= xPos + range && y <= yPos + range) && (x >= xPos - range && y <= yPos + range) &&
						(x <= xPos + range && y >= yPos - range) && (x >= xPos - range && y >= yPos - range)){					
					message += "Point : (" + df.format(xPos) + "," + df.format(yPos) + ") : " + ee.getName() + " : "  + ee.getStrength() + "\n";
					on_screen = true;
				}
			}
			this.update_on_screen = button;
			this.updateTextBox(message);
			if(!on_screen){
				this.on_screen_message = null;
				this.curr_on_screen = null;
			}
			else{
				this.on_screen_message = message;
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

