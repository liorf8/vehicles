package vehicles.processing;

import processing.core.*;

/**
 *
 * @author Niall O'Hara
 */
public class Sensor implements PConstants {

    float x, y; //position relative to the whole frame
    float maxReading;
    float sense;
    PApplet parent; // The parent PApplet that we will render ourselves onto

    public Sensor(PApplet p, float x, float y) {

        parent = p;
        this.x = x;
        this.y = y;
        maxReading = 1;
    }

    public void setLocation(float x, float y) { //place the sensor in a certain place
        this.x = x;
        this.y = y;
    }

    public void draw() { //just draw a graphical representation
        parent.fill(255);
        parent.ellipse(x, y, 20, 20);
        parent.fill(255 * sense, 100 * sense, 0);
        parent.ellipse(x, y, 4 + 12 * sense, 2 + 12 * sense);
        parent.fill(0);
        parent.ellipse(x, y, 6 * sense, 6 * sense);
    }
}
