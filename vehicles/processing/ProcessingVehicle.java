/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.processing;

import processing.core.*;
import vehicles.vehicle.*;
import vehicles.genetics.*;
import vehicles.simulation.SimulationLog;
import java.text.DecimalFormat;
import vehicles.util.StopWatch;

/**
 *A class for using vehicle objects in the actual simulation. This class allows one to access all of a vehicles
 *attributes whilst at the same time being able to draw and move a vehicle and do things such as reproduction
 * @author Niall O'Hara, Shaun Gray
 */
public class ProcessingVehicle extends Vehicle implements PConstants {

	float chance_to_mate = 0.2f; //this is out of ten - may change
	float last_storedX, last_storedY; //used for checking if a vehicle is stuck somehwere
	float time_speed; //used to change speed of a vehicle as per the ui slider in the simulator
	float max_speed, curr_max_speed, default_speed; //used for moving the vehicle
	float x, y, axle; //for drawing/moving
	float axleHalf, axleSquared; //derivatives of axle
	float angle; // direction of the ProcessingVehicle
	float wheel_diff, wheel_average; // the difference and average of the wheels' rotating speed
	Wheel wA, wB; // two wheels
	Sensor sA, sB; // two sensors
	int colorRed, colorGreen, colorBlue, id; //colours and unique id of the vehicle
	int interval = 2; //max time a vehicle is allowed be stuck somewhere
	PApplet parent; // The parent PApplet that we will render ourselves onto
	float max_battery, curr_battery; //the current and max batteries as floats
	boolean canDie, pairedMating, dead = false, remember = false; //booleans for mating, death and memory
	public StopWatch stopwatch, samePos_watch; //stopwatches for chekcing time alive and if it's stuck

	/*
	 * This constructor takes a vehicle as a parameter
	 * Means that all the vehicle methods are now avaialble within processing
	 * 
	 */
	public ProcessingVehicle(PApplet p, Vehicle v, float x, float y, float angle, float axle_length, int id, boolean pairedM, boolean d) {

		super(v);
		this.parent = p;
		this.x = x;
		this.y = y;
		this.colorRed = v.getVehicleColourRed();
		this.colorGreen = v.getVehicleColourGreen();
		this.colorBlue = v.getVehicleColourBlue();
		this.angle = angle;
		this.id = id;
		this.max_speed = v.getMotorStrength()/10;
		this.default_speed = this.max_speed / 20;
		this.max_battery = this.getMaxBatteryCapacity();
		this.curr_battery = this.getCurrentBatteryCapacity();
		this.canDie = d;
		this.pairedMating = pairedM;

		this.stopwatch = new StopWatch();
		this.samePos_watch = new StopWatch();
		this.stopwatch.start();
		this.samePos_watch.start();

		this.time_speed = 100;

		axle = axle_length;
		axleHalf = axle / 2;
		axleSquared = axle * axle;

		wA = new Wheel(parent, x + axleHalf * PApplet.cos(angle - HALF_PI), y + axleHalf * PApplet.sin(angle - HALF_PI), 5, 0, this.max_speed);
		wB = new Wheel(parent, x + axleHalf * PApplet.cos(angle + HALF_PI), y + axleHalf * PApplet.sin(angle + HALF_PI), 5, 0, this.max_speed);

		sA = new Sensor(parent, x + axle * PApplet.cos(angle - HALF_PI / 1.5f), y + axle * PApplet.sin(angle - HALF_PI / 1.5f), 
				this.getLeftSensorPower(), this.getLeftSensorWater(), this.getLeftSensorLight(), this.getLeftSensorHeat());
		sB = new Sensor(parent, x + axle * PApplet.cos(angle + HALF_PI / 1.5f), y + axle * PApplet.sin(angle + HALF_PI / 1.5f),
				this.getRightSensorPower(), this.getRightSensorWater(), this.getRightSensorLight(), this.getRightSensorHeat());

	}

