package vehicles.processing;
import processing.core.*;
import vehicles.environment.*;

import java.awt.Color;
import java.util.Vector;

/**
 *
 * @author Niall O'Hara, Shuan Gray
 */
public class EnvironmentLayout extends PApplet {
	int w, h;
	Vector<EnvironmentElement> ee;
	ElementBrush eb;
	
	public void setWidth_and_Height(int h, int w){
		this.w = w;
		this.h = h;
	}
	public void setBrush(ElementBrush brush){
		this.eb = brush;
	}
	
	@Override
	public void setup() {
		ee = new Vector<EnvironmentElement>();
		eb = new ElementBrush();
		this.setWidth_and_Height(640, 480);
		// original setup code here ...
		size(w, h);
		background(100);
		// prevent thread from starving everything else
		smooth();
		noLoop();
		cursor(CROSS);
	}

	@Override
	public void draw() {
		size(w,h); //lets the window be redrawn to a different size
		background(Color.BLACK.getRGB());
		
		// drawing code goes here
	}

	@Override
	public void mouseClicked() {
		EnvironmentElement toSet = eb.getCurrentlySelected();
		toSet.setPosition(new Point(mouseX,mouseY));
		System.out.println(toSet.toString());
		this.ee.add(toSet);
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
		this.ee.add(e);
	}
}
