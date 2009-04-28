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
    float axleHalf, axleSquared; //derivatives of axle
    float angle; // direction of the Robot
    float wheel_diff, wheel_average; // the difference and average of the wheels' rotating speed
    Wheel wA, wB; // two wheels
    Sensor sA, sB; // two sensors
    int colorRed, colorGreen, colorBlue; //maybe we can make this by doing a hash on the vehicle's name?
    PApplet parent; // The parent PApplet that we will render ourselves onto

    public Robot(PApplet p, float x, float y, float angle, float axle_length, int r, int g, int b) { //constructor

        this.parent = p;
        this.x = x;
        this.y = y;
        this.colorRed = r;
        this.colorGreen = g;
        this.colorBlue = b;
        this.angle = angle;

        axle = axle_length;
        axleHalf = axle / 2;
        axleSquared = axle * axle;

        wA = new Wheel(parent, x + axleHalf * PApplet.cos(angle - HALF_PI), y + axleHalf * PApplet.sin(angle - HALF_PI), 5, 0);
        wB = new Wheel(parent, x + axleHalf * PApplet.cos(angle + HALF_PI), y + axleHalf * PApplet.sin(angle + HALF_PI), 5, 0);

        sA = new Sensor(parent, x + axle * PApplet.cos(angle - HALF_PI / 1.5f), y + axle * PApplet.sin(angle - HALF_PI / 1.5f));
        sB = new Sensor(parent, x + axle * PApplet.cos(angle + HALF_PI / 1.5f), y + axle * PApplet.sin(angle + HALF_PI / 1.5f));

    }

    public void draw() { //simply draw a representaion of the vehicle

        parent.fill(colorRed, colorGreen, colorBlue);
        wA.draw(angle, 1);
        wB.draw(angle - PI, -1);

        parent.ellipse(x, y, axle * 3, axle * 3);
        sA.draw();
        sB.draw();

    }

    public void move() {

        float ang;
        checkBounds();

        // move

        /*Just update the vehicle's position and direction, this stuff won't need to be changed*/
        wheel_diff = wA.d - wB.d;
        wheel_average = (wA.d + wB.d) / 2;
        angle += wheel_diff / axle;
        x += PApplet.cos(angle) * wheel_average;
        y += PApplet.sin(angle) * wheel_average;

        // wheels move
        ang = angle - HALF_PI;

        wA.x = x + axleHalf * PApplet.cos(ang);
        wA.y = y + axleHalf * PApplet.sin(ang);

        ang = angle + HALF_PI;

        wB.x = x + axleHalf * PApplet.cos(ang);
        wB.y = y + axleHalf * PApplet.sin(ang);

        // sensors move
        ang = angle - HALF_PI / 2;

        sA.x = x + axle * PApplet.cos(ang);
        sA.y = y + axle * PApplet.sin(ang);

        ang = angle + HALF_PI / 2;

        sB.x = x + axle * PApplet.cos(ang);
        sB.y = y + axle * PApplet.sin(ang);

    }

    public void checkBounds() {

        x = PApplet.max(axle + 5, PApplet.min(parent.width - axle - 5, x));
        y = PApplet.max(axle + 5, PApplet.min(parent.height - axle - 5, y));

    }

    public void updateColor(int p_red, int p_green, int p_blue) {
        this.colorRed = p_red;
        this.colorGreen = p_green;
        this.colorBlue = p_blue;
    }

    public void setLeftSpeed(float ang_speed) {
        wA.setSpeed(ang_speed * 1.0f);
    }

    public void setRightSpeed(float ang_speed) {
        wB.setSpeed(ang_speed * 1.0f);
    }

    public void changeLeftSpeed(float inc) {
        wA.setSpeedChange(inc);
    }

    public void changeRightSpeed(float inc) {
        wB.setSpeedChange(inc);
    }
    
    public void updateRed(int r){
    	this.colorRed= r;
    }
    
    public void updateGreen(int g){
    	this.colorGreen = g;
    }
    
    public void updateBlue(int b){
    	this.colorBlue= b;
    }
}
