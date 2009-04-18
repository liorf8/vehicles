package vehicles.environment;

public class HeatSource extends EnvironmentElement{

	private double intensity;
	private double range;

	public HeatSource(String n, String fl, Point p, double i, double r){
		super(n, fl, p);
		this.intensity = i;
		this.range = r;
	}
	public double heatIntensity(){
		return this.intensity;
	}
	public double heatRange(){
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
	public String modifyHeatSource(double range, double intensity, String name){
		this.range= range;
		this.intensity = intensity;
		String nameOne= name;
		return nameOne;
	}
	public void value(double e) throws valueOutOfBounds{
		if (e <0.0 || e>100.0){
		throw new valueOutOfBounds("HeatIntensity "+e+" out of bounds.\nValue must be greater than 0\n" +
				" or less than or equal to 100\n");
		}
	}
}