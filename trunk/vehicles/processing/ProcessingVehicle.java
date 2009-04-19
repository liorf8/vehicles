/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vehicles.processing;
import processing.core.*;



@SuppressWarnings("serial")
public class ProcessingVehicle extends PApplet{

	/*
	 * This is a Processing sketch.
	 * See http://processing.org to learn more about Processing
	 */

	// Processing Code Start
	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


	Robot[] robots;
	Source[] sources;
	boolean mouseDown;
	PImage ground;
	boolean specialSense = true;
	int numOfLights = 5;
	int numOfRobots = 3;
	float move_speed = PI/6;

	int source_drag_id = -1;


	// Processing Sketch Setup
	public void setup() {
		size(360, 360);
		background(255);

		ellipseMode( CENTER );
		rectMode( CENTER );

		noStroke();

		ground = new PImage( width, height );

		robots = new Robot[10];
		for (int i=0; i<robots.length; i++) {
			robots[i] = new Robot( i*(width/5)+50, height/2, random(PI), 15, i );
		}


		sources = new Source[5];
		for (int i=0; i<sources.length; i++) {
			sources[i] = new Source( i*(width/6)+60, height/2+(i%2)*50, 1.0f, 100, i );

		}

		smooth();

		updateGround();
	}


	// Processing Sketch Main Loop
	public void draw() {
		background( 150 );

		image( ground, 0, 0 );

		fill( 255 );
		for (int i=0; i<numOfLights; i++) {
			sources[i].draw();

		}

		for (int i=0; i<numOfRobots; i++) {
			robots[i].move();
			robots[i].draw();
		}
	}




	 /**
	  * key press handler
	  */
	 public void keyPressed() {

		if (key==32) { // SPACEBAR

			specialSense = !specialSense;
			updateGround();
		}


		// num of lights
		if (key>='1' && key<='5') {
			numOfLights = 5-('5'-key);
			updateGround();
		}

	 }


	 public void mousePressed() {
		mouseDown = true;
	 }


	 public void mouseReleased() {
		mouseDown = false;
		updateGround();
	 }


	 /**
	  * update the light source on the ground
	  */
	 void updateGround() {


		float sum;
		int c;
		int px = 5;

		ground = new PImage( width, height );
		for (int i=0; i<width; i+=px ) {
			for (int k=0; k<height; k+=px ) {

				sum = 0;
				for (int m=0; m<numOfLights; m++ ) {

					sum += sources[m].getReading( i, k, true );
					if (sum>=1) break;

				}

				c = (int)min(sum*255, 255);


				for (int p=0; p<px; p++) {
					for (int q=0; q<px; q++) {
						ground.set( i+p, k+q, color(  c, c, (specialSense) ? c/8 : c ) );
						//ground.set(p, q, 0xff0000);
					}
				}
			}
		}

	 }

	 float nonlinear(float r, float rmax) {
		float f = (rmax - Math.min(r, rmax)) / rmax;
		return 0.5f - 0.5f*cos(f*PI);
	 }





	// Inner Classes ---------------


	/**
	 * Robot class
	 */
	 class Robot {

		float x, y, axle, half_axle, axleSq;
		float angle; // direction of the Robot
		float wheel_diff, wheel_average; // the difference and average of the wheels' rotating speed
		Wheel wA, wB; // two wheels
		Sensor sA, sB; // two sensors
		int id;

		Robot( float x, float y, float angle, float axle_length, int id ) {

			this.x = x;
			this.y = y;
			this.angle = angle;
			this.id = id;
			axle = axle_length;
			half_axle = axle/2;
			axleSq = axle*axle;

			wA = new Wheel( x+half_axle*cos(angle-HALF_PI), y+half_axle*sin(angle-HALF_PI), 5, 0 );
			wB = new Wheel( x+half_axle*cos(angle+HALF_PI), y+half_axle*sin(angle+HALF_PI), 5, 0 );

			sA = new Sensor( x+axle*cos(angle-HALF_PI/1.5f), y+axle*sin(angle-HALF_PI/1.5f) );
			sB = new Sensor( x+axle*cos(angle+HALF_PI/1.5f), y+axle*sin(angle+HALF_PI/1.5f) );

		}


		void draw() {

			fill(255,0,0);
			triangle( sA.x, sA.y, sB.x, sB.y, x+cos(angle)*axle*2f, y+sin(angle)*axle*2f );


			//fill(200,54,130);

			//fill(102,102,51);

			fill(0);
			wA.draw(angle, 1);
			wB.draw(angle-PI, -1);

			ellipse( x, y, axle*3, axle*3 );

			sA.draw();
			sB.draw();

		}



		void move() {

			checkBounds();


			// check sensor
			if (specialSense) { // special taste mode
				setLeftSpeed( sB.getSense( false ) );
				setRightSpeed( sA.getSense( false ) );
			} else {  // love mode
				setLeftSpeed( sA.getSense( false ) );
				setRightSpeed( sB.getSense( false ) );

			}


			// move

			wheel_diff = wA.d - wB.d;
			wheel_average = ( wA.d + wB.d ) / 2;
			angle += wheel_diff / axle;

			x += cos( angle ) * wheel_average;
			y += sin( angle ) * wheel_average;

			checkCollision();

			// wheels move
			float ang = angle - HALF_PI;

			wA.x = x + half_axle * cos( ang );
			wA.y = y + half_axle * sin( ang );

			ang =  angle + HALF_PI;

			wB.x = x + half_axle * cos( ang );
			wB.y = y + half_axle * sin( ang );


			// sensors move
			ang = angle - HALF_PI/2;

			sA.x = x + axle * cos( ang );
			sA.y = y + axle * sin( ang );

			ang =  angle + HALF_PI/2;

			sB.x = x + axle * cos( ang );
			sB.y = y + axle * sin( ang );



		}



