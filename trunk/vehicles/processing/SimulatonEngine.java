package vehicles.processing;

import java.util.Iterator;
import java.util.Vector;

import processing.core.*;
import vehicles.environment.*;
import vehicles.simulation.*;
import vehicles.vehicle.*;

@SuppressWarnings("serial")
public class SimulatonEngine extends PApplet {
	
	Simulation sim; //simulation representing this
	Vector<Robot> robots; 
    Vector<Source> sources;
    Environment enviro;
    boolean mouseDown;
    PImage ground; //background image
    boolean specialSense = true;
    float move_speed = 0;
    int num_sources;
    int w, h;
    
    public float getMove_speed() {
        return move_speed;
    }

    public void setMove_speed(float move_speed) {
        this.move_speed = move_speed / PI;
    }

    int source_drag_id = -1;

    public SimulatonEngine(Simulation simu){
    	System.out.println("Engine received object "+ simu.getXmlLocation());
    	this.sim = simu;
    	Vector<EnvironmentElement> elements = simu.getEnvironment().getElements();
    	Vector<Vehicle> veh = simu.getVehicles();
    	int num_veh = veh.size();
    	
    	for(int i = 0; i < num_veh; i++){
    		this.robots.add(new Robot(i * (width / 10) + 20, height / 2, random(PI), 10, i));
    	}
    	
    	this.num_sources = elements.size();
    	enviro = this.sim.getEnvironment();
    	this.w = enviro.getWidth();
    	this.h = enviro.getHeigth();
    	   	
    	sources = new Vector<Source>();
    	
    	for(int i = 0; i < this.num_sources; i++){
    		EnvironmentElement curr = elements.elementAt(i); 
    		System.out.print(i + " : ");
    		sources.add(new Source(
    				(float)curr.getXpos(),
    				(float)curr.getYpos(),
    				(float)((float)curr.getStrength()/100.0f),
    				(float)curr.getRadius(),
    				curr.getName().hashCode(), //generate some id
    				curr.getType()));
    		print(sources.elementAt(i));
    	}
    	
    }

    // Processing Sketch Setup
    @Override
    public void setup() {
        size(w, h);
        noStroke();
        ground = new PImage(width, height);
        smooth();
        updateGround();
    }


    // Processing Sketch Main Loop
    @Override
    public void draw() {
        background(0,0,0);

        image(ground, 0, 0);

        fill(155);
        for (int i = 0; i < numOfLights; i++) {
            sources[i].draw();

        }
        /*
         * if(frameCount % some_constant == 0){
         * 		make vehicles evolve
         */
        for (int i = 0; i < numOfRobots; i++) {
            robots[i].move();
            robots[i].draw();
        }
    }

    /**
     * key press handler
     */
    @Override
    public void keyPressed() {

        if (key == 32) { // SPACEBAR

            specialSense = !specialSense;
            updateGround();
        }
        /*
         * we won't need this functionality , should take it out
         */
        // num of lights
        if (key >= '1' && key <= '5') {
            numOfLights = 5 - ('5' - key);
            updateGround();
        }
    }

    private double distBetweenPoints(Point a, Point b){
    	double x1 = a.getXpos();
    	double y1 = a.getYpos();
    	double x2 = b.getXpos();
    	double y2 = b.getYpos();
    	double dist = Math.sqrt((Math.pow((y2 - y1), 2) + Math.pow((x2 - x1), 2)));
    	return dist;
    }
    
    @Override
    public void mousePressed() {
        mouseDown = true;
    }

    @Override
    public void mouseReleased() {
        mouseDown = false;
        updateGround();
    }

    /**
     * update the light source on the ground
     * 
     * This seems to be for the functionality of inverting the sense of the environment
     *  elements, so the vehicles are afraid of them -- only partly. It actually draws
     *  the sources
     */
    void updateGround() {

        float sum;
        int c;
        int px = 1;

        ground = new PImage(width, height);
        for (int i = 0; i < width; i += px) {
            for (int k = 0; k < height; k += px) { //process every pixel in the image

                sum = 0;
                for (int m = 0; m < numOfLights; m++) {
                	
                	//pass this pixel's position
                    sum += sources[m].getReading(i, k, true); 
                    //sum up intensity of sources until it reachrs one
                    if (sum >= 1) {
                        break;
                    }

                }

                c = (int) min(sum * 255, 255);


                for (int p = 0; p < px; p++) {
                    for (int q = 0; q < px; q++) {
                    	//agh, horrible code
                        ground.set(i + p, k + q,
                        		color(c , c, c / 8 )); //r,g,b
                    }
                }
            }
        }

    }

    float nonlinear(float r, float rmax) {
        float f = (rmax - Math.min(r, rmax)) / rmax;
        return 0.5f - 0.5f * cos(f * PI);
    }

