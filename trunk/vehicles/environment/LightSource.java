package vehicles.environment;

public class LightSource extends EnvironmentElement{

	private double intensity;
	private double range;

	public LightSource(String n, String fl, Point p, double i, double r){
		super(n, fl ,p);
		this.intensity = i;
		this.range = r;
	}
	public double lightIntensity(){
		return this.intensity;
	}
	public double lightRange(){
		return this.range;
	}
	public void modifyIntensity(double intensity){

		try{
			 value(intensity);
		}catch(valueOutOfBounds de){

		}
		this.intensity=intensity;
	}
	public void modifyRange(double range){
		this.range = range;
	}
	public void value(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("LightIntensity "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
}
