/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vehicles.processing;
import processing.core.*;


public class ProcessingVehicle extends PApplet {

// Vehicle 1 - Getting Around
// 1 sensor, 1 motor
// Based upon Valentino Braitenberg's "Vehicles"
// by MARK ROLAND <http://www.markrolanddesign.com>
// August 30, 2005


Vehicle1 v1_1;
Vehicle1 v1_2;
Vehicle1 v1_3;
Vehicle1 v1_4;
Vehicle1 v1_5;


public void setup()
{
  size(420, 260);
  smooth();

  // Setup the Vehicles
  v1_1 = new Vehicle1(8, 0xff1FC309);
  v1_2 = new Vehicle1(10, 0xff66CC66);
  v1_3 = new Vehicle1(10, 0xff66BB66);
  v1_4 = new Vehicle1(12, 0xff669966);
  v1_5 = new Vehicle1(16, 0xff336633);
}

public void draw()
{
  background(0);
  //image(a, 0, 0);

  // Draw each vehicle
  v1_1.draw();
  v1_2.draw();
  v1_3.draw();
  v1_4.draw();
  v1_5.draw();
}
class Vehicle1{

  int   c_dia;             // vehicle diameter
  int c;                 // color
  float xpos, ypos;        // position variables

  float xspeed_0;          // Speed of the shape
  float yspeed_0;          // Speed of the shape

  int   xdirection;        //  - Left or + Right
  int   ydirection;        //  - Top or + Bottom

  // CONSTRUCTOR
  Vehicle1(int dia, int c_) {
    c_dia = dia;
    c = c_;

    reset();  // reset initial positions, speeds, & directions

  }

  public void draw() {

    // Get the "temperature" at the pixel to determine speed
    int xipos = PApplet.parseInt(xpos);
    int yipos = PApplet.parseInt(ypos);
    int cp = get(xipos + c_dia/2, yipos + c_dia/2);
    float temp = red(cp);

    // Update the position of the shape
    xpos = xpos + random(-c_dia, c_dia) / 4 + ( xspeed_0 * (temp / 255) * xdirection);
    ypos = ypos + random(-c_dia, c_dia) / 4 + ( yspeed_0 * (temp / 255) * ydirection);

    // Test to see if the shape exceeds the boundaries of the screen
    // If it does, reverse its direction by multiplying by -1
    if (xpos + c_dia/2 > width|| xpos - c_dia/2 < 0) {
      xdirection *= -1;
    }
    if (ypos + PApplet.parseFloat(c_dia)/2 > height || ypos - PApplet.parseFloat(c_dia)/2 < 0) {
      ydirection *= -1;
    }

    stroke(0,64,0);
    fill(c);
    ellipseMode(CENTER);
    ellipse(xpos, ypos, c_dia, c_dia);
    //ellipse(xpos+c_dia/2, ypos+c_dia/2, c_dia, c_dia);

    if(mousePressed){
      reset();
    }

  }

  public void reset(){
    xpos =  random(.2f * width, .8f * width);  //load random starting position
    ypos =  random(.2f * height, .8f * height);
    xspeed_0 = random(0, 96 / c_dia);
    yspeed_0 = random(0, 96 / c_dia);

    float rand_ix = random(-1, 1);
    if(rand_ix > 0){
      xdirection = 1;
    }
    else{
      xdirection = -1;
    }

    float rand_iy = random(-1, 1);
    if(rand_iy > 0){
      ydirection = 1;
    }
    else{
      ydirection = -1;
    }
  }

}
}
