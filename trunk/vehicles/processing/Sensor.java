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


	float getSense(boolean found, float maxSpeed, float aggression, float p_red, float p_green, float p_blue) {
		if(found){
			return this.defaultSense;
		}
		SimulatonEngine engineParent = (SimulatonEngine) parent;
		Iterator<ProcessingEnviroElement> elementIterator = engineParent.elementVector.iterator();
		ProcessingEnviroElement temp;
		float intensity_atPoint, red_atPoint, green_atPoint, blue_atPoint, max_sense = 0;
		while (elementIterator.hasNext()) {
			temp = elementIterator.next();
			intensity_atPoint = temp.getIntensityAtPoint(this.x, this.y);
            red_atPoint = temp.getRedAtPoint(this.x, this.y);
            green_atPoint = temp.getGreenAtPoint(this.x, this.y);
            blue_atPoint = temp.getBlueAtPoint(this.x, this.y);
			if(intensity_atPoint > 0.0f | red_atPoint > 0.0f | green_atPoint > 0.0f | red_atPoint > 0.0f){
				max_sense += (intensity_atPoint/temp.getStrength()) * (maxSpeed / aggression) * 0.1f;
                max_sense += (red_atPoint/p_red) * (maxSpeed / aggression) * 0.1f;
                max_sense += (green_atPoint/p_green) * (maxSpeed / aggression)* 0.1f;
                max_sense += (blue_atPoint/p_blue) * (maxSpeed / aggression)* 0.1f;
			} else {
                max_sense = 0.1f;
            }
		}
		return max_sense;
		/*
		if(max_sense == 0.0f){
			return this.parent.random(maxSpeed*2) - maxSpeed;
		}
		else {
			//System.out.println("Returning speed of: " + (max_sense * (aggression * 0.02f)));
			if(aggression == 0){
				return max_sense;
			}
			return max_sense * (aggression * 0.02f);
		}
		*/
				
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
		parent.ellipse(x, y, 4 + 12 * sense, 2 + 12 * sense);
		parent.fill(0);
		parent.ellipse(x, y, 6 * sense, 6 * sense);
	}
}
