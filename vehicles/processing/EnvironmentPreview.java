package vehicles.processing;
import processing.core.*;

/**
 *
 * @author Niall O'Hara
 */
public class EnvironmentPreview extends PApplet {
     @Override
     public void setup() {
         // original setup code here ...
         size(400, 400);
         background(100);
         // prevent thread from starving everything else
         noLoop();
     }

     @Override
     public void draw() {
         // drawing code goes here
     }

     @Override
     public void mouseDragged() {
         // do something based on mouse movement
         line(mouseX, mouseY, pmouseX, pmouseY);
         // update the screen (run draw once)
         redraw();
     }
 }
