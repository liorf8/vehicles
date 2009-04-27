package vehicles.processing;
import processing.core.*;

/**
 *
 * @author Niall O'Hara
 */
public class VehicleAppearance extends PApplet {
	
	Robot robot;
     @Override
     public void setup() {
         size(400, 400);
         background(100);
         robot = new Robot(this, width / 2, height / 2, random(PI), 10, 155, 155, 155);
         noLoop();
     }

     @Override
     public void draw() {
    	 robot.setLeftSpeed(0);
         robot.setRightSpeed(0);
         robot.draw();
         robot.move();
     }

     
     public void updateRed(int r){
     	this.robot.updateRed(r);
     }
     
     public void updateGreen(int g){
     	this.robot.updateRed(g);
     }
     
     public void updateBlue(int b){
     	this.robot.updateBlue(b);
     }


 }
