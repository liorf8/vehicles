package vehicles.environment;

/* Here is the classes, it's a start. Not to sure about how some of the methods should be
 * done. Anyway let me know what changes need to be made. 
 * Does this class need an area method?
 * 
 * 
 * 
 * regards
 * James*/
import java.util.*;
public class Enviroment {
	private String name;
	@SuppressWarnings("unchecked")
	private Vector elementVector;
	private double width;
	private double height;
	private String fileLocation;
	
	@SuppressWarnings("unchecked")
	public Enviroment(String na, String fl){
		this.name = na;
		elementVector = new Vector();
		this.width = 0.0;
		this.height = 0.0;
		this.fileLocation = fl;
	}

	public String getName(){
		return this.name;
	}
	public void changeName(String name){
		this.name = name;
	}
	public double getWidth(){
		return this.width;
	}
	public void changeWidth(double width){
		this.width = width;
	}
	public double getHeigth(){
		return this.height;
	}
	public void changeHeight(double height){
		this.height= height;
	}
	public String getFileLocation(){
		return this.fileLocation;
	}
	@SuppressWarnings("unchecked")
	public Vector getVectorElement(){
		return elementVector;
	}
	@SuppressWarnings("unchecked")
	/*Might be best to add an area method to the EnvironmentElement class and
	 * use it here*/
	public boolean occupied(double xPos, double yPos, double width, double height){
		Vector v = new Vector();
		v = elementVector;
		boolean j=true;
		Enumeration n = v.elements();
		   while (n.hasMoreElements()){
			   EnvironmentElement obj = (EnvironmentElement)n.nextElement();
			   if((obj.getPosition().getXpos()==(xPos))&&(obj.getPosition().getYpos()==yPos)){
				   j= true;
			   }else{
				   j= false;
			   }
		  }
		   return j;
	}
	@SuppressWarnings("unchecked")
	//adding the element to the Vector
	public void saveElement(EnvironmentElement eE){
		elementVector.add(eE);
	}
	public void saveElementTwo(String name, String path){
		//not sure what this is to contain
		String n = null; String file = null;
		double x= 0.0; double y = 0.0;
		Point p = new Point(x,y);
		EnvironmentElement e = new EnvironmentElement(n, file, p);
		e.changeName(name);
		e.changeFileLocation(path);
	}
	@SuppressWarnings("unchecked")
	/*An element has been created and saved in the Vector
	 * so using the name of the element we can retrieve it*/
	public EnvironmentElement loadElement(String elementName){
		EnvironmentElement e ;
		String name = null;
		String FileLocation = null;
		double x=0.0;
		double y=0.0;
		Point p = new Point(x,y);
		Enumeration n = elementVector.elements();
		   while (n.hasMoreElements()){
			   EnvironmentElement obj = (EnvironmentElement)n.nextElement();
			   if (obj.getName()== elementName){
				   name = obj.getName();
				   FileLocation = obj.getFileLocation();
				   p = obj.getPosition();
			   }
		  }
		   e = new EnvironmentElement(name, FileLocation, p);
		   return e;
	}
	//not to sure if this is what is required?
	public void environmentElement(String fileLocation){
		String name = null; String file = null;
		double x= 0.0; double y = 0.0;
		Point p = new Point(x,y);
		EnvironmentElement e = new EnvironmentElement(name, file, p);
		e.changeFileLocation(fileLocation);
		
	}
	
}
