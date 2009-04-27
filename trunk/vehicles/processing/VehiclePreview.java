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
        size(60, 50);
        background(0);
        robot = new Robot(this, width / 2, height / 2, random(PI), 10, 155, 155, 155);
        noLoop();
    }

    @Override
    public void draw() {
        robot.setLeftSpeed(10);
        robot.setRightSpeed(0.9f);
        robot.draw();
        robot.move();
    }
}