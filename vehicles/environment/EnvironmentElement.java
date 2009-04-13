package vehicles.environment;

/* This class could be changed to an abstract class if required
 * Might need to add in an area method
 * Added a point class instead of using xpos and ypos in this class
 * */
public class EnvironmentElement {
	private String name;
	private String fileLocation;
	private Point position;
	
	public EnvironmentElement(String n, String fl, Point p){
		this.name = n;
		this.fileLocation= fl;
		this.position = p;
		
	}	
	public String getName(){
		return this.name;
	}
	public void changeName(String name){
		this.name= name;
	}
	public String getFileLocation(){
		return this.fileLocation;
	}
	public void changeFileLocation(String f){
		this.fileLocation = f;
	}
	public Point getPosition(){
		return this.position;	
	}
	
	
}
