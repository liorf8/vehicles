package vehicles.processing;

import processing.core.*;
import java.util.Iterator;
import java.util.Vector;

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
	int water, heat, light, power;

	public Sensor(PApplet p, float x, float y, int w, int pow, int h, int l) {
		parent = p;
		this.x = x;
		this.y = y;
		maxReading = 1;
		this.water = w;
		this.heat = h;
		this.light = l;
		this.power = pow;
	}

	public void setLocation(float x, float y) { //place the sensor in a certain place
		this.x = x;
		this.y = y;
	}

	public int getPowerDispos(){
		return this.power;
	}

	public int getWaterDispos(){
		return this.water;
	}

	public int getHeatDispos(){
		return this.heat;
	}

	public int getLightDispos(){
		return this.light;
	}


	float getSense(boolean found, float maxSpeed, float aggression) {
		if(found){
			return this.defaultSense;
		}
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		Iterator<ProcessingEnviroElement> elementIterator = engineParent.elementVector.iterator();
		ProcessingEnviroElement temp;
		float intensity_atPoint, max_sense = 0;
		while (elementIterator.hasNext()) {
			temp = elementIterator.next();
			intensity_atPoint = temp.getIntensityAtPoint(this.x, this.y);
			if(intensity_atPoint > 0.0f){
				max_sense += (intensity_atPoint/temp.getStrength()) * maxSpeed;
			}
		}
		if(max_sense == 0.0){
			return this.parent.random(maxSpeed);
		}
		else return max_sense * (aggression * 0.02f);
				
				/*
				
				
				
			}
		int c = engineParent.ground.get( (int)x, (int)y );
		int a=(c>>24)&0xFF;
		int r=(c>>16)&0xFF;
		int g=(c>>8 )&0xFF;
		int b=c&0xFF; 


		float sum = parent.red( engineParent.ground.get( (int)x, (int)y ) )/255.0f;
		sum = (plus) ? sum : 1-sum;
		sense = (true) ? engineParent.nonlinear( sum, maxReading ) : 1-sum;
		return sense;*/
	}


	public void draw() { //just draw a graphical representation
		parent.fill(255);
		parent.ellipse(x, y, 20, 20);
		parent.fill(255 * sense, 100 * sense, 0);
		parent.ellipse(x, y, 4 + 12 * sense, 2 + 12 * sense);
		parent.fill(0);
		parent.ellipse(x, y, 6 * sense, 6 * sense);
	}
}
