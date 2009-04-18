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
 public class Embedded extends PApplet {

     public void setup() {
         // original setup code here ...
         size(400, 400);
         background(100);
         // prevent thread from starving everything else
         noLoop();
     }

     public void draw() {
         // drawing code goes here
     }

     public void mouseDragged() {
         // do something based on mouse movement
         line(mouseX, mouseY, pmouseX, pmouseY);
         // update the screen (run draw once)
         redraw();
     }
 }

