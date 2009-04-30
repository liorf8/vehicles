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

/*
 * 
 * Vehicles max spped is 10 % of motor strength. This can change via the ui slider
 * 
 * If two vehicles bump into each other, they each do a damge of 10% of their max battery to the other vehicle, 
 * gaiuning that for themselves
 * 
 * Heat will damage a battery by the intensity at the current point times 5% of the max_battery
 * 
 * Water will damage a battery by the intensity at the current point times  2.5% of the max_battery
 * 
 * Power will heal a battery by the intensity at the current point times  2.5% of the max_battery
 * 
 */
/**
 *
 * @author Niall O'Hara, Shaun Gray
 */
public class ProcessingVehicle extends Vehicle implements PConstants {

	float chance_to_mate = 0.2f; //this is out of ten
	float last_storedX, last_storedY;
	float time_speed;
	float max_speed, curr_max_speed;
	float x, y, axle;
	float axleHalf, axleSquared; //derivatives of axle
	float angle; // direction of the ProcessingVehicle
	float wheel_diff, wheel_average; // the difference and average of the wheels' rotating speed
	Wheel wA, wB; // two wheels
	Sensor sA, sB; // two sensors
	int colorRed, colorGreen, colorBlue, id; //maybe we can make this by doing a hash on the vehicle's name?
	int interval = 2;
	PApplet parent; // The parent PApplet that we will render ourselves onto
	float max_battery, curr_battery;
	boolean canDie, pairedMating, dead = false, remember = false;
	public StopWatch stopwatch, samePos_watch;
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

	public int getId() {
		return id;
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

		float ang;// = this.parent.random(10);
		checkBounds(true);
		//setLeftSpeed(sB.getSense(false, this.max_speed, this.aggression, this.colorRed, this.colorGreen, this.colorBlue, this.getMem()));
		//setRightSpeed(sA.getSense(false, this.max_speed, this.aggression, this.colorRed, this.colorGreen, this.colorBlue, this.getMem()));
		setLeftSpeed(sB.getSense(this.max_speed, this.aggression, this.mu));
		setRightSpeed((sA.getSense(this.max_speed, this.aggression, this.mu)));
		/*Just update the vehicle's position and direction, this stuff won't need to be changed*/
		wheel_diff = wA.d - wB.d;
		wheel_average = (wA.d + wB.d) / 2;
		angle += wheel_diff / axle;
		//angle += wheel_diff / axle;
		//x += this.time_speed * ((PApplet.cos(angle) * wheel_average)/85);
		//y += this.time_speed * ((PApplet.sin(angle) * wheel_average)/85);
		x += (PApplet.cos(angle) * wheel_average);
		y += (PApplet.sin(angle) * wheel_average);
		///x+= this.time_speed * (10/100);
		//y+= this.time_speed * (10/100);

		checkCollisionVehicles();
		checkCollisionElements();

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

		//this.checkSamePos();

		if(canDie){
			depleteBatt();
		}
	}

	public void updateChanceOfMating(float c){
		this.chance_to_mate = c;
	}

	public void depleteBatt(){
		this.curr_battery -= 0.005 * (this.time_speed / 100);
		this.checkBattery();
	}

