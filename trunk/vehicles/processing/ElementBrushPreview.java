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
			size(pev.getRadius() + 10,pev.getRadius() + 10);
		}catch(NullPointerException e){}
		background(Color.BLACK.getRGB());
		smooth();
	}
	public void draw(){
		try{
			size(pev.getRadius() + 10,pev.getRadius() + 10);
			pev.previewDraw(width /2, height /2);
		}catch(NullPointerException e){}

	}


}