	public ProcessingVehicle(PApplet p, float x, float y, float angle, float axle_length, int r, int g, int b, float m) { 

		this.parent = p;
		this.x = x;
		this.y = y;
		this.colorRed = r;
		this.colorGreen = g;
		this.colorBlue = b;
		this.angle = angle;
		this.max_speed = m;
		this.curr_max_speed = max_speed;

		axle = axle_length;
		axleHalf = axle / 2;
		axleSquared = axle * axle;

		wA = new Wheel(parent, x + axleHalf * PApplet.cos(angle - HALF_PI), y + axleHalf * PApplet.sin(angle - HALF_PI), 5, 0, this.max_speed);
		wB = new Wheel(parent, x + axleHalf * PApplet.cos(angle + HALF_PI), y + axleHalf * PApplet.sin(angle + HALF_PI), 5, 0, this.max_speed);

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

		float ang;
		float sense1, sense2;
		checkPos(true); //check the vehicles position
		/* Get the sense for left and right sensors.
		 * Left sensor drives right wheel
		 * Right sensor drives left wheel
		 */
		sense1 = sB.getSense(this.mu);
		sense2 = sA.getSense(this.mu);
		setLeftSpeed(this.default_speed + sense1);
		setRightSpeed(this.default_speed + sense2);

		/*Update the position of the vehicle*/
		wheel_diff = wA.d - wB.d;
		wheel_average = (wA.d + wB.d) / 2;
		angle += wheel_diff / axle;

		x += (PApplet.cos(angle) * wheel_average);
		y += (PApplet.sin(angle) * wheel_average);

		/*After moving the vehicle, check if it has collided with and vehicles or element */
		checkCollisionVehicles();
		checkCollisionElements();

		/* Update the poisiton of the wheels */
		ang = angle - HALF_PI;

		wA.x = x + axleHalf * PApplet.cos(ang);
		wA.y = y + axleHalf * PApplet.sin(ang);

		ang = angle + HALF_PI;

		wB.x = x + axleHalf * PApplet.cos(ang);
		wB.y = y + axleHalf * PApplet.sin(ang);

		/* Update the positiojn of the sensors */
		ang = angle - HALF_PI / 2;

		sA.x = x + axle * PApplet.cos(ang);
		sA.y = y + axle * PApplet.sin(ang);

		ang = angle + HALF_PI / 2;

		sB.x = x + axle * PApplet.cos(ang);
		sB.y = y + axle * PApplet.sin(ang);

		/* If the vehicle can dir, deplete it's battery */
		if(canDie){
			depleteBatt();
		}
	}

	/**
	 * Method to update the chance of mating
	 * @param c The new chance of mating.
	 */
	public void updateChanceOfMating(float c){
		this.chance_to_mate = c;
	}

	/**
	 * Method to deplete a vehicle battery based on the speed of the simulation
	 *
	 */
	public void depleteBatt(){
		this.curr_battery -= 0.005 * (this.time_speed / 100);
		this.checkBattery();
	}

	/**
	 * Method to check the vehciles battery 
	 * If it has depleetd, the vehicles dies. 
	 * Also makes sure a vehicle batery cannot become over charged
	 *
	 */
	public void checkBattery(){
		if(this.curr_battery <= 0){
			SimulatonEngine engineParent = (SimulatonEngine) parent;
			this.die();
			engineParent.sim.log.addToLog("Vehicle: " + this.getName() + " perished when it's battery ran to 0.0");
			return;
		}
		if(this.curr_battery > this.max_battery){
			this.curr_battery = this.max_battery;
		}
	}

	/**
	 * Method to get the current battery of this vehicle as a float
	 * @return The cureent battery of this vehicle as a float
	 */
	public float getCurrbatt(){
		return this.curr_battery;
	}

	/**
	 * Method to get the maximum battery of this vehicle as a float
	 * @return Maximum battery as a float
	 */
	public float getMaxBatt(){
		return this.max_battery;
	}

	/**
	 * Methos to get the unique id of this vehicle
	 * @return The unique id of this vehicle
	 */
	public int getId() {
		return id;
	}

