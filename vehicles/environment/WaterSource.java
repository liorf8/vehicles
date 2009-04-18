package vehicles.environment;



public class WaterSource extends EnvironmentElement {
	private double depth;

	public WaterSource(String n, String fl, Point p, double d){
		super( n, fl ,p);
		this.depth = d;
	}
	public double waterDepth(){
		return depth;
	}
	public void modifyDepth(double depth){

		try{
			 value(depth);
		}catch(valueOutOfBounds de){
	
		}
		this.depth= depth;
	}
	public String modoifyWaterSource(double depth, String name){
		this.depth = depth;
		String nameOne = name;
		return nameOne;
	}
	public void value(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("WaterDepth "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
}
