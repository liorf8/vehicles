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
         size(100, 100);
         background(100);
         robot = new Robot(this, width / 2, height / 2, random(PI), 10, 155, 155, 155);
     }

     @Override
     public void draw() {
        background(0);
        robot.setLeftSpeed(0.2f);
        robot.setRightSpeed(0.1f);
        robot.draw();
        robot.move();
     }

     
    public void updateColor (int p_red, int p_green, int p_blue) {
        robot.updateColor(p_red, p_green, p_blue);
    }
}