	/**
	 * A movement method used in preview windows where sensors are not needed to move
	 *
	 */
	public void moveWithoutSensor() {

		float ang;
		checkPos(false);

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

	/**
	 * A method to check the position of a vehicle.
	 * If it is stuck in one place for more than this.interval seconds, move it.
	 * If it has been running along a border for more than this.interval seconds, change the vehicle angle
	 * @param z A boolean used for differnet movement methods to call this function differently 
	 */
	void checkPos(boolean z) {
		if(z){
			long elapsed = this.samePos_watch.getElapsedTimeSecs();
			if(elapsed >= this.interval){
				this.samePos_watch.reset();
				if((this.sA.x - 8 <= 0 || this.sA.x + 8 >= parent.width || this.sB.x  - 8 <= 0 || this.sB.x + 8 >= parent.width ||
						this.sA.y - 8 <= 0 || this.sA.y + 8 >= parent.height || this.sB.y - 8 <= 0 || this.sB.y +8 >= parent.height) ||
						((x <= this.last_storedX + this.axle && y <= this.last_storedY + this.axle) && (x >= this.last_storedX - this.axle && 
								y <= this.last_storedY + this.axle)	&& (x <= this.last_storedX + this.axle && y >= this.last_storedY - this.axle) &&
								(x >= this.last_storedX - this.axle && y >= this.last_storedY - this.axle))){
					if(this.parent.random(10) <= 4){
						this.wB.setSpeed((this.wB.getAngleSpeed() * 1.1f) + 0.5f);
					}
					else{
						this.wA.setSpeed((this.wA.getAngleSpeed() * 1.1f) + 0.5f);
					}
					if(this.parent.random(10) <= 4){
						this.angle += this.parent.random(HALF_PI, PI);
					}
					else{
						this.angle -= this.parent.random(HALF_PI, PI);
					}
				}
			}
			if(elapsed % 2 == 0){
				this.last_storedX = this.x;
				this.last_storedY = this.y;
			}
		}
		/* Makes sure the vehicle cannot drive off the edge of the environment */
		x = PApplet.max(axle + 5, PApplet.min(parent.width - axle - 5, x));
		y = PApplet.max(axle + 5, PApplet.min(parent.height - axle - 5, y));

	}

	/**
	 * Method that checks if a vehicle has collided with an element.
	 * If the vehicle is within axle distance of the center of an element, add it to memory.
	 * Otherwise deplete battery or add battery based on the intenisty of the element and type
	 *
	 */
	void checkCollisionElements() { 
		float xPos, yPos, radius, intensity;
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		ProcessingEnviroElement temp;
		int size = engineParent.num_sources;
		for(int i = 0; i < size; i++){
			temp = engineParent.elementVector.elementAt(i);
			xPos = temp.xPos;
			yPos = temp.yPos;
			radius = temp.getRadius();
			//If the vehicle is within radial distance of the element
			if((x <= xPos + radius && y <= yPos + radius) && (x >= xPos - radius && y <= yPos + radius) &&
					(x <= xPos + radius && y >= yPos - radius) && (x >= xPos - radius && y >= yPos - radius)){
				//and the vehicle is within axle distance of the vehicle add to memory
				if((x <= xPos + axle && y <= yPos + axle) && (x >= xPos - axle && y <= yPos + axle) &&
						(x <= xPos + axle && y >= yPos - axle) && (x >= xPos - axle && y >= yPos - axle)){
					this.addElementToMemory(engineParent.elementVector.elementAt(i), engineParent.sim.log);
				}
				/*if the vehicle can perish, damage or heal battery */
				if(this.canDie){
					intensity = temp.getIntensityAtPoint(x, y);
					switch(temp.getType()){
					case ProcessingEnviroElement.WaterSource:
						this.curr_battery -= (intensity * 0.01)/100 * this.time_speed;
						break;
					case ProcessingEnviroElement.PowerSource:
						this.curr_battery += (intensity * 0.01)/100 * this.time_speed;
						break;
					case ProcessingEnviroElement.HeatSource:
						this.curr_battery -= (intensity * 0.02)/100 * this.time_speed;
						break;
					}
					/*Check the battery, if the vehicle has died, return..no point checking collision with other elements */
					this.checkBattery();
					if(this.dead){
						return;
					}
				}
			}
		}
	}

	/**
	 * Method to check if two vehicles are touching.
	 * Move them if they are..
	 * Also, if paired mating is on, mate them if they are compatible
	 * If not, deplete the batteries based on agrresion and battery
	 *
	 */
	void checkCollisionVehicles() { 
		float dx, dy, da;
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		ProcessingVehicle temp;
		int size = engineParent.num_vehicles;
		for(int i = 0; i < size; i++){
			try{
				temp = engineParent.vehicleVector.elementAt(i);
				if (temp.getId() != id) {
					dx = x - temp.x;
					dy = y - temp.y;
					if (dx * dx + dy * dy < axleSquared) {
						if(this.pairedMating){
							//System.out.println("Vehicles can mate...");
							double my_fitness = this.getFitness();
							double their_fitness = temp.getFitness();
							if((their_fitness >= my_fitness - (my_fitness * 0.1)) ||(their_fitness <= my_fitness + (my_fitness * 0.1)) ){
								float r = this.parent.random(10);
								if(r <= chance_to_mate){ 
									this.mate(temp);
									this.depleteBatt();
									this.checkBattery();
									temp.depleteBatt();
									temp.checkBattery();
									size = engineParent.num_vehicles;
								}
							}
						}
						else{
							float batt_to_steal = temp.curr_battery * ((float)this.aggression / 10);
							temp.curr_battery = temp.curr_battery - batt_to_steal;
							temp.checkBattery();
							this.curr_battery = this.curr_battery + batt_to_steal;
							if(this.curr_battery > this.max_battery){
								this.curr_battery = this.max_battery;
							}
							engineParent.sim.log.addToLog("Vehicle " + this.getName() + " stole " +
									batt_to_steal + "energy from Vehicle " + temp.getName() +
									"\nVehicle " + this.getName() + " current battery charge is now " + this.curr_battery);
							size = engineParent.num_vehicles;
						}
						da = PApplet.atan2(dy, dx);
						x = x + PApplet.cos(da) * axleHalf;
						y = y + PApplet.sin(da) * axleHalf;
						//do the actual move that avoids the collision
						temp.x = temp.x + PApplet.cos(da + PI) * axleHalf;
						temp.y = temp.y + PApplet.sin(da + PI) * axleHalf;
					}
				}
			}
			catch(Exception e){
				//e.printStackTrace();
				return;
			}
			//update the number of vehicles
			size = engineParent.num_vehicles;
		}
	}

	/**
	 * Used to mate two vehicles
	 * @param other The other vehicle to mate with
	 */
	//TODO need to implement something here so that vehicles canot mate over and over..they need to be stopped for x time
	private void mate(ProcessingVehicle other){
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		SimulationLog s = engineParent.sim.log;
		String log = "";
		Vehicle v = Genetics.pairedMating(this, other, s);
		int axle = 10;
		ProcessingVehicle pv = new ProcessingVehicle(this.parent, v, this.parent.random(this.parent.width),
				this.parent.random(this.parent.height), this.parent.random(PI), axle, v.getName().hashCode(),
				this.pairedMating, this.canDie);

		if(v != null){
			log += "Adding new vehicle to simulation.\nVehicle " + v.getName() + " added successfully!";
			engineParent.vehicleVector.add(pv);
		}
		//Stops the vehicles killing everything, this wil change to be user defined.
		if(engineParent.vehicleVector.size() >= 100){
			pv = engineParent.vehicleVector.elementAt(0);
			log+= "\nToo many vehicles in simulation. Oldest vehicle moving out.\nVehicle " + pv.getName() + " has moved out!";
			pv.die();
		}
		s.addToLog(log);
	}

	/**
	 * Kill a vehicle, remove it from the simulation
	 *
	 */
	public void die(){
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		engineParent.vehicleVector.remove(this);
		this.dead = true;
	}

	/**
	 * Update the colour of this vehicle
	 * @param p_red Red colour
	 * @param p_green Green colour
	 * @param p_blue Blue colour
	 */
	public void updateColor(int p_red, int p_green, int p_blue) {
		this.colorRed = p_red;
		this.colorGreen = p_green;
		this.colorBlue = p_blue;
	}

	/*Set the speed of the left wheel */
	public void setLeftSpeed(float ang_speed) {
		wA.setSpeed(ang_speed * 1.0f);
	}

	/*Set the speed of the right wheel */
	public void setRightSpeed(float ang_speed) {
		wB.setSpeed(ang_speed * 1.0f);
	}

	/*Update the speed the vehicle should be moving at*/
	public void updateSpeed_ofTime(float percent){
		this.time_speed = percent;
		this.curr_max_speed = (percent * (this.max_speed / 100))/5;
		this.wA.setMaxSpeed(this.curr_max_speed);
		this.wB.setMaxSpeed(this.curr_max_speed);
	}


	/* Return this vehicle as a string, that is..all of the details of this vehicle */
	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		double alive = (this.stopwatch.getElapsedTimeDoubleSecs() / 100) * this.time_speed;
		return "Name: " + this.vehicleName + "\nMotor Strength: " + this.getMotorStrength() + "\nMax Displacement(Movement): " + df.format(this.max_speed) +
		"\nCurrent Maximum Displacement: " + df.format(this.curr_max_speed) + 		"\nLeft Wheel Speed: " + this.wA.getAngleSpeed() +
		"\nRight Wheel Speed: " + this.wB.getAngleSpeed() + "\nMax Battery: " + df.format(this.max_battery) + "\nCurr Battery: " +
		df.format(this.curr_battery) + "\nAggression: " + this.aggression + "\nItems In Memory: " + this.mu.numItems() + 
		"\nCo-ordinates: (" + df.format(this.x) + "," + df.format(this.y) + ")" + "\nRight Sensor Values" + "\nPower: " + this.getRightSensorPower() +
		"\nHeat: " + this.getRightSensorHeat() + "\nLight: " + this.getRightSensorLight() + "\nWater: " + this.getRightSensorWater() +
		"\nLeft Sensor Values" + "\nPower: " + this.getLeftSensorPower() +"\nHeat: " + this.getLeftSensorHeat() +
		"\nLight: " + this.getLeftSensorLight() + "\nWater: " + this.getLeftSensorWater() + "\nAlive for: " + df.format(alive) + " seconds.";
	}
}
