package vehicles.environment;


public class Terrain extends EnvironmentElement {

	private double friction;
	private String imagePath;

	public Terrain(String n, String fl, Point p, double f, String ip){
		super(n, fl ,p);
		this.friction = f;
		this.imagePath = ip;
	}
	public double terrainFriction(){

		return this.friction;
	}
	public String terrainImagePath(){
		return this.imagePath;
	}
	public void modifyFriction(double friction){

		try{
			 value(friction);
		}catch(valueOutOfBounds de){
	
		}
		this.friction = friction;
	}
	public void modifyImagePath(String imagePath){
		this.imagePath = imagePath;
	}
	public void value(double e) throws valueOutOfBounds{
		if (e <0.0 || e>10.0){
		throw new valueOutOfBounds("TerrainFriction "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
}