    // Inner Classes ---------------
    /**
     * Robot class
     */
    class Robot {

        float x, y, axle;
        float half_axle, axleSq; //derivatives of axle
        float angle; // direction of the Robot
        float wheel_diff, wheel_average; // the difference and average of the wheels' rotating speed
        Wheel wA, wB; // two wheels
        Sensor sA, sB; // two sensors
        int id; //maybe we can make this by doing a hash on the vehicle's name?

        Robot(float x, float y, float angle, float axle_length, int id) { //constructor

            this.x = x;
            this.y = y;
            this.angle = angle;
            this.id = id;
            axle = axle_length;
            half_axle = axle / 2;
            axleSq = axle * axle;

            wA = new Wheel(x + half_axle * cos(angle - HALF_PI), y + half_axle * sin(angle - HALF_PI), 5, 0);
            wB = new Wheel(x + half_axle * cos(angle + HALF_PI), y + half_axle * sin(angle + HALF_PI), 5, 0);

            sA = new Sensor(x + axle * cos(angle - HALF_PI / 1.5f), y + axle * sin(angle - HALF_PI / 1.5f));
            sB = new Sensor(x + axle * cos(angle + HALF_PI / 1.5f), y + axle * sin(angle + HALF_PI / 1.5f));

        }

        void draw() { //simply draw a representaion of the vehicle
        	
        	//Commented out lines draw the vehicle "nose"
            //fill(55, 55, 0);
            //triangle(sA.x, sA.y, sB.x, sB.y, x + cos(angle) * axle * 2f, y + sin(angle) * axle * 2f);

            fill(id * 20, 120, id * 20);
            wA.draw(angle, 1);
            wB.draw(angle - PI, -1);

            ellipse(x, y, axle * 3, axle * 3);
            sA.draw();
            sB.draw();

        }

        void move() {

            checkBounds();


            // check sensor
            if (specialSense) { // special taste mode
            	/*Again, for the inverted sense mode, maybe can be taken out for simplification*/
                setLeftSpeed(sB.getSense(false));
                setRightSpeed(sA.getSense(false));
            } else {  // love mode
                setLeftSpeed(sA.getSense(false)); //speed is determined by sensors, good
                setRightSpeed(sB.getSense(false));

            }


            // move
            
            /*Just update the vehicle's position and direction, this stuff won't need to be changed*/
            wheel_diff = wA.d - wB.d;
            wheel_average = (wA.d + wB.d) / 2;
            angle += wheel_diff / axle;
            x += cos(angle) * wheel_average;
            y += sin(angle) * wheel_average;
            checkCollision();

            // wheels move
            //remember the wheels are not part of the vehicle image, only look that way,
            //	so have to move them separately
            float ang = angle - HALF_PI;

            wA.x = x + half_axle * cos(ang);
            wA.y = y + half_axle * sin(ang);

            ang = angle + HALF_PI;

            wB.x = x + half_axle * cos(ang);
            wB.y = y + half_axle * sin(ang);


            // sensors move
            ang = angle - HALF_PI / 2;

            sA.x = x + axle * cos(ang);
            sA.y = y + axle * sin(ang);

            ang = angle + HALF_PI / 2;

            sB.x = x + axle * cos(ang);
            sB.y = y + axle * sin(ang);



        }

        void checkBounds() {

            //x = ( x<0 ) ? width+x : ((x>width) ? x-width : x );
            //y = ( y<0 ) ? height+y : ((y>height) ? y-height : y );

            x = max(axle + 5, min(width - axle - 5, x));
            y = max(axle + 5, min(height - axle - 5, y));

        }

        void checkCollision() { //if vehicles are occupying the same spot, move them

            float dx, dy, da;
            for (int i = 0; i < numOfRobots; i++) {
                if (i != id) {

                    dx = x - robots[i].x;
                    dy = y - robots[i].y;
                    //if (abs(dx) <=axle && abs(dy)<=axle ) {
                    if (dx * dx + dy * dy < axleSq) {
                        da = atan2(dy, dx);
                        //angle = ;
                        x = x + cos(da) * half_axle;
                        y = y + sin(da) * half_axle;
                        //do the actual move that avoids the collision
                        robots[i].x = robots[i].x + cos(da + PI) * half_axle;
                        robots[i].y = robots[i].y + sin(da + PI) * half_axle;
                    }

                //}
                }
            }
        }

        /*handy interfaces*/
        void setLeftSpeed(float ang_speed) {
            wA.setSpeed(ang_speed * move_speed);
        }

        void setRightSpeed(float ang_speed) {
            wB.setSpeed(ang_speed * move_speed);
        }

        void changeLeftSpeed(float inc) {
            wA.setSpeedChange(inc);
        }

