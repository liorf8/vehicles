package test.environment;
import vehicles.environment.Environment;
import vehicles.environment.EnvironmentElement;
import vehicles.environment.Point;

public class EnvironmentTest{
	public static void main(String args[]){
		/*
		 * Create a light source, populate it's attributes, and write to file
		 */
		EnvironmentElement ls = new EnvironmentElement();	
		ls.setType(EnvironmentElement.LightSource);//TODO put this in object constructor
		ls.setPosition(new Point(150,42));		
		ls.setRadius(78);
		ls.setStrength(95);		
		/*
		 * Create a heat source, populate it's attributes, and write to file
		 */
		EnvironmentElement hs = new EnvironmentElement();
		hs.setType(EnvironmentElement.HeatSource); //TODO put this in object constructor
		hs.setPosition(new Point(400,240));
		hs.setRadius(20);
		hs.setStrength(60);	
		
		EnvironmentElement hes = new EnvironmentElement();
		hes.setType(EnvironmentElement.HeatSource); //TODO put this in object constructor
		hes.setPosition(new Point(400,240));
		hes.setRadius(300);
		hes.setStrength(60);	
			
			
		EnvironmentElement hss = new EnvironmentElement();
		hss.setType(EnvironmentElement.HeatSource); //TODO put this in object constructor
		hss.setPosition(new Point(500,140));
		hss.setRadius(40);
		hss.setStrength(86);	
	
		/*
		 * Create an environment and write to xml
		 */
		Environment e = new Environment("desert","xml/environments/desert.env");
		e.setWidth(800);
		e.setHeight(600);
		e.setAuthor("some guy");
		e.setDescription("a harsh environment");
		e.addElement(ls);
		e.addElement(hs);
		e.addElement(hes);
		e.addElement(hss);
		e.saveEnvironment();

		/*Read that xml and create an object from it, then re-write it back to xml*/
		Environment env = new Environment("xml/environments/default_environment1.env"); //load up object values from xml
		env.setXMLLocation("xml/environments/desertDuplicate.env");
		env.saveEnvironment();	
		
		
	}
}
