package vehicles.environment;

/**/
public class LightSource extends EnvironmentElement{

	private double intensity;
	private double range;
	
	public LightSource(String n, String fl, Point p, double i, double r){
		super( n, fl ,p);
		this.intensity = i;
		this.range = r;
	}
	public double getIntensity(){
		return this.intensity;
	}
	public double getRange(){
		return this.range;
	}
	public void modifyIntensity(double intensity){
		this.intensity=intensity;
	}
	public void modifyRange(double range){
		this.range = range;
	}
}
