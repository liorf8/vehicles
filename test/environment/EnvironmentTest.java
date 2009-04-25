package test.environment;
import vehicles.environment.Environment;
import vehicles.environment.EnvironmentElement;
import vehicles.environment.HeatSource;
import vehicles.environment.LightSource;
import vehicles.environment.Point;

public class EnvironmentTest{
	public static void main(String args[]){
		/*
		 * Create a light source, populate it's attributes, and write to file
		 */
		LightSource ls = new LightSource();	
		ls.setFileLocation("xml/environments/StrongLightSource.xml");
		ls.setName("Strong Light Source");
		ls.setType(EnvironmentElement.LightSource);//TODO put this in object constructor
		ls.setPosition(new Point(15,42));		
		ls.setRadius(78);
		ls.setStrength(95);
		
		/*
		 * Create a heat source, populate it's attributes, and write to file
		 */
		HeatSource hs = new HeatSource();
		hs.setFileLocation("xml/environments/WeakHeatSource.xml");
		hs.setName("Weak Heat Source");
		hs.setType(EnvironmentElement.HeatSource); //TODO put this in object constructor
		hs.setPosition(new Point(30,40));
		hs.setRadius(10);
		hs.setStrength(30);		
		
		/*
		 * Create an environment and write to xml
		 */
		Environment e = new Environment("desert","xml/environments/desert.xml");
		e.setHeight(500);
		e.setWidth(200);
		//e.setLastModified(timeStamp) Shaun- will you tell me how to get the current time?
		//Karl, timestamp is autoset when writing xml
		e.addElement(ls);
		e.addElement(hs);
		e.saveEnvironment();
		
		/*Read that xml and create an object from it, then re-write it back to xml*/
		Environment env = new Environment("xml/environments/desert.xml"); //load up object values from xml
		env.setXMLLocation("xml/environments/desertDuplicate.xml");
		env.saveEnvironment();
		}
}
