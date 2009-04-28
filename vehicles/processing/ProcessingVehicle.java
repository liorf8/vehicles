/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.processing;

import java.util.Iterator;
import java.util.Vector;

import com.mallardsoft.tuple.*;
import com.mallardsoft.tuple.Tuple;

import processing.core.*;
import vehicles.vehicle.*;

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

	/*
	 * This constructor takes a vehicle as a parameter
	 * Means that all the vehicle methods are now avaialble within processing
	 * 
	 */
	public ProcessingVehicle(PApplet p, Vehicle v, float x, float y, float angle, float axle_length, int id, float m) {

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

		axle = axle_length;
		axleHalf = axle / 2;
		axleSquared = axle * axle;

		wA = new Wheel(parent, x + axleHalf * PApplet.cos(angle - HALF_PI), y + axleHalf * PApplet.sin(angle - HALF_PI), 5, 0);
		wB = new Wheel(parent, x + axleHalf * PApplet.cos(angle + HALF_PI), y + axleHalf * PApplet.sin(angle + HALF_PI), 5, 0);

		sA = new Sensor(parent, x + axle * PApplet.cos(angle - HALF_PI / 1.5f), y + axle * PApplet.sin(angle - HALF_PI / 1.5f),
				this.getLeftSensorWater(), this.getLeftSensorPower(), this.getLeftSensorHeat(), this.getLeftSensorLight());
		sB = new Sensor(parent, x + axle * PApplet.cos(angle + HALF_PI / 1.5f), y + axle * PApplet.sin(angle + HALF_PI / 1.5f),
				this.getRightSensorWater(), this.getRightSensorPower(), this.getRightSensorHeat(), this.getRightSensorLight());

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

		axle = axle_length;
		axleHalf = axle / 2;
		axleSquared = axle * axle;

		wA = new Wheel(parent, x + axleHalf * PApplet.cos(angle - HALF_PI), y + axleHalf * PApplet.sin(angle - HALF_PI), 5, 0);
		wB = new Wheel(parent, x + axleHalf * PApplet.cos(angle + HALF_PI), y + axleHalf * PApplet.sin(angle + HALF_PI), 5, 0);

		sA = new Sensor(parent, x + axle * PApplet.cos(angle - HALF_PI / 1.5f), y + axle * PApplet.sin(angle - HALF_PI / 1.5f),
				this.getLeftSensorWater(), this.getLeftSensorPower(), this.getLeftSensorHeat(), this.getLeftSensorLight());
		sB = new Sensor(parent, x + axle * PApplet.cos(angle + HALF_PI / 1.5f), y + axle * PApplet.sin(angle + HALF_PI / 1.5f),
				this.getRightSensorWater(), this.getRightSensorPower(), this.getRightSensorHeat(), this.getRightSensorLight());

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

		//setLeftSpeed(1f);
		//setRightSpeed(1f);
		//this.setSpeed(sA, wB);
		//this.setSpeed(sB, wA);

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
		Iterator<ProcessingVehicle> vehicleIterator = engineParent.vehicleVector.iterator();
		while (vehicleIterator.hasNext()) {
			ProcessingVehicle temp = vehicleIterator.next();
			if (temp.getId() != id) {

				dx = x - temp.x;
				dy = y - temp.y;
				//if (abs(dx) <=axle && abs(dy)<=axle ) {
				if (dx * dx + dy * dy < axleSquared) {
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