        void changeRightSpeed(float inc) {
            wB.setSpeedChange(inc);
        }
    }

    /**
     * Robot's Wheel... customize the robots' style.
     * 
     * I think this class should be fine as is
     *
     */
    class Wheel {

        float x, y;
        float ang_speed, radius; 
        float angle; //direction
        float d; //displacement, I think

        Wheel(float x, float y, float radius, float ang_speed) {

            this.x = x;
            this.y = y;
            this.radius = radius;
            this.ang_speed = ang_speed;
            angle = 0;

            d = ang_speed * radius; //looks like displacement to me?
        }

        void setSpeed(float ang_speed) {

            this.ang_speed = ang_speed;
            d = ang_speed * radius;
        }

        void setSpeedChange(float inc) {
            ang_speed += inc;
            d = ang_speed * radius;
        }

        void draw(float ainc, int dir) {
            angle += ang_speed;
            if (angle > TWO_PI) {
                angle -= TWO_PI;
            }
            float temp = sin(angle) * 0.5f + ainc;

            ellipse(x + cos(temp) * 10 * dir, y + sin(temp) * 10 * dir, 25, 25);
        }
    }

    /**
     * Robot's Sensor
     */
    class Sensor {

        float x, y; //position relative to the whole frame
        float maxReading;
        float sense;

        Sensor(float x, float y) {
            this.x = x;
            this.y = y;
            maxReading = 1;
        }

        void setLocation(float x, float y) { //place the sensor in a certain place
            this.x = x;
            this.y = y;
        }
        
        
        /**
         * Very very important method for getting a reading from the sensor
         * @param plus this is always false when called, maybe 
         * @return
         */
        float getSense(boolean plus) {
        	//get a value of the red-ness of the ground at our current position
            float sum = red(ground.get((int) x, (int) y)) / 255.0f;
            
            if(plus ==true){ //which it never seems to be
            	//sum = sum;
            }else{
            	sum = 1-sum;
            }
            //sum = (plus) ? sum : 1 - sum;
            
            /*This if statement is for handling the inverted sense, should be taken out 
             * and the true option taken
             */
            if(specialSense == true){ 
            	sense = nonlinear(sum,maxReading);
            }else{
            	sense = 1-sense;
            }
            //sense = (specialSense) ? nonlinear(sum, maxReading) : 1 - sum;
            return sense;
        }

        void draw() { //just draw a graphical representation
        	fill(255);
            ellipse(x, y, 20, 20);
            fill(255 * sense, 100 * sense, 0);
            ellipse(x, y, 4 + 12 * sense, 2 + 12 * sense);
            fill(0);
            ellipse(x, y, 6 * sense, 6 * sense);
        }
    }

    /**
     * Light Source class
     */
    class Source {

        float x, y;
        float intensity; // between 0 to 1
        float max_radius;
        //boolean dragging = true; //so taking this functionaloty out
        int id;
        int type; //enumeration values are in EnvironmentElement

        Source(float x, float y, float intensity, float max_radius, int id, int type) {
            this.x = x;
            this.y = y;
            this.intensity = intensity;
            this.max_radius = max_radius;
            this.id = id;
            this.type = type;
        }

        void setLocation(float x, float y) { //place the source somewhere
            this.x = x;
            this.y = y;
        }

        void draw() {

           // if (id < numOfLights) {
             //   checkCollision();
                /*
                // dragging?
                int range = 80;
                
                if (mouseDown && mouseX > x - range && mouseX < x + range && mouseY > y - range && mouseY < y + range) {
                    if (source_drag_id == -1) {
                        dragging = true;
                        source_drag_id = id;
                    }
                }

                if (!mouseDown) {
                    dragging = false;
                    source_drag_id = -1;
                }

                if (dragging) {
                    x = mouseX;
                    y = mouseY;
                    updateGround();
                }
*/
           // }
        }

        void checkCollision() {

            float dx, dy, da;
            for (int i = 0; i < sources.length; i++) {
                if (i != id) {

                    dx = x - sources[i].x;
                    dy = y - sources[i].y;

                    if (dx * dx + dy * dy < max_radius) {
                        da = atan2(dy, dx);
                        sources[i].x = sources[i].x + cos(da + PI) * 5;
                        sources[i].y = sources[i].y + sin(da + PI) * 5;
                    }
                }
            }
        }

        float getReading(float tx, float ty, boolean plus) {

            float d = dist(tx, ty, x, y);
            if (d >= max_radius) {
                return ((plus) ? 0 : 1);
            }
            d = 1 - nonlinear(d, max_radius);

            return ((plus) ? 1 - d : d);
        }
    
        public String toString(){
        	return(this.x + " " + this.y + " " + this.intensity + " " + this.max_radius);
        }
        
        
        
    }
}

