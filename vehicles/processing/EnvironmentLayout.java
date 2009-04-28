package vehicles.processing;
import processing.core.*;
import vehicles.environment.Point;

/**
 *
 * @author Niall O'Hara
 */
public class EnvironmentLayout extends PApplet {
	private int w  = 5, h = 5;
	float grid_unit_x, grid_unit_y;

	public void updateWidthHeight(int h, int w){
		this.h = h;
		this.w = w;
		
		grid_unit_x = (float)width/(float)w;
		grid_unit_y = (float)height/(float)h;
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
		stroke(144, 144, 213);
		this.updateWidthHeight(6, 4);
		float j = 0;
		for(float i = 0; i < w; i++){
			line(0, j, height, j);
			j+= grid_unit_x;
		}
		j = 0;
		for(float i = 0; i < h; i++){
			line(j, 0, j, width);
			j+= grid_unit_y;
		}
	}
	
	public Point getRelativeGridPoint(int x, int y){
		int relX = 0, relY = 0;
		int temp;
		float temp_div;
		for(float i = 0; i < w; i++){
			if(i == x){
				break;
			}
			temp_div = x / grid_unit_x;
		}
		
		return new Point(3, 4);
	}
	
}
