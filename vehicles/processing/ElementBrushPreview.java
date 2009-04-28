package vehicles.processing;

import java.awt.Color;

import processing.core.PApplet;

public class ElementBrushPreview extends PApplet{
	ElementBrush eb;
	ProcessingEnviroElement pev;	

	public ElementBrush getEb() {
		return eb;
	}
	public void setEb(ElementBrush eb) {
		this.eb = eb;
	}
	public ProcessingEnviroElement getPev() {
		return pev;
	}
	public void setPev(ProcessingEnviroElement pev) {
		this.pev = pev;

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