		void checkBounds() {

			//x = ( x<0 ) ? width+x : ((x>width) ? x-width : x );
			//y = ( y<0 ) ? height+y : ((y>height) ? y-height : y );

			x = max( axle+5, min( width-axle-5, x ) );
			y = max( axle+5, min( height-axle-5, y ) );

		}


		void checkCollision() {

			float dx, dy, da;
			for (int i=0; i<numOfRobots; i++) {
				if (i!=id) {

					dx = x-robots[i].x;
					dy = y-robots[i].y;
					//if (abs(dx) <=axle && abs(dy)<=axle ) {
					if (dx*dx + dy*dy<axleSq ) {
						da = atan2( dy, dx );
						//angle = ;
						x = x + cos(da)*half_axle;
						y = y + sin(da)*half_axle;
						robots[i].x = robots[i].x + cos(da+PI)*half_axle;
						robots[i].y = robots[i].y + sin(da+PI)*half_axle;
					}

					//}
				}
			}
		 }


		void setLeftSpeed( float ang_speed ) {
			wA.setSpeed( ang_speed*move_speed );
		}

		void setRightSpeed( float ang_speed ) {
			wB.setSpeed( ang_speed*move_speed );
		}

		void changeLeftSpeed( float inc ) {
			wA.setSpeedChange( inc );
		}

		void changeRightSpeed( float inc ) {
			wB.setSpeedChange( inc );
		}


	 }



	 /**
	  * Robot's Wheel... customize the robots' style.
	  *
	  */
	 class Wheel {

		float x, y;
		float ang_speed, radius;
		float angle;
		float d;

		Wheel( float x, float y, float radius, float ang_speed ) {

			this.x = x;
			this.y = y;
			this.radius = radius;
			this.ang_speed = ang_speed;
			angle = 0;

			d = ang_speed * radius;
		}



		void setSpeed( float ang_speed ) {

			this.ang_speed = ang_speed;
			d = ang_speed * radius;
		}


		void setSpeedChange( float inc ) {
			ang_speed += inc;
			d = ang_speed * radius;
		}


		void draw(float ainc, int dir) {
			angle += ang_speed;
			if (angle>TWO_PI) angle -= TWO_PI;
			float temp = sin(angle)*0.5f+ainc;

			ellipse( x+cos(temp)*10*dir, y+sin(temp)*10*dir, 25, 25 );
		}
	 }



	 /**
	  * Robot's Sensor
	  */
	 class Sensor {

		float x, y;
		float maxReading;
		float sense;

		Sensor( float x, float y ) {
			this.x = x;
			this.y = y;

			maxReading = 1;

		}

		void setLocation( float x, float y ) {
			this.x = x;
			this.y = y;
		}

		float getSense( boolean plus ) {



			float sum = red( ground.get( (int)x, (int)y ) )/255.0f;
			sum = (plus) ? sum : 1-sum;
			sense = (specialSense) ? nonlinear( sum, maxReading ) : 1-sum;

			return sense;
		}


		void draw() {
			fill(255);
			ellipse( x, y, 20, 20 );
			fill(255*sense,100*sense,0);
			ellipse( x, y, 4+12*sense, 2+12*sense );
			fill(0);
			ellipse( x, y, 6*sense, 6*sense );
		}

	 }




	 /**
	  * Light Source class
	  */
	 class Source {

		float x, y;
		float strength; // between 0 to 1
		float max_radius;
		boolean dragging = true;

		int id;

		Source( float x, float y, float strength, float max_radius, int id ) {
			this.x = x;
			this.y = y;
			this.strength = strength;
			this.max_radius = max_radius;
			this.id = id;
		}

		void setLocation( float x, float y ) {
			this.x = x;
			this.y = y;
		}

		void draw() {

			if (id < numOfLights ) {
				checkCollision();
				// dragging?
				int range = 80;
				if (mouseDown && mouseX>x-range && mouseX<x+range && mouseY>y-range && mouseY<y+range) {
					if (source_drag_id==-1) {
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

				//fill(255);
				//ellipse(x, y, range, range );

			}
		}


		void checkCollision() {

			float dx, dy, da;
			for (int i=0; i<sources.length; i++) {
				if (i!=id) {

					dx = x-sources[i].x;
					dy = y-sources[i].y;
					//if (abs(dx) <=axle && abs(dy)<=axle ) {
						if (dx*dx + dy*dy<max_radius ) {
							da = atan2( dy, dx );
							sources[i].x = sources[i].x + cos(da+PI)*5;
							sources[i].y = sources[i].y + sin(da+PI)*5;
						}

					//}
				}
			}
		}


		float getReading( float tx, float ty, boolean plus ) {

			float d = dist( tx, ty, x, y );
			if (d >= max_radius) return ((plus) ? 0 : 1);

			//d = strength*(d/max_radius);
				d = 1-nonlinear( d, max_radius );

				return ((plus) ? 1-d : d );
			}



		 }





	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	// Processing Code End




}

