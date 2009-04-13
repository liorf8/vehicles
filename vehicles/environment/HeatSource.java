package vehicles.environment;


public class HeatSource extends EnvironmentElement{

	private double intensity;
	private double range;
	
	public HeatSource(String n, String fl, Point p, double i, double r){
		super( n, fl, p);
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
	public String modifyHeatSource(double range, double intensity, String name){
		this.range= range;
		this.intensity = intensity;
		String nameOne= name;
		return nameOne;
	}
}
