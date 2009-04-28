package vehicles.processing;

import processing.core.*;

/**
 *
 * @author Niall O'Hara
 */
public class VehiclePreview extends PApplet {
    Robot robot;

    @Override
    public void setup() {
        background(0);
        robot = new Robot(this, 60, 60, random(PI), 10, 155, 155, 155);
        smooth();
    }

    @Override
    public void draw() {
        background(0);
        robot.setLeftSpeed(0.2f);
        robot.setRightSpeed(0.17f);
        robot.move();
        robot.draw();
    }

    public void updateColor (int p_red, int p_green, int p_blue) {
        robot.updateColor(p_red, p_green, p_blue);
    }
    
    public void updateSize (int x, int y) {
         size(x,y);
    }
}