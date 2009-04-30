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
	int bufferTime;

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
	public void setEnvironment(Environment e){
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
		//	this.redraw();
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
		Iterator<ProcessingEnviroElement> it = ee.iterator();
		while(it.hasNext()){
			ProcessingEnviroElement curr = it.next();
			curr.editorDraw();
		}
		//print("Now have "+ee.size()+ " elements\n");
	}

	@Override
	public void mousePressed() {
		if(mouseX <= this.w && mouseY <= this.h  && eb != null && (frameCount - bufferTime > 2) ){

			ProcessingEnviroElement toSet = new ProcessingEnviroElement(eb.getPev());
			toSet.parent = this;
			toSet.setPosition(new Point(mouseX,mouseY));
			//System.out.println(toSet.tostring());
			this.ee.add(toSet);
			print("Now have "+ee.size()+ " elements\n");
			bufferTime = frameCount;
			redraw();
		}
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
		this.ee = null;
		this.ee = new Vector<ProcessingEnviroElement>();
	}
}
