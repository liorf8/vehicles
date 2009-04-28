package vehicles.processing;
import processing.core.*;
import vehicles.environment.*;
import java.util.Vector;

/**
 *
 * @author Niall O'Hara
 */
public class EnvironmentLayout extends PApplet {
	int w, h;
	Vector<EnvironmentElement> ee;
	
	public void setWidth_and_Height(int h, int w){
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void setup() {
		ee = new Vector<EnvironmentElement>();
		setWidth_and_Height(100, 400);
		// original setup code here ...
		size(w, h);
		background(100);
		// prevent thread from starving everything else
		noLoop();
	}

	@Override
	public void draw() {
		// drawing code goes here
	}

	@Override
	public void mouseDragged() {
		// do something based on mouse movement
		line(mouseX, mouseY, pmouseX, pmouseY);
		// update the screen (run draw once)
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
