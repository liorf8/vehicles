package vehicles.processing;

import processing.core.*;
import java.util.Iterator;
import java.util.Vector;
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


	float getSense(float maxSpeed, float aggression, MemoryUnit memu) {
		SimulatonEngine p = (SimulatonEngine) parent;
		float sum = parent.red( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.green( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.blue( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum = (false) ? sum : 1-sum;
		sense = (false) ? p.nonlinear( sum, maxReading ) : 1-sum;
		ProcessingEnviroElement temp;
		float total_intensity = (aggression * 0.01f), temp_intensity;
		int size = p.elementVector.size();
		for(int i = 0; i < size; i++){
			temp = p.elementVector.elementAt(i);
			temp_intensity = temp.getIntensityAtPoint(x, y);
			switch(temp.getType()){
			case ProcessingEnviroElement.PowerSource:
				total_intensity += temp_intensity * ((float)this.power); 
				break;
			case ProcessingEnviroElement.HeatSource:
				total_intensity += temp_intensity * ((float)this.heat);
				break;
			case ProcessingEnviroElement.LightSource:
				total_intensity += temp_intensity * ((float)this.light);
				break;
			case ProcessingEnviroElement.WaterSource:
				total_intensity += temp_intensity * ((float)this.water);
				break;
			}
			System.out.println("Intensity: " + total_intensity);
		}
		if (total_intensity <= 0) {
			System.out.println("GEtting Num from Memory");
			Point[] points = memu.getPoints();
			size = points.length;
			if(size == 0){
				return this.parent.random(maxSpeed);
			}
			int ran1 = (int)this.parent.random(size);
			int ran2;
			int x_point, y_point;
			for(int i = 0; i < ran1; i++){
				ran2 = (int)this.parent.random(size);
				x_point = (int)points[ran2].getXpos();
				y_point = (int)points[ran2].getYpos();
				temp_intensity = (memu.getIntensityOfElementAt(x_point, y_point)) * 0.01f;
				switch(memu.getTypeOfElementAt(x_point, y_point)){
				case ProcessingEnviroElement.PowerSource:
					total_intensity += temp_intensity * ((float)this.power); 
					break;
				case ProcessingEnviroElement.HeatSource:
					total_intensity += temp_intensity * ((float)this.heat);
					break;
				case ProcessingEnviroElement.LightSource:
					total_intensity += temp_intensity * ((float)this.light);
					break;
				case ProcessingEnviroElement.WaterSource:
					total_intensity += temp_intensity * ((float)this.water);
					break;
				}
			}
			if(total_intensity <= 0){
				return this.parent.random(maxSpeed);
			}
		}
		total_intensity = total_intensity * this.parent.random(2);
		if(total_intensity > maxSpeed){
			total_intensity = maxSpeed;
		}
		sum = parent.red( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.green( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.blue( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum = (false) ? sum : 1-sum;
		sense = (false) ? p.nonlinear( sum, maxReading ) : 1-sum;
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
