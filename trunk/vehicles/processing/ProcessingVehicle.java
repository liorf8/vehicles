/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.processing;

import java.util.Iterator;


import processing.core.*;
import vehicles.vehicle.*;
import vehicles.genetics.*;
import vehicles.simulation.SimulationLog;

/**
 *
 * @author Niall O'Hara
 */
public class ProcessingVehicle extends Vehicle implements PConstants {

	float max_speed;
	float x, y, axle;
	float axleHalf, axleSquared; //derivatives of axle
	float angle; // direction of the ProcessingVehicle
	float wheel_diff, wheel_average; // the difference and average of the wheels' rotating speed
	Wheel wA, wB; // two wheels
	Sensor sA, sB; // two sensors
	int colorRed, colorGreen, colorBlue, id; //maybe we can make this by doing a hash on the vehicle's name?
	PApplet parent; // The parent PApplet that we will render ourselves onto
	float max_battery, curr_battery;
	boolean canDie, pairedMating;
	/*
	 * This constructor takes a vehicle as a parameter
	 * Means that all the vehicle methods are now avaialble within processing
	 * 
	 */
	public ProcessingVehicle(PApplet p, Vehicle v, float x, float y, float angle, float axle_length, int id, float m, boolean pairedM, boolean d) {

		super(v);
		this.parent = p;
		this.x = x;
		this.y = y;
		this.colorRed = v.getVehicleColourRed();
		this.colorGreen = v.getVehicleColourGreen();
		this.colorBlue = v.getVehicleColourBlue();
		this.angle = angle;
		this.id = id;
		this.max_speed = m;
		this.max_battery = this.getMaxBatteryCapacity();
		this.curr_battery = this.getCurrentBatteryCapacity();
		this.canDie = d;
		this.pairedMating = pairedM;

		axle = axle_length;
		axleHalf = axle / 2;
		axleSquared = axle * axle;

		wA = new Wheel(parent, x + axleHalf * PApplet.cos(angle - HALF_PI), y + axleHalf * PApplet.sin(angle - HALF_PI), 5, 0);
		wB = new Wheel(parent, x + axleHalf * PApplet.cos(angle + HALF_PI), y + axleHalf * PApplet.sin(angle + HALF_PI), 5, 0);

		sA = new Sensor(parent, x + axle * PApplet.cos(angle - HALF_PI / 1.5f), y + axle * PApplet.sin(angle - HALF_PI / 1.5f));
		sB = new Sensor(parent, x + axle * PApplet.cos(angle + HALF_PI / 1.5f), y + axle * PApplet.sin(angle + HALF_PI / 1.5f));

	}

	public int getId() {
		return id;
	}

	public ProcessingVehicle(){
		
	}
	
	public ProcessingVehicle(PApplet p, float x, float y, float angle, float axle_length, int r, int g, int b, float m) { //constructor

		this.parent = p;
		this.x = x;
		this.y = y;
		this.colorRed = r;
		this.colorGreen = g;
		this.colorBlue = b;
		this.angle = angle;
		this.max_speed = m;

		axle = axle_length;
		axleHalf = axle / 2;
		axleSquared = axle * axle;

		wA = new Wheel(parent, x + axleHalf * PApplet.cos(angle - HALF_PI), y + axleHalf * PApplet.sin(angle - HALF_PI), 5, 0);
		wB = new Wheel(parent, x + axleHalf * PApplet.cos(angle + HALF_PI), y + axleHalf * PApplet.sin(angle + HALF_PI), 5, 0);

		sA = new Sensor(parent, x + axle * PApplet.cos(angle - HALF_PI / 1.5f), y + axle * PApplet.sin(angle - HALF_PI / 1.5f));
		sB = new Sensor(parent, x + axle * PApplet.cos(angle + HALF_PI / 1.5f), y + axle * PApplet.sin(angle + HALF_PI / 1.5f));

	}

	public void draw() { //simply draw a representaion of the vehicle

		parent.fill(colorRed, colorGreen, colorBlue);
		wA.draw(angle, 1);
		wB.draw(angle - PI, -1);

		parent.ellipse(x, y, axle * 3, axle * 3);
		sA.draw();
		sB.draw();
	}

	public void move() {


		float ang;// = this.parent.random(10);
		checkBounds();


		setLeftSpeed(sB.getSense(false, this.max_speed, this.aggression));
		setRightSpeed(sA.getSense(false, this.max_speed, this.aggression));

		/*Just update the vehicle's position and direction, this stuff won't need to be changed*/
		wheel_diff = wA.d - wB.d;
		wheel_average = (wA.d + wB.d) / 2;
		angle += wheel_diff / axle;
		x += PApplet.cos(angle) * wheel_average;
		y += PApplet.sin(angle) * wheel_average;
		checkCollision();

		// wheels move
		ang = angle - HALF_PI;

		wA.x = x + axleHalf * PApplet.cos(ang);
		wA.y = y + axleHalf * PApplet.sin(ang);

		ang = angle + HALF_PI;

		wB.x = x + axleHalf * PApplet.cos(ang);
		wB.y = y + axleHalf * PApplet.sin(ang);

		// sensors move
		ang = angle - HALF_PI / 2;

		sA.x = x + axle * PApplet.cos(ang);
		sA.y = y + axle * PApplet.sin(ang);

		ang = angle + HALF_PI / 2;

		sB.x = x + axle * PApplet.cos(ang);
		sB.y = y + axle * PApplet.sin(ang);

		if(canDie){
			checkBattery();
		}
	}

