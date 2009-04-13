package vehicles.environment;


public class Terrain extends EnvironmentElement {

	private double friction;
	private double imagePath;
	
	public Terrain(String n, String fl, Point p, double f, double ip){
		super( n, fl ,p);
		this.friction = f;
		this.imagePath = ip;
	}
	public double getFriction(){
		return this.friction;
	}
	public double getImagePath(){
		return this.imagePath;
	}
	public void modifyFriction(double friction){
		this.friction = friction;
	}
	public void modifyImagePath(double imagePath){
		this.imagePath = imagePath;
	}
}
