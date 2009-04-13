package vehicles.environment;


public class WaterSource extends EnvironmentElement {
	private double depth;
	
	public WaterSource(String n, String fl, Point p, double d){
		super( n, fl ,p);
		this.depth = d;
	}
	public double getDepth(){
		return depth;
	}
	public void modifyDepth(double depth){
		 this.depth= depth;
	}
	public String modoifyWaterSource(double depth, String name){
		this.depth = depth;
		String nameOne = name;
		return nameOne;
	}
}
