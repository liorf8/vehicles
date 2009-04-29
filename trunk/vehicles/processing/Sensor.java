package vehicles.processing;

import processing.core.*;
import java.util.Iterator;
import vehicles.vehicle.MemoryUnit;
import vehicles.environment.Point;

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


	float getSense(boolean found, float maxSpeed, float aggression, float p_red, float p_green, float p_blue, MemoryUnit memu) {
		SimulatonEngine p = (SimulatonEngine) parent;
		float sum = parent.red( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.green( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum += parent.blue( p.ground.get( (int)x, (int)y ) ) / 255.0f;
		sum = (found) ? sum : 1-sum;
		sense = (false) ? p.nonlinear( sum, maxReading ) : 1-sum;
		//if nothing sensed, then look towards memory to know where to go
		if (sense == 0) { 
			float distance, temp_dist;
			int closest_x, closest_y;
			Point[] points = memu.getPoints();
			Point temp = new Point(this.x, this.y);
			int size = points.length;
			if(size > 0){
				distance = points[0].getDistanceBetween(temp);
				closest_x = (int)points[0].getXpos();
				closest_y = (int)points[0].getYpos();
			}
			else {
				return 0.1f;
			}
			for(int i = 1; i < size; i++){
				temp_dist = points[i].getDistanceBetween(temp);
				if(temp_dist <= distance){
					distance = temp_dist;
					closest_x = (int)points[i].getXpos();
					closest_y = (int)points[i].getYpos();
				}
			}
			sum = parent.red( p.ground.get(closest_x, closest_y) ) / 255.0f;
			sum += parent.green( p.ground.get(closest_x, closest_y) ) / 255.0f;
			sum += parent.blue( p.ground.get(closest_x, closest_y) ) / 255.0f;
			sum = (found) ? sum : 1-sum;
			sense = (false) ? p.nonlinear( sum, maxReading ) : 1-sum;
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
