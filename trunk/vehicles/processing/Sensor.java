package vehicles.processing;

import processing.core.*;
import java.util.Iterator;
import vehicles.vehicle.MemoryUnit;
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


	/**
	 * This method is used to get the sense at a point.
	 * The sense at the point is the combined total of the ratio of (each intenisty of en elementt type, summed up) to the disposition to that element
	 * @param memu A memory unit
	 * @return A float representing the sense at that point
	 */
	float getSense(MemoryUnit memu) {

		//This makes the sensors look freakin' awesome
		SimulatonEngine p = (SimulatonEngine) parent;
		float sum = parent.red( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.green( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.blue( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum = (false) ? sum : 1-sum;
		sense = (false) ? this.nonlinear( sum, maxReading ) : 1-sum;

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
					break;
				case ProcessingEnviroElement.HeatSource:
					heat_intensity += temp_intensity;
					if(!heat){
						heat = true;
					}
					break;
				case ProcessingEnviroElement.LightSource:
					light_intensity += temp_intensity;
					if(!light){
						light = true;
					}
					break;
				case ProcessingEnviroElement.WaterSource:
					water_intensity += temp_intensity;
					if(!water){
						water = true;
					}
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
		if(!light && !heat && !water && !power){
			//TODO Implement memory search here, old memory search algorithm was wrong
		}
		return total_intensity;
	}



	/**
	 * Get the sense at a point based on the amount of red at that point
	 * @return A float representing sense at a point based on colour red
	 */
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

	/**
	 * Get the sense at a point based on the amount of green at that point
	 * @return A float representing sense at a point based on colour green
	 */
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

	/**
	 * Get the sense at a point based on the amount of blue at that point
	 * @return A float representing sense at a point based on colour blue
	 */
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

	/**
	 * Method to draw a graphical representation of this sensor 
	 *
	 */
	public void draw() { 
		parent.fill(255);
		parent.ellipse(x, y, 20, 20);
		parent.fill(255 * sense, 100 * sense, 0);
		parent.ellipse(x, y, 4 + 9 * sense, 2 + 9 * sense);
		parent.fill(0);
		parent.ellipse(x, y, 4.5f * sense, 4.5f * sense);
	}

	private float nonlinear(float r, float rmax) {
		float f = (rmax - Math.min(r, rmax)) / rmax;
		return 0.5f - 0.5f * PApplet.cos(f * PI);
	}
}
