/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles.processing;

import processing.core.*;

/**
 *
 * @author Niall O'Hara
 */
public class Robot implements PConstants {

    float x, y, axle;
    float half_axle, axleSq; //derivatives of axle
    float angle; // direction of the Robot
    float wheel_diff, wheel_average; // the difference and average of the wheels' rotating speed
    Wheel wA, wB; // two wheels
    Sensor sA, sB; // two sensors
    int r, gr, b, id; //maybe we can make this by doing a hash on the vehicle's name?
    PApplet parent; // The parent PApplet that we will render ourselves onto

    public Robot (PApplet p, float x, float y, float angle, float axle_length, int r, int g, int b) { //constructor

        parent = p;
        this.x = x;
        this.y = y;
        this.r = r;
        this.gr = g;
        this.b = b;
        this.angle = angle;
        //this.id = id;
        axle = axle_length;
        half_axle = axle / 2;
        axleSq = axle * axle;

        wA = new Wheel(parent, x + half_axle * PApplet.cos(angle - HALF_PI), y + half_axle * PApplet.sin(angle - HALF_PI), 5, 0);
        wB = new Wheel(parent, x + half_axle * PApplet.cos(angle + HALF_PI), y + half_axle * PApplet.sin(angle + HALF_PI), 5, 0);

        sA = new Sensor(parent, x + axle * PApplet.cos(angle - HALF_PI / 1.5f), y + axle * PApplet.sin(angle - HALF_PI / 1.5f));
        sB = new Sensor(parent, x + axle * PApplet.cos(angle + HALF_PI / 1.5f), y + axle * PApplet.sin(angle + HALF_PI / 1.5f));

    }

    public void draw() { //simply draw a representaion of the vehicle

        parent.fill(r, gr, b);
        wA.draw(angle, 1);
        wB.draw(angle - PI, -1);

        parent.ellipse(x, y, axle * 3, axle * 3);
        sA.draw();
        sB.draw();

    }

    public void move() {

        checkBounds();

        // move

        /*Just update the vehicle's position and direction, this stuff won't need to be changed*/
        wheel_diff = wA.d - wB.d;
        wheel_average = (wA.d + wB.d) / 2;
        angle += wheel_diff / axle;
        x += PApplet.cos(angle) * wheel_average;
        y += PApplet.sin(angle) * wheel_average;

        // wheels move
        //remember the wheels are not part of the vehicle image, only look that way,
        //	so have to move them separately
        float ang = angle - HALF_PI;

        wA.x = x + half_axle * PApplet.cos(ang);
        wA.y = y + half_axle * PApplet.sin(ang);

        ang = angle + HALF_PI;

        wB.x = x + half_axle * PApplet.cos(ang);
        wB.y = y + half_axle * PApplet.sin(ang);


        // sensors move
        ang = angle - HALF_PI / 2;

        sA.x = x + axle * PApplet.cos(ang);
        sA.y = y + axle * PApplet.sin(ang);

        ang = angle + HALF_PI / 2;

        sB.x = x + axle * PApplet.cos(ang);
        sB.y = y + axle * PApplet.sin(ang);



    }

    public void checkBounds() {

        //x = ( x<0 ) ? width+x : ((x>width) ? x-width : x );
        //y = ( y<0 ) ? height+y : ((y>height) ? y-height : y );

        x = PApplet.max(axle + 5, PApplet.min(parent.width - axle - 5, x));
        y = PApplet.max(axle + 5, PApplet.min(parent.height - axle - 5, y));

    }

    public void changeLeftSpeed(float inc) {
        wA.setSpeedChange(inc);
    }

    public void changeRightSpeed(float inc) {
        wB.setSpeedChange(inc);
    }
}
