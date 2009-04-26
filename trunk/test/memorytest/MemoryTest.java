package test.memorytest;
import java.util.Random;
import java.util.Vector;

import vehicles.environment.Environment;
import vehicles.environment.EnvironmentElement;
import vehicles.environment.Point;
import vehicles.vehicle.*;

public class MemoryTest {
	public static void main(String[] args){

		/*used to create a crazy environemnt to test vehicle memory
		 * 
		 */
		Environment crazy = new Environment("crazy", "xml/environments/crazy.env");
		crazy.setWidth(640);
		crazy.setHeight(480);
		crazy.setAuthor("Shaun");
		crazy.setDescription("Fuckin loads of elements");

		Random r = new Random();
		EnvironmentElement test;
		for(int i = 0; i < 250; i++){
			test = new EnvironmentElement();
			test.setType(EnvironmentElement.LightSource);//TODO put this in object constructor
			test.setPosition(new Point(r.nextInt(1000),r.nextInt(1000)));		
			test.setRadius(r.nextInt(100));
			test.setStrength(r.nextInt(100));
			crazy.addElement(test);
		}
		crazy.saveEnvironment();	
		
		
//		Testing adding an environment to vehicle memory
		
		Vehicle veh = new Vehicle("xml/vehicles/hungry.veh"); //constructor loads xml into an object
		
		Environment e = new Environment("xml/environments/desert.env");
		Vector<EnvironmentElement> els = e.getElements();
		EnvironmentElement ee = els.elementAt(0);
		for(int i = 0; i < 100; i++){
			veh.mu.addElement(ee);
		}

		//Testing if environment in memory
		System.out.println("Remebers element at (" + ee.getXpos() + "," + ee.getYpos() + ")" + 
				veh.mu.remembersElementAt(ee.getXpos(), ee.getYpos()));

		//testing getting environment xpos, ypos and type
		System.out.println("Element is of type: " + veh.mu.getTypeOfElementAt(ee.getXpos(), ee.getYpos()));

		//Testing memory deletion
		veh.mu.resetMem();
		System.out.println("Remebers element at (" + ee.getXpos() + "," + ee.getYpos() + ")" + 
				veh.mu.remembersElementAt(ee.getXpos(), ee.getYpos()));

		//Testing adding many many things in memory
		Environment crazyEnv = new Environment("xml/environments/crazy.env");
		Vector<EnvironmentElement> crazy_el = crazyEnv.getElements();
		for(int i = 0; i < crazy_el.size(); i++){
			EnvironmentElement cr = crazy_el.elementAt(i);
			for(int j = 0; j < 100; j++){
				veh.mu.addElement(cr);
			}
		}
		veh.mu.printMemory();
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
