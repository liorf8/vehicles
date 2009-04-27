package vehicles.processing;

import processing.core.*;

/**
 *
 * @author Niall O'Hara
 */
public class Wheel implements PConstants {

    float x, y;
    float ang_speed, radius;
    float angle; //direction
    float d; //displacement, I think
    PApplet parent; // The parent PApplet that we will render ourselves onto

    public Wheel (PApplet p, float x, float y, float radius, float ang_speed) {

        parent = p;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.ang_speed = ang_speed;
        angle = 0;
        

        d = ang_speed * radius; //looks like displacement to me?
    }

    public void setSpeed(float ang_speed) {

        this.ang_speed = ang_speed;
        d = ang_speed * radius;
    }

    public void setSpeedChange(float inc) {
        ang_speed += inc;
        d = ang_speed * radius;
    }

    public void draw(float ainc, int dir) {
        angle += ang_speed;
        if (angle > TWO_PI) {
            angle -= TWO_PI;
        }
        float temp = PApplet.sin(angle) * 0.5f + ainc;

        parent.ellipse(x + PApplet.cos(temp) * 10 * dir, y + PApplet.sin(temp) * 10 * dir, 25, 25);
    }
}
