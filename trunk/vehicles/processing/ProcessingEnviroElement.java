/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.processing;

import processing.core.*;
import vehicles.environment.*;

/**
 *
 * @author Shaun Gray
 */
public class ProcessingEnviroElement extends EnvironmentElement implements PConstants {

	int colorRed, colorGreen, colorBlue, id; //maybe we can make this by doing a hash on the vehicle's name?
	PApplet parent; // The parent PApplet that we will render ourselves onto
	float xPos, yPos;

	/*
	 * This constructor takes a vehicle as a parameter
	 * Means that all the vehicle methods are now avaialble within processing
	 *
	 */
	public ProcessingEnviroElement(PApplet p, EnvironmentElement ee, int id) {

		super(ee);
		this.parent = p;
		this.setColor();
		this.id = id;
		this.xPos = (float) this.getXpos();
		this.yPos = (float) this.getYpos();
	}

	public ProcessingEnviroElement(ProcessingEnviroElement other){
		super(other);
		this.setColor();
	}
	public int getId() {
		return id;
	}

	public ProcessingEnviroElement(PApplet p, float x, float y, int intensity, int max_range, int type, int id) { //constructor

		this.xPos = x;
		this.yPos = y;
		this.id = id;
		this.type = type;
		this.strength = intensity;
		this.setPosition(new Point(x, y));
		this.radius = max_range;
		this.parent = p;
		this.setColor();

	}

	public void draw() { //simply draw a representaion of the element

		/*parent.fill(this.colorRed, this.colorGreen, this.colorBlue);
        /*
        int alpha = this.strength;
        for(float d = 0.0f; d < this.radius; d++){
        //System.out.println("Alpha: " + alpha);
        this.parent.stroke(this.colorRed, this.colorBlue, this.colorGreen, 0);
        this.parent.ellipse(this.xPos, this.yPos, d, d);
        alpha -- ;

        }
		 */
	}

	public void editorDraw() { //simply draw a representaion of the element, for in the editor
		this.parent.fill(this.colorRed, this.colorGreen, this.colorBlue);
		this.parent.ellipse((float)this.getXpos(), (float)this.getYpos(), radius, radius);
	}

	public void previewDraw(int x, int y) {
		this.parent.fill(this.colorRed, this.colorGreen, this.colorBlue);
		this.parent.ellipse(x, y, radius, radius);
	}

	public void setColor() {
		switch (this.type) {
		case EnvironmentElement.HeatSource:
			this.colorRed = 255;
			this.colorGreen = 0;
			this.colorBlue = 0;
			break;
		case EnvironmentElement.PowerSource:
			this.colorRed = 0;
			this.colorGreen = 255;
			this.colorBlue = 0;
			break;
		case EnvironmentElement.LightSource:
			this.colorRed = 255;
			this.colorGreen = 255;
			this.colorBlue = 255;
			break;
		case EnvironmentElement.WaterSource:
			this.colorRed = 0;
			this.colorGreen = 0;
			this.colorBlue = 255;
			break;
		}
	}

	public float getIntensityAtPoint(float x, float y) {

		float d = PApplet.dist(this.xPos, this.yPos, x, y);
		if (d > this.radius) {
			return 0.0f;
		} else {
			float diff = (float) this.radius - d;
			return (0.01f * ((diff / (float) this.radius) * (float) this.strength));
		}
	}

	public float getAplhaAtPoint(float x, float y) {
		float d = PApplet.dist(this.xPos, this.yPos, x, y);
		if (d > this.radius) {
			return 0.0f;
		} else {
			float diff = (float) this.radius - d;
			float alpha = (float) this.strength;
			float units = alpha / (float) this.radius;
			for (float i = 0; i < diff; i++) {
				alpha -= units;
			}
			return alpha;
		}
	}

	public float getRedAtPoint(float x, float y) {

		float d = PApplet.dist(this.xPos, this.yPos, x, y);
		if (d > this.radius) {
			return 0.0f;
		} else {
			float diff = (float) this.radius - d;
			return (float) this.colorRed * (0.10f * ((diff / (float) this.radius) * (float) this.strength));
		}
	}

	public float getGreenAtPoint(float x, float y) {

		float d = PApplet.dist(this.xPos, this.yPos, x, y);
		if (d > this.radius) {
			return 0.0f;
		} else {
			float diff = (float) this.radius - d;
			return (float) this.colorGreen * (0.10f * ((diff / (float) this.radius) * (float) this.strength));
		}
	}

	public float getBlueAtPoint(float x, float y) {

		float d = PApplet.dist(this.xPos, this.yPos, x, y);
		if (d > this.radius) {
			return 0.0f;
		} else {
			float diff = (float) this.radius - d;
			return (float) this.colorBlue * (0.9f * ((diff / (float) this.radius) * (float) this.strength));
		}
	}

	public String toString() {
		return (this.xPos + " " + this.yPos + " " + this.strength + " " + this.radius);
	}

	public int getColorBlue() {
		return colorBlue;
	}

	public int getColorGreen() {
		return colorGreen;
	}

	public int getColorRed() {
		return colorRed;
	}
}