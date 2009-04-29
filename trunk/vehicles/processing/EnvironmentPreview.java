package vehicles.processing;
import java.util.Iterator;

import processing.core.*;
import vehicles.environment.Environment;
import vehicles.environment.EnvironmentElement;

/**
 *
 * @author Niall O'Hara, Karl Reid
 */
public class EnvironmentPreview extends PApplet {
	PImage ground; //background image
	Environment e;

	public EnvironmentPreview(Environment env){
		this.e = new Environment(env);
	}
	public void setEnvironment(Environment env){
		this.e = new Environment(env);
	}
	@Override
	public void setup() {
		size(e.getWidth(),e.getHeigth());
		ground = new PImage(width, height);
		image(ground, 0, 0);
	}

	@Override
	public void draw() {
		image(ground, 0, 0);
		updateGround();
	}
	/**
	 * Scale this applet to panel size
	 * @param x panel x size
	 * @param y panel y size
	 */
	public void updateSize(int x, int y){
		float scaleFactor = (float)this.width / (float) x ;
		for(int i = 0;i<e.getElements().size();i++){
			e.getElements().elementAt(i).
				setRadius((int)(e.getElements().elementAt(i).getRadius() / scaleFactor));
			e.getElements().elementAt(i).
				setXpos(e.getElements().elementAt(i).getXpos() / scaleFactor);
			e.getElements().elementAt(i).
				setYpos(e.getElements().elementAt(i).getYpos() / scaleFactor);
			e.getElements().elementAt(i).
				setStrength((int)(e.getElements().elementAt(i).getStrength() / scaleFactor));			
		}
		size(x,y);
	}

	
	public void updateGround() {
			float sum;
			int c, r, g, b;
			int px = 1;

			ground = new PImage(width, height);
			for (int i = 0; i < width; i += px) {
				for (int k = 0; k < height; k += px) { //process every pixel in the image

					sum = 0;
				r = 0;
				g = 0;
				b = 0;
				for (int m = 0; m < e.getElements().size(); m++) {
					ProcessingEnviroElement temp = 
							new ProcessingEnviroElement(this,e.getElements().elementAt(m),0);
					//pass this pixel's position
					sum += temp.getIntensityAtPoint(i, k);
					r += temp.getRedAtPoint(i, k);
					g += temp.getGreenAtPoint(i, k);
					b += temp.getBlueAtPoint(i, k);
					//sum up intensity of elementVector until it reachrs one
					if (sum >= 1) {
						break;
					}

				}

				c = (int) min(sum * 255, 255);


				for (int p = 0; p < px; p++) {
					for (int q = 0; q < px; q++) {
						//agh, horrible code
						ground.set(i + p, k + q,
								color(r, g, b / 8, c)); //r,g,b
					}
				}

				}
			}
			
		}

	}