	public void checkBattery(){
		if(this.curr_battery <= 0){
			SimulatonEngine engineParent = (SimulatonEngine) parent;
			engineParent.sim.log.addToLog("Vehicle " + this.getName() + " died.");
			this.die();
			engineParent.sim.log.addToLog("Vehicle: " + this.getName() + " perished when it's battery ran to 0.0");
			return;
		}
		if(this.curr_battery > this.max_battery){
			this.curr_battery = this.max_battery;
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
		checkBounds(false);

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

	void checkBounds(boolean z) {
		if(z){
			long elapsed = this.samePos_watch.getElapsedTimeSecs();
			if(elapsed >= this.interval){
				this.samePos_watch.reset();
				if(this.sA.x - 8 <= 0 || this.sA.x + 8 >= parent.width || this.sB.x  - 8 <= 0 || this.sB.x + 8 >= parent.width ||
						this.sA.y - 8 <= 0 || this.sA.y + 8 >= parent.height || this.sB.y - 8 <= 0 || this.sB.y +8 >= parent.height){
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
		}
		x = PApplet.max(axle + 5, PApplet.min(parent.width - axle - 5, x));
		y = PApplet.max(axle + 5, PApplet.min(parent.height - axle - 5, y));

	}

	void checkCollisionElements() { //if vehicles occupies exact same spot as element, add it to memory
		float xPos, yPos, radius, intensity;
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		ProcessingEnviroElement temp;
		int size = engineParent.num_sources;
		for(int i = 0; i < size; i++){
			temp = engineParent.elementVector.elementAt(i);
			xPos = temp.xPos;
			yPos = temp.yPos;
			radius = temp.getRadius();
			if((x <= xPos + radius && y <= yPos + radius) && (x >= xPos - radius && y <= yPos + radius) &&
					(x <= xPos + radius && y >= yPos - radius) && (x >= xPos - radius && y >= yPos - radius)){
				//add element to memory
				if((x <= xPos + axle && y <= yPos + axle) && (x >= xPos - axle && y <= yPos + axle) &&
						(x <= xPos + axle && y >= yPos - axle) && (x >= xPos - axle && y >= yPos - axle)){
					this.addElementToMemory(engineParent.elementVector.elementAt(i), engineParent.sim.log);
				}
				if(this.canDie){
					//do damage or heal
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
					this.checkBattery();
					if(this.dead){
						return;
					}
				}
			}
		}
	}

	void checkCollisionVehicles() { //if vehicles are occupying the same spot, move them 
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
							}
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
			size = engineParent.num_vehicles;
		}
	}

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
		//Stops the vehicles killing everything
		if(engineParent.vehicleVector.size() >= 100){
			pv = engineParent.vehicleVector.elementAt(0);
			log+= "\nToo many vehicles in simulation. Oldest vehicle moving out.\nVehicle " + pv.getName() + " has moved out!";
			pv.die();
		}
		s.addToLog(log);
	}

	public void die(){
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		engineParent.vehicleVector.remove(this);
		this.dead = true;
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

	public float getMaxSpeed(){
		return this.max_speed;
	}

	public void updateSpeed_ofTime(float percent){
		this.time_speed = percent;
		this.curr_max_speed = (percent * (this.max_speed / 100))/5;
		this.wA.setMaxSpeed(this.curr_max_speed);
		this.wB.setMaxSpeed(this.curr_max_speed);
	}


	public String toString(){
		DecimalFormat df = new DecimalFormat("#.##");
		double alive = (this.stopwatch.getElapsedTimeDoubleSecs() / 100) * this.time_speed;
		return "Name: " + this.vehicleName + "\nMotor Strength: " + this.getMotorStrength() + "\nMax Displacement(Movement): " + df.format(this.max_speed) +
		"\nCurrent Maximum Displacement: " + df.format(this.curr_max_speed) + 		"\nLeft Motor Turn Speed: " + this.wA.getAngleSpeed() +
		"\nRight Motor Turn Speed: " + this.wB.getAngleSpeed() + "\nMax Battery: " + df.format(this.max_battery) + "\nCurr Battery: " +
		df.format(this.curr_battery) + "\nAggression: " + this.aggression + "\nItems In Memory: " + this.mu.numItems() + 
		"\nCo-ordinates: (" + df.format(this.x) + "," + df.format(this.y) + ")" + "\nRight Sensor Values" + "\nPower: " + this.getRightSensorPower() +
		"\nHeat: " + this.getRightSensorHeat() + "\nLight: " + this.getRightSensorLight() + "\nWater: " + this.getRightSensorWater() +
		"\nLeft Sensor Values" + "\nPower: " + this.getLeftSensorPower() +"\nHeat: " + this.getLeftSensorHeat() +
		"\nLight: " + this.getLeftSensorLight() + "\nWater: " + this.getLeftSensorWater() + "\nAlive for: " + df.format(alive) + " seconds.";
	}
}
