package vehicles.processing;
import processing.core.*;

/**
 *
 * @author Niall O'Hara
 */
public class EnvironmentLayout extends PApplet {
	private int w  = 5, h = 5;

	public void updateWidthHeight(int h, int w){
		this.h = h;
		this.w = w;
	}
	
	@Override
	public void setup() {
		// original setup code here ...
		size(400, 400);
		background(100);
		// prevent thread from starving everything else
		noLoop();
	}

	@Override
	public void draw() {
		// drawing code goes here
		drawGrid();
	}

	@Override
	public void mouseDragged() {
		// do something based on mouse movement
		line(mouseX, mouseY, pmouseX, pmouseY);
		// update the screen (run draw once)
		redraw();
	}

	public void drawGrid(){
		float num_l_r = (float)width/(float)w;
		float num_u_d = (float)height/(float)h;
		float j = 0;
		for(float i = 0; i < w; i++){
			line(0, j, height, j);
			j+= num_l_r;
		}
		j = 0;
		for(float i = 0; i < h; i++){
			line(j, 0, j, width);
			j+= num_u_d;
		}
	}
}
