package vehicles.processing;

import java.awt.Color;

import processing.core.PApplet;
import vehicles.environment.EnvironmentElement;

public class ElementBrushPreview extends PApplet{
	ProcessingEnviroElement pev;	

	public ProcessingEnviroElement getPev() {
		return pev;
	}
	public void setPev(EnvironmentElement pev) {
		this.pev = new ProcessingEnviroElement(this,pev,0);

	}
	public void setup(){
		try{
			this.pev = new ProcessingEnviroElement(this,eb.getCurrentlySelected(),0);
		}catch(NullPointerException e){}
		size(eb.getCurrentlySelected().getRadius() + 10, eb.getCurrentlySelected().getRadius() + 10);
		background(Color.BLACK.getRGB());
		smooth();
	}
	public void draw(){
		try{
			this.pev = new ProcessingEnviroElement(this,eb.getCurrentlySelected(),0);
			size(eb.getCurrentlySelected().getRadius() + 10, eb.getCurrentlySelected().getRadius() + 10);
			pev.previewDraw(width /2, height /2);
		}catch(NullPointerException e){}
		
	}


}
