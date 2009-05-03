package vehicles.processing;

import processing.core.*;
import java.util.Iterator;
import vehicles.vehicle.MemoryUnit;
import vehicles.environment.Point;
import vehicles.processing.ProcessingEnviroElement;

/**
 *
 * @author Niall O'Hara, Shaun Gray
 */
public class Sensor implements PConstants {

	PApplet parent; // The parent PApplet that we will render ourselves onto
	float x, y; //position relative to the whole frame
	float maxReading;
	float sense;
	float defaultSense = 0.2f;
	int power, water, light, heat;

	public Sensor(PApplet p, float x, float y, int pow, int wat, int light, int heat) {
		parent = p;
		this.x = x;
		this.y = y;
		maxReading = 1;
		this.power = pow;
		this.water = wat;
		this.light = light;
		this.heat = heat;

	}

	public Sensor(PApplet p, float x, float y) {
		parent = p;
		this.x = x;
		this.y = y;
		maxReading = 1;
	}

	public void setLocation(float x, float y) { //place the sensor in a certain place
		this.x = x;
		this.y = y;
	}


	float getSense(MemoryUnit memu) {

		//This makes the sensors look freakin' awesome
		SimulatonEngine p = (SimulatonEngine) parent;
		//float sum = parent.red( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		//sum += parent.green( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		//sum += parent.blue( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		//sum = (false) ? sum : 1-sum;
		//sense = (false) ? p.nonlinear( sum, maxReading ) : 1-sum;

		//Now deal with moving it getting sense at a point
		ProcessingEnviroElement temp;
		float total_intensity = 0, temp_intensity = 0;
		float water_intensity = 0, power_intensity = 0, light_intensity = 0, heat_intensity = 0;
		boolean water=false, power=false, light=false, heat=false;
		int size = p.elementVector.size();
		for(int i = 0; i < size; i++){
			temp = p.elementVector.elementAt(i);
			temp_intensity = temp.getIntensityAtPoint(x, y);
			if(temp_intensity != 0){
				switch(temp.getType()){
				case ProcessingEnviroElement.PowerSource:
					power_intensity += temp_intensity;
					if(!power){
						power = true;
					}
					//total_intensity += ((float)this.power); 
					break;
				case ProcessingEnviroElement.HeatSource:
					heat_intensity += temp_intensity;
					if(!heat){
						heat = true;
					}
					//total_intensity += ((float)this.heat);
					break;
				case ProcessingEnviroElement.LightSource:
					light_intensity += temp_intensity;
					if(!light){
						light = true;
					}
					//total_intensity += ((float)this.light);
					break;
				case ProcessingEnviroElement.WaterSource:
					water_intensity += temp_intensity;
					if(!water){
						water = true;
					}
					//total_intensity += ((float)this.water);
					break;
				}
			}
		}
		if(light){
			if(this.light != 0){
				total_intensity += light_intensity / this.light;
			}
		}
		if(water){
			if(this.water != 0){
				total_intensity += water_intensity / this.water;
			}
		}
		if(power){
			if(this.power != 0){
				total_intensity += power_intensity / this.power;
			}
		}
		if(heat){
			if(this.heat != 0){
				total_intensity += heat_intensity / this.heat;
			}
		}
		//if(total_intensity != 0) System.out.println("Total Intensity: " + total_intensity);
		sense = Math.abs(total_intensity);
		return total_intensity;
	}

	float getSense(boolean found, float maxSpeed, float aggression, float p_red, float p_green, float p_blue, MemoryUnit memu) {
		SimulatonEngine p = (SimulatonEngine) parent;
		float sum = parent.red( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.green( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.blue( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum = (found) ? sum : 1-sum;
		sense = (false) ? p.nonlinear( sum, maxReading ) : 1-sum;
		//if nothing sensed, then look towards memory to know where to go
		if (sense == 0) {
			Point[] points = memu.getPoints();
			int size = points.length;
			if(size == 0){
				return this.parent.random(0.25f);
			}
			int ran = (int)this.parent.random(size);
			int x_point, y_point;
			x_point = (int)points[ran].getXpos();
			y_point = (int)points[ran].getYpos();
			sum = parent.red( p.ground.get(x_point, y_point) ) / 255.0f;
			sum += parent.green( p.ground.get(x_point, y_point) ) / 255.0f;
			sum += parent.blue( p.ground.get(x_point, y_point) ) / 255.0f;
			if(sense == 0){
				return this.parent.random(0.25f);
			}
		}
		return sense;
	}


	float getSenseRed() {
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		Iterator<ProcessingEnviroElement> elementIterator = engineParent.elementVector.iterator();
		ProcessingEnviroElement temp;
		float red_atPoint = 0.0f;
		while (elementIterator.hasNext()) {
			temp = elementIterator.next();
			red_atPoint += temp.getRedAtPoint(this.x, this.y);
		}
		return red_atPoint;
	}

	float getSenseGreen() {
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		Iterator<ProcessingEnviroElement> elementIterator = engineParent.elementVector.iterator();
		ProcessingEnviroElement temp;
		float red_atPoint = 0.0f;
		while (elementIterator.hasNext()) {
			temp = elementIterator.next();
			red_atPoint += temp.getGreenAtPoint(this.x, this.y);
		}
		return red_atPoint;
	}

	float getSenseBlue() {
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		Iterator<ProcessingEnviroElement> elementIterator = engineParent.elementVector.iterator();
		ProcessingEnviroElement temp;
		float red_atPoint = 0.0f;
		while (elementIterator.hasNext()) {
			temp = elementIterator.next();
			red_atPoint += temp.getBlueAtPoint(this.x, this.y);
		}
		return red_atPoint;
	}

	public void draw() { //just draw a graphical representation
		parent.fill(255);
		parent.ellipse(x, y, 20, 20);
		parent.fill(255 * sense, 100 * sense, 0);
		parent.ellipse(x, y, 4 + 9 * sense, 2 + 9 * sense);
		parent.fill(0);
		parent.ellipse(x, y, 4.5f * sense, 4.5f * sense);
	}
}
