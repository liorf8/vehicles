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
	ElementBrushPreview eb;
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

	}

	public void setWidth_and_Height(int w, int h){
		this.w = w;
		this.h = h;
		this.draw();
	}

	
	public void setEb(ElementBrushPreview b){
		this.eb = b;
	}

	@Override
	public void setup() {
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
			ProcessingEnviroElement curr = it.next();
			curr.editorDraw();
		}
		//updateGround(); // works, but too slow
		print("Now have "+ee.size()+ " elements\n");
	}

	@Override
	public void mouseClicked() {
		
		//System.out.println("mouseClicked() : using "+this.getBrush().getCurrentlySelected());

		if(mouseX <= this.w && mouseY <= this.h){
			ProcessingEnviroElement toSet = new ProcessingEnviroElement(eb.getPev());
			toSet.parent = this;
			toSet.setPosition(new Point(mouseX,mouseY));
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
	
	public void removeAllElements(){
		this.ee = new Vector<ProcessingEnviroElement>();
	}
}
