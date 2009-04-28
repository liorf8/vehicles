package vehicles.processing;
import processing.core.*;
import vehicles.environment.*;

import java.awt.Color;
import java.util.Vector;

import java.util.Iterator;

/**
 *
 * @author Niall O'Hara, Shuan Gray
 */
public class EnvironmentLayout extends PApplet {
	int w, h;
	Vector<ProcessingEnviroElement> ee;
	ElementBrush eb;

	public void setWidth_and_Height(int h, int w){
		this.w = w;
		this.h = h;
	}

	public void setBrush(EnvironmentElement brush){
		eb = new ElementBrush(brush);
	}

	@Override
	public void setup() {
		ee = new Vector<ProcessingEnviroElement>();
		this.setWidth_and_Height(200, 200);
		// original setup code here ...
		size(w, h);
		background(100);
		// prevent thread from starving everything else
		smooth();
		cursor(CROSS);
	}

	@Override
	public void draw() {
		size(w,h); //lets the window be redrawn to a different size
		background(Color.BLACK.getRGB());
		stroke(Color.RED.getRGB());
		strokeWeight(4);
		fill(Color.GRAY.getRGB());
		rect(0,0,width,height);
		noFill();
		noStroke();
		Iterator<ProcessingEnviroElement> it = ee.iterator();
		while(it.hasNext()){
			it.next().draw();
		}
		noLoop();

		// drawing code goes here
	}

	@Override
	public void mouseClicked() {
		if(mouseX <= this.w && mouseY <= this.h){
			eb.setPosition(new Point(mouseX,mouseY));
			ProcessingEnviroElement toSet = new ProcessingEnviroElement((PApplet)this,eb.getCurrentlySelected(),0);
			System.err.println("ok to add");
			System.out.println(toSet.tostring());
			this.ee.add(toSet);
		}
		redraw();
	}

	public void addElement(int xPos, int yPos, int type, int radius, int intensity){
		EnvironmentElement e = new EnvironmentElement();
		e.setRadius(radius);
		e.setStrength(intensity);
		e.setType(type);

		switch(type){
		case EnvironmentElement.WaterSource:
			e.setName("Water Source");
			break;
		case EnvironmentElement.HeatSource:
			e.setName("Heat Source");
			break;
		case EnvironmentElement.PowerSource:
			e.setName("Power Source");
			break;
		case EnvironmentElement.LightSource:
			e.setName("Light Source");
			break;
		}

		int len = this.ee.size();
		Point p = new Point(xPos, yPos);
		for(int i = 0; i < len; i++){
			if(this.ee.elementAt(i).comparePoint(p)){
				this.ee.removeElementAt(i);
				break;
			}
		}
		e.setPosition(new Point(xPos, yPos));
		//this.ee.add(e);
	}
}