	public void checkBattery(){
		this.curr_battery -= 0.05;
		if(this.curr_battery <= 0){
			SimulatonEngine engineParent = (SimulatonEngine) parent;
			engineParent.vehicleVector.remove(this);
			return;
		}
	}

	public float getCurrbatt(){
		return this.curr_battery;
	}
	
	public float getMaxBatt(){
		return this.max_battery;
	}
	
//	used in preview windows
	public void moveWithoutSensor() {

		float ang;
		checkBounds();

		// move

		/*Just update the vehicle's position and direction, this stuff won't need to be changed*/
		wheel_diff = wA.d - wB.d;
		wheel_average = (wA.d + wB.d) / 2;
		angle += wheel_diff / axle;
		x += PApplet.cos(angle) * wheel_average;
		y += PApplet.sin(angle) * wheel_average;

		// wheels move
		ang = angle - HALF_PI;

		wA.x = x + axleHalf * PApplet.cos(ang);
		wA.y = y + axleHalf * PApplet.sin(ang);

		ang = angle + HALF_PI;

		wB.x = x + axleHalf * PApplet.cos(ang);
		wB.y = y + axleHalf * PApplet.sin(ang);

		// sensors move
		ang = angle - HALF_PI / 2;

		sA.x = x + axle * PApplet.cos(ang);
		sA.y = y + axle * PApplet.sin(ang);

		ang = angle + HALF_PI / 2;

		sB.x = x + axle * PApplet.cos(ang);
		sB.y = y + axle * PApplet.sin(ang);

	}

	public void checkBounds() {

		x = PApplet.max(axle + 5, PApplet.min(parent.width - axle - 5, x));
		y = PApplet.max(axle + 5, PApplet.min(parent.height - axle - 5, y));

	}

	void checkCollision() { //if vehicles are occupying the same spot, move them

		float dx, dy, da;
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		ProcessingVehicle temp;
		int size = engineParent.vehicleVector.size();
		for(int i = 0; i < size; i++){
			temp = engineParent.vehicleVector.elementAt(i);
			if (temp.getId() != id) {
				dx = x - temp.x;
				dy = y - temp.y;
				//if (abs(dx) <=axle && abs(dy)<=axle ) {
				if (dx * dx + dy * dy < axleSquared) {
					if(this.pairedMating){
						System.out.println("Vehicles can mate...");
						double my_fitness = this.getFitness();
						double their_fitness = temp.getFitness();
						if((their_fitness >= my_fitness - (my_fitness * 0.1)) ||(their_fitness <= my_fitness + (my_fitness * 0.1)) ){
							float r = this.parent.random(10);
							if(r <= 2){
								System.out.println("Vehicles are mating ...");
								this.mate(temp);
							}
						}
					}
					da = PApplet.atan2(dy, dx);
					//angle = ;
					x = x + PApplet.cos(da) * axleHalf;
					y = y + PApplet.sin(da) * axleHalf;
					//do the actual move that avoids the collision
					temp.x = temp.x + PApplet.cos(da + PI) * axleHalf;
					temp.y = temp.y + PApplet.sin(da + PI) * axleHalf;
				}
			}
		}
	}

	private void mate(ProcessingVehicle other){
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		SimulationLog s = engineParent.sim.log;
		Vehicle v = Genetics.pairedMating(this, other, s);
		ProcessingVehicle pv = new ProcessingVehicle(this.parent, v, 400, 400, this.parent.random(PI), 10, (int)this.parent.random(100), 3, this.pairedMating, this.canDie);
		if(v != null){
			engineParent.vehicleVector.add(pv);
		}
	}
	
	public void updateColor(int p_red, int p_green, int p_blue) {
		this.colorRed = p_red;
		this.colorGreen = p_green;
		this.colorBlue = p_blue;
	}

	public void setLeftSpeed(float ang_speed) {
		wA.setSpeed(ang_speed * 1.0f);
	}

	public void setSpeed(Wheel w, float s){
		w.setSpeed(s);
	}

	public void setRightSpeed(float ang_speed) {
		wB.setSpeed(ang_speed * 1.0f);
	}

	public void changeLeftSpeed(float inc) {
		wA.setSpeedChange(inc);
	}

	public void changeRightSpeed(float inc) {
		wB.setSpeedChange(inc);
	}

	public void updateRed(int r) {
		this.colorRed = r;
	}

	public void updateGreen(int g) {
		this.colorGreen = g;
	}

	public void updateBlue(int b) {
		this.colorBlue = b;
	}
}
