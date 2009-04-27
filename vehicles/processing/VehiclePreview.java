package vehicles.processing;
import processing.core.*;
import vehicles.processing.SimulatonEngine.Sensor;
import vehicles.processing.SimulatonEngine.Wheel;

/**
 *
 * @author Niall O'Hara
 */
public class VehiclePreview extends PApplet {
	private int red, green, blue;
	
	public VehiclePreview(){
	}
	
     @Override
     public void setup() {
         // original setup code here ...
         size(400, 400);
         background(255);
         // prevent thread from starving everything else
         noLoop();
     }

     @Override
     public void draw() {
    	 fill(this.red, this.green, this.blue);
    	 rect(50, 50, 200, 200);
         // drawing code goes here
     }

     @Override
     public void mouseDragged() {
         // do something based on mouse movement
    	 updateRed(mouseX);
    	 updateGreen(mouseY);
    	 updateGreen(mouseX + mouseY);
        // line(mouseX, mouseY, pmouseX, pmouseY);
         // update the screen (run draw once)
         redraw();
     }
     
     
    public void updateRed(int new_red){
    	this.red = new_red;
    	redraw();
    }
    
    public void updateBlue(int new_blue){
    	this.blue = new_blue;
    	redraw();
    }
    
    public void updateGreen(int new_green){
    	this.green = new_green;
    	redraw();
    }
 }
















/*

*//**












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
            	/*Again, for the inverted sense mode, maybe can be taken out for simplification*//*
                setLeftSpeed(sB.getSense(false));
                setRightSpeed(sA.getSense(false));
            } else {  // love mode
                setLeftSpeed(sA.getSense(false)); //speed is determined by sensors, good
                setRightSpeed(sB.getSense(false));

            }


            // move
            
            Just update the vehicle's position and direction, this stuff won't need to be changed
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

        handy interfaces
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

    *//**
     * Robot's Wheel... customize the robots' style.
     * 
     * I think this class should be fine as is
     *
     *//*
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

    *//**
     * Robot's Sensor
     *//*
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
        
        
        *//**
         * Very very important method for getting a reading from the sensor
         * @param plus this is always false when called, maybe 
         * @return
         *//*
        float getSense(boolean plus) {
        	//get a value of the red-ness of the ground at our current position
            float sum = red(ground.get((int) x, (int) y)) / 255.0f;
            
            if(plus ==true){ //which it never seems to be
            	//sum = sum;
            }else{
            	sum = 1-sum;
            }
            //sum = (plus) ? sum : 1 - sum;
            
            This if statement is for handling the inverted sense, should be taken out 
             * and the true option taken
             
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















































*/