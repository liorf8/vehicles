package vehicles.processing;
import processing.core.*;
import vehicles.environment.*;

import java.awt.Color;
import java.util.Vector;

import java.util.Iterator;

/**
 *
 * @author Niall O'Hara, Shuan Gray, Karl Reid
 */
@SuppressWarnings("serial")
public class EnvironmentLayout extends PApplet {
	int w, h;
	Vector<ProcessingEnviroElement> ee;
	ElementBrush eb;
	public EnvironmentLayout(){
		
	}
	/**
	 * Create a new preview from an environment object
	 * @param e the environment to load from
	 */
	public EnvironmentLayout(Environment e){

		ee = new Vector<ProcessingEnviroElement>();
		Iterator<EnvironmentElement> it = e.getElements().iterator();
		while(it.hasNext()){
			ProcessingEnviroElement temp = new ProcessingEnviroElement(this,it.next(),0);
			ee.add(temp);
		}
        w = e.getWidth();
        h = e.getHeigth();
        eb = new ElementBrush();

	}

	public void setWidth_and_Height(int w, int h){
		this.w = w;
		this.h = h;
		this.draw();
	}

	public ElementBrush getBrush(){
		return eb;
	}

	public void setBrush(EnvironmentElement brush){
		eb = new ElementBrush(brush);
	}

	@Override
	public void setup() {
		//ee = new Vector<ProcessingEnviroElement>();
		// original setup code here ...
		size(w, h);
		background(0);
		//ground = new PImage(width, height);

		//updateGround();
		cursor(CROSS);
		noLoop();
	}

	@Override
	public void draw() {
		size(w,h); //lets the window be redrawn to a different size
		background(0);
		//image(ground, 0, 0); //works, just too slow		
//		stroke(Color.RED.getRGB());
//		strokeWeight(4);
//		fill(Color.BLACK.getRGB());
//		rect(0,0,width,height);//draw a border - take out later
//		noFill();
//		noStroke();
		Iterator<ProcessingEnviroElement> it = ee.iterator();
		while(it.hasNext()){
			it.next().editorDraw();
		}
		//updateGround(); // works, but too slow
		//print("Now have "+ee.size()+ " elements\n");
	}

	@Override
	public void mouseClicked() {
		if(mouseX <= this.w && mouseY <= this.h){
			eb.getCurrentlySelected().setPosition(new Point(mouseX,mouseY));
			ProcessingEnviroElement toSet = new ProcessingEnviroElement((PApplet)this,eb.getCurrentlySelected(),0);
			System.out.println(toSet.tostring());
			this.ee.add(toSet);
			this.draw();
		}
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
	/**
	 * Get an array of the elements in this editing session 
	 * @return this array
	 */
	public EnvironmentElement[] getElements(){
		EnvironmentElement[] toReturn = new EnvironmentElement[ee.size()];
		Iterator<ProcessingEnviroElement> it = ee.iterator(); int i = 0;
		while(it.hasNext()){
			toReturn[i] = new EnvironmentElement(it.next());
			i++;
		}		
		return toReturn;		
	}
}
